package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;


public class StationDetector extends Sensor{


    public StationDetector(LitterAgent agent) {
        super(agent);
    }


    public Point readSensor(ExploredMap exploredMap){

        int size = 30;
        Point position = agent.getPosition();
        Cell[][] view = exploredMap.getView(position, size);
        Point errorDestination = new Point(99999999, 99999999);
        Point destination = errorDestination;

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

}
