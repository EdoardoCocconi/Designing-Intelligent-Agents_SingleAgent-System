package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public class RechargeBehaviour extends State{

    public RechargeBehaviour(LitterAgent agent) {
        super(agent);
    }


    public Action Return (ExploredMap exploredMap){

        Point destination = new RechargeDetector(agent).readSensor(exploredMap);

        if (agent.getPosition().equals(destination)) {
            return new RechargeAction();
        }
        return new MoveTowardsAction(destination);

    }

}