package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public class DisposeBehaviour extends Behaviour {


    public DisposeBehaviour(LitterAgent agent) {
        super(agent);
    }


    public Action act(ExploredMap exploredMap) {

        Point destination = agent.stationDetector.readSensor(exploredMap);

        if (agent.getPosition().equals(destination)) {
            return new DisposeAction();
        } else {
            return new MoveTowardsAction(destination);
        }

    }

}