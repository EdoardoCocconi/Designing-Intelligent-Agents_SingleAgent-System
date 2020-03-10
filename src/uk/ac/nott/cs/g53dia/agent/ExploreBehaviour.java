package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class ExploreBehaviour extends Behaviour {

    public boolean isExplorationGoing = TRUE;

    int halfSquareSide = 200;

    Point origin = new Point(0, 0);

    Point vertex1 = new Point(halfSquareSide, -halfSquareSide);
    Point vertex2 = new Point(-halfSquareSide, -halfSquareSide * 3);
    Point vertex3 = new Point(-halfSquareSide * 3, -halfSquareSide);
    Point vertex4 = new Point(-halfSquareSide, halfSquareSide);


    Point destination = vertex1;


    public ExploreBehaviour(LitterAgent agent) {
        super(agent);
    }


    public Action act(ExploredMap exploredMap) {

        if (exploredMap.map.containsKey(vertex4)) {
            destination = origin;
            int distanceToOrigin = agent.getPosition().distanceTo(origin);
            if (distanceToOrigin <= 30) {
                isExplorationGoing = FALSE;
            }
        } else if (agent.getPosition().distanceTo(vertex3) <= 30) {
            destination = vertex4;
        } else if (agent.getPosition().distanceTo(vertex2) <= 30) {
            destination = vertex3;
        } else if (agent.getPosition().distanceTo(vertex1) <= 30) {
            destination = vertex2;
        }

        agent.agentDestination = destination;

        return new MoveTowardsAction(agent.agentDestination);

    }

}
