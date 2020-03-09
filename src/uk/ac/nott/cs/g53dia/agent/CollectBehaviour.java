package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public class CollectBehaviour extends Behaviour {


    public CollectBehaviour(LitterAgent agent) {
        super(agent);
    }


     public Action act(ExploredMap exploredMap) {

        Point destination = agent.litterDetector.readSensor(exploredMap);

        if (agent.getPosition().equals(destination)) {
            LitterBin currentBin = (LitterBin) exploredMap.map.get(destination);
            return new LoadAction(currentBin.getTask());
        }

        return new MoveTowardsAction(destination);

    }

}


