package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public abstract class Behaviour {

    protected DemoLitterAgent agent;

    public enum StateType {
        BATTERY_STATE,
        EXPLORE_STATE,
        COLLECT_STATE,
        DUMP_STATE;
    }


    public Behaviour(LitterAgent agent) {
        this.agent = (DemoLitterAgent)agent;
    }


    public abstract Action act(ExploredMap exploredMap);

}
