package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public class RechargeBehaviour extends Behaviour {

    public RechargeBehaviour(LitterAgent agent) {
        super(agent);
    }


    public Action act(ExploredMap exploredMap){

        agent.agentDestination = new RechargeDetector(agent).readSensor(exploredMap);

        if (agent.getPosition().equals(agent.agentDestination)) {
            return new RechargeAction();
        }
        return new MoveTowardsAction(agent.agentDestination);

    }

}