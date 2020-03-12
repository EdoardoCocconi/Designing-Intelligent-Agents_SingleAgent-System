package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.agent.Behaviour.BehaviourType;
import uk.ac.nott.cs.g53dia.library.*;

import java.util.Random;

/**
 * A simple example LitterAgent
 *
 * @author Julian Zappala
 */
/*
 * Copyright (c) 2011 Julian Zappala
 *
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */
public class DemoLitterAgent extends LitterAgent {

    RechargeBehaviour rechargeBehaviour = new RechargeBehaviour(this);
    ExploreBehaviour exploreBehaviour = new ExploreBehaviour(this);
    CollectBehaviour collectBehaviour = new CollectBehaviour(this);
    DisposeBehaviour disposeBehaviour = new DisposeBehaviour(this);

    LitterDetector litterDetector = new LitterDetector(this);
    RechargeDetector rechargeDetector = new RechargeDetector(this);
    StationDetector stationDetector = new StationDetector(this);

    ExploredMap exploredMap = new ExploredMap();
    final int finalTime = 10000;

    public Point agentDestination;
    final Point errorDestination = new Point(99999999, 99999999);
    final Point origin = new Point(0, 0);

    public DemoLitterAgent() {
        this(new Random());
    }

    /**
     * The tanker implementation makes random moves. For reproducibility, it
     * can share the same random number generator as the environment.
     *
     * @param r The random number generator.
     */
    public DemoLitterAgent(Random r) {
        this.r = r;
    }


    private BehaviourType sense(ExploredMap exploredMap, long timestep) {

        double maxCapacity = LitterAgent.MAX_LITTER;
        double currentLitter = this.getLitterLevel();
        BehaviourType nextState;

        if (rechargeDetector.isRechargeInRange(exploredMap, timestep)) {
            nextState = BehaviourType.BATTERY_BEHAVIOUR;
        } else if (currentLitter != maxCapacity && (currentLitter == 0 || !litterDetector.readSensor(exploredMap).equals(errorDestination))) {
            nextState = BehaviourType.COLLECT_BEHAVIOUR;
        } else if (!stationDetector.readSensor(exploredMap).equals(errorDestination)) {
            nextState = BehaviourType.DUMP_BEHAVIOUR;
        } else {
            nextState = BehaviourType.EXPLORE_BEHAVIOUR;
        }

        return nextState;

    }


    private Action act(BehaviourType nextState) {
        Action resultAction = null;

        switch (nextState) {

            case BATTERY_BEHAVIOUR:
                resultAction = rechargeBehaviour.act(exploredMap);
                break;
            case EXPLORE_BEHAVIOUR:
                resultAction = exploreBehaviour.act(exploredMap);
                break;
            case COLLECT_BEHAVIOUR:
                resultAction = collectBehaviour.act(exploredMap);
                break;
            case DUMP_BEHAVIOUR:
                resultAction = disposeBehaviour.act(exploredMap);
                break;
        }


        return resultAction;
    }


    public Action senseAndAct(Cell[][] view, long timestep) {

        exploredMap.updateMap(view);

        BehaviourType nextState = sense(exploredMap, timestep);
        return act(nextState);

    }
}
