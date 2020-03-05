package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.agent.State.StateType;
import uk.ac.nott.cs.g53dia.library.*;

import java.util.Random;

import static sun.security.pkcs11.wrapper.PKCS11Constants.FALSE;

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

    private StateType nextState = StateType.COLLECT_STATE;
    private StateType previousState = StateType.COLLECT_STATE;
    public boolean isMapCreated = FALSE;
    ExploredMap exploredMap = new ExploredMap(this);


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


    public void setNextState(StateType nextState) {
        this.previousState = this.nextState;
        this.nextState = nextState;
    }

    public StateType getPreviousState() {
        return previousState;
    }

    public void RechargeCondition(ExploredMap exploredMap) {

        Point destination;
        destination = new BatteryState(this, nextState).closestRecharge(exploredMap);
        int distance = this.getPosition().distanceTo(destination);

        double charge = this.getChargeLevel();
        double maxCharge = LitterAgent.MAX_CHARGE;

//        if (charge <= distance + 1){
//            System.out.println("Was about to die");
//            setNextState(StateType.BATTERY_STATE);
//        } else if (charge <= maxCharge * 0.8 && distance <= ceil(4 * pow((1.0 - charge / maxCharge), 2) * 10)) {
//            setNextState(StateType.BATTERY_STATE);
//        }

        if (charge <= distance + 2) {
//            System.out.println("charge: " + charge + " distance: " + distance);
            setNextState(StateType.BATTERY_STATE);
        } else if (charge <= maxCharge * 0.9 && distance <= 3) {
            setNextState(StateType.BATTERY_STATE);
        } else if (charge <= maxCharge * 0.6 && distance <= 4) {
            setNextState(StateType.BATTERY_STATE);
        } else if (charge <= maxCharge * 0.5 && distance <= 10) {
            setNextState(StateType.BATTERY_STATE);
        } /*else if (charge <= maxCharge * 0.4 && distance <= 15) {*/
//            setNextState(StateType.BATTERY_STATE);
//        } else if (charge <= maxCharge * 0.3 && distance <= 20) {
//            setNextState(StateType.BATTERY_STATE);
//        } else if (charge <= maxCharge * 0.2 && distance <= 25) {
//            setNextState(StateType.BATTERY_STATE);
//        }


//        if (charge <= maxCharge * 0.9 && distance <= 3) {
//            setNextState(StateType.BATTERY_STATE);
//        } else if (charge <= maxCharge * 0.5 && distance <= 10) {
//            setNextState(StateType.BATTERY_STATE);
//        } else if (charge <= maxCharge * 0.2) {
//            setNextState(StateType.BATTERY_STATE);
//        }

    }


    /*
     * The following is a simple demonstration of how to write a tanker. The
     * code below is very stupid and simply moves the tanker randomly until the
     * charge agt is half full, at which point it returns to a charge pump.
     */
    public Action senseAndAct(Cell[][] view, long timestep) {

        exploredMap.Update(view);

        if (nextState != StateType.BATTERY_STATE) {
            RechargeCondition(exploredMap);
        }

        Action resultAction = null;

        switch (nextState) {

            case BATTERY_STATE:
                resultAction = new BatteryState(this, nextState).Return(exploredMap);
                break;
            case EXPLORE_STATE:
                resultAction = new ExploreState(this).Return(exploredMap);
                break;
            case COLLECT_STATE:
                resultAction = new CollectState(this).Return(exploredMap);
                break;
            case DUMP_STATE:
                resultAction = new DumpState(this).Return(exploredMap);
                break;
        }


        return resultAction;

    }
}
