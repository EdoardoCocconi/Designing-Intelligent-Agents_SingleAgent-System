package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public class ExploreBehaviour extends Behaviour {


    public ExploreBehaviour(LitterAgent agent) {
        super(agent);
    }


    public Action act(ExploredMap exploredMap) {

        Point destination = agent.errorDestination;
        if (agent.getPosition().distanceTo(agent.origin) > 30)
            destination = agent.origin;

        return new MoveTowardsAction(destination);

    }


}
