package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.Cell;
import uk.ac.nott.cs.g53dia.library.LitterAgent;
import uk.ac.nott.cs.g53dia.library.Point;
import uk.ac.nott.cs.g53dia.library.RechargePoint;


public class RechargeDetector extends Sensor{


    public RechargeDetector(LitterAgent agent) {
        super(agent);
    }


    public Point readSensor(ExploredMap exploredMap){

        Point position = agent.getPosition();
        Point destination = new Point(99999999, 99999999);
        int size = 30;

        while (destination.getX() == 99999999 && destination.getY() == 99999999) {

            Cell[][] view = exploredMap.getView(position, size);

            for (Cell[] row : view) {
                for (Cell cell : row) {
                    if (cell instanceof RechargePoint) {
                        if (agent.getPosition().distanceTo(cell.getPoint()) < agent.getPosition().distanceTo(destination)) {
                            destination = cell.getPoint();
                        }
                    }
                }
            }

            size += 10;

        }

        return destination;

    }

}
