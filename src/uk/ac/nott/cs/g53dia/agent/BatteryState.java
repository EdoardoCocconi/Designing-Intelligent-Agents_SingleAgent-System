package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;

public class BatteryState extends State{


    public BatteryState(LitterAgent agent, StateType previousState) {
        super(agent, StateType.BATTERY_STATE);
    }


    public Point closestRecharge(ExploredMap exploredMap){

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


    public Action Return (ExploredMap exploredMap){

        if (agent.getPosition().equals(closestRecharge(exploredMap))) {
            agent.setNextState(agent.getPreviousState());
            return new RechargeAction();
        }
        return new MoveTowardsAction(closestRecharge(exploredMap));


    }

}