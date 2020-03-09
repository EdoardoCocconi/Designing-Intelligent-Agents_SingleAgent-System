package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;

public class DumpState extends State {


    public DumpState(LitterAgent agent) {
        super(agent, StateType.DUMP_STATE);
    }


    public Point closestStation(ExploredMap exploredMap){
        int size = 30;
        Point position = agent.getPosition();
        Cell[][] view = exploredMap.getView(position, size);
        Point destination = new Point(99999999, 99999999);

        if (agent.getWasteLevel() != 0) {

            for (Cell[] row : view) {
                for (Cell cell : row) {
                    if (cell instanceof WasteStation) {
                        if (agent.getPosition().distanceTo(cell.getPoint()) < agent.getPosition().distanceTo(destination)) {
                            destination = cell.getPoint();
                        }
                    }
                }
            }

        } else {


            for (Cell[] row : view) {
                for (Cell cell : row) {
                    if (cell instanceof RecyclingStation) {
                        if (agent.getPosition().distanceTo(cell.getPoint()) < agent.getPosition().distanceTo(destination)) {
                            destination = cell.getPoint();
                        }
                    }
                }
            }

        }

        return destination;

    }


    public Action Return(ExploredMap exploredMap) {

        double maxCapacity = LitterAgent.MAX_LITTER;
        Point litterDestination = new CollectState(agent).closestLitter(exploredMap);
        double currentLitter = agent.getLitterLevel();
        Point originalDestination = new Point(99999999, 99999999);

        if (currentLitter != maxCapacity && agent.getPosition().equals(litterDestination)) {
            LitterBin currentBin = (LitterBin)exploredMap.map.get(litterDestination);
            return new LoadAction(currentBin.getTask());
        } else if (currentLitter != maxCapacity && !litterDestination.equals(originalDestination)) {
            agent.setNextState(StateType.COLLECT_STATE);
            return new MoveTowardsAction(litterDestination);
        }


        Point destination = closestStation(exploredMap);


        if (agent.getPosition().equals(destination)) {
            agent.setNextState(StateType.COLLECT_STATE);
            return new DisposeAction();
        } else {
            return new MoveTowardsAction(destination);
        }

    }

}