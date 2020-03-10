package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public class CollectBehaviour extends Behaviour {


    public CollectBehaviour(LitterAgent agent) {
        super(agent);
    }


     public Action act(ExploredMap exploredMap) {

         agent.agentDestination = agent.litterDetector.readSensor(exploredMap);

        if (agent.getPosition().equals(agent.agentDestination)) {
            LitterBin currentBin = (LitterBin) exploredMap.map.get(agent.agentDestination);
            return new LoadAction(currentBin.getTask());
        }

        return new MoveTowardsAction(agent.agentDestination);

    }

}


