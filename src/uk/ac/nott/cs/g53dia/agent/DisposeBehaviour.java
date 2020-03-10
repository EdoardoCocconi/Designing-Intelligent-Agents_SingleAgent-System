package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public class DisposeBehaviour extends Behaviour {


    public DisposeBehaviour(LitterAgent agent) {
        super(agent);
    }


    public Action act(ExploredMap exploredMap) {

        agent.agentDestination = agent.stationDetector.readSensor(exploredMap);

        if (agent.getPosition().equals(agent.agentDestination)) {
            return new DisposeAction();
        } else {
            return new MoveTowardsAction(agent.agentDestination);
        }

    }

}