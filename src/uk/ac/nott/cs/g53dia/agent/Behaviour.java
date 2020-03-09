package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public abstract class State {

    protected DemoLitterAgent agent;

    public enum StateType {
        BATTERY_STATE,
        EXPLORE_STATE,
        COLLECT_STATE,
        DUMP_STATE;
    }


    public State(LitterAgent agent) {
        this.agent = (DemoLitterAgent)agent;
    }


    public abstract Action Return(ExploredMap exploredMap);

}
