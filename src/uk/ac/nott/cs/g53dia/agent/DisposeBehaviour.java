package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public class DisposeBehaviour extends State {


    public DisposeBehaviour(LitterAgent agent) {
        super(agent);
    }


    public Action Return(ExploredMap exploredMap) {

        Point destination = new StationDetector(agent).readSensor(exploredMap);

        if (agent.getPosition().equals(destination)) {
            return new DisposeAction();
        } else {
            return new MoveTowardsAction(destination);
        }

    }

}