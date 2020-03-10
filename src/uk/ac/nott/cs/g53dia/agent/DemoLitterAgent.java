package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.agent.Behaviour.StateType;
import uk.ac.nott.cs.g53dia.library.*;

import java.util.Random;

import static sun.security.pkcs11.wrapper.PKCS11Constants.FALSE;
import static sun.security.pkcs11.wrapper.PKCS11Constants.TRUE;

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


    public boolean shouldRecharge(ExploredMap exploredMap, long timestep) {

        boolean recharge = FALSE;
        Point destination;
        destination = rechargeDetector.readSensor(exploredMap);
        int distance = getPosition().distanceTo(destination);

        double charge = getChargeLevel();
        double maxCharge = LitterAgent.MAX_CHARGE;


//        if (charge <= distance + 1){
//            System.out.println("Was about to die");
//            recharge = TRUE;
//        } else if (charge <= maxCharge * 0.8 && distance <= ceil(4 * pow((1.0 - charge / maxCharge), 2) * 10)) {
//            recharge = TRUE;
//        }

        if (agentDestination != null) {

            if (charge <= distance + 2 /*+ this.getPosition().distanceTo(agentDestination)*/) {
//            System.out.println("charge: " + charge + " distance: " + distance);
                recharge = TRUE;
            } else if (charge <= maxCharge * 0.9 && distance <= 3) {
                recharge = TRUE;
            } else if (charge <= maxCharge * 0.6 && distance <= 4) {
                recharge = TRUE;
            } else if (charge <= maxCharge * 0.5 && distance <= 10) {
                recharge = TRUE;
            }
        }/*else if (charge <= maxCharge * 0.4 && distance <= 15) {*/
//            recharge = TRUE;
//        } else if (charge <= maxCharge * 0.3 && distance <= 20) {
//            recharge = TRUE;
//        } else if (charge <= maxCharge * 0.2 && distance <= 25) {
//            recharge = TRUE;
//        }


//        if (charge <= maxCharge * 0.9 && distance <= 3) {
//            recharge = TRUE;
//        } else if (charge <= maxCharge * 0.5 && distance <= 10) {
//            recharge = TRUE;
//        } else if (charge <= maxCharge * 0.2) {
//            recharge = TRUE;
//        }

        // Doesn't charge at the end. Only cares about points
        if ((finalTime - timestep) < maxCharge && charge > (finalTime - timestep)) {
            recharge = FALSE;
        }

        return recharge;
    }


    private StateType sense(ExploredMap exploredMap, long timestep) {

        double maxCapacity = LitterAgent.MAX_LITTER;
        double currentLitter = this.getLitterLevel();
        StateType nextState;

        if (shouldRecharge(exploredMap, timestep)) {
            nextState = StateType.BATTERY_STATE;
        } else if (FALSE/*exploreBehaviour.isExplorationGoing*/) {
            nextState = StateType.EXPLORE_STATE;
        } else if (currentLitter != maxCapacity && (currentLitter == 0 || !litterDetector.readSensor(exploredMap).equals(errorDestination))) {
            nextState = StateType.COLLECT_STATE;
        } else {
            nextState = StateType.DUMP_STATE;
        }


        return nextState;

    }


    private Action act(StateType nextState) {
        Action resultAction = null;

        switch (nextState) {

            case BATTERY_STATE:
                resultAction = rechargeBehaviour.act(exploredMap);
                break;
            case EXPLORE_STATE:
                resultAction = exploreBehaviour.act(exploredMap);
                break;
            case COLLECT_STATE:
                resultAction = collectBehaviour.act(exploredMap);
                break;
            case DUMP_STATE:
                resultAction = disposeBehaviour.act(exploredMap);
                break;
        }


        return resultAction;
    }


    public Action senseAndAct(Cell[][] view, long timestep) {

        if (TRUE/*exploreBehaviour.isExplorationGoing*/)
            exploredMap.updateMap(view);
        else {
            exploredMap.updateTasks(view);
        }

        StateType nextState = sense(exploredMap, timestep);
        return act(nextState);

    }
}
