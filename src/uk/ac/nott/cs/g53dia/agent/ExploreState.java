package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;

public class ExploreState extends State{

    LitterAgent agent;

    public ExploreState(LitterAgent agent) {
        super(agent, StateType.BATTERY_STATE);
        this.agent = agent;
    }

    public Action Return (ExploredMap exploredMap){

        int size = 30;
        Point position = agent.getPosition();
        Cell[][] view = exploredMap.getView(position, size);

        System.out.println("Explore State");
        Point destination = new Point(500,0);
        return new MoveTowardsAction(destination);

    }

}
