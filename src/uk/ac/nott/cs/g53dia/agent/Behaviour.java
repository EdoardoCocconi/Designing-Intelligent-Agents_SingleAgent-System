package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public abstract class Behaviour {

    protected DemoLitterAgent agent;

    public enum BehaviourType {
        BATTERY_BEHAVIOUR,
        EXPLORE_BEHAVIOUR,
        COLLECT_BEHAVIOUR,
        DUMP_BEHAVIOUR;
    }


    public Behaviour(LitterAgent agent) {
        this.agent = (DemoLitterAgent)agent;
    }


    public abstract Action act(ExploredMap exploredMap);

}
