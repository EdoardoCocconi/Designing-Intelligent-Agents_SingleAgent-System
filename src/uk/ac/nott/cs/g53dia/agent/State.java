package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;

import static uk.ac.nott.cs.g53dia.library.LitterAgent.RECHARGE_POINT_LOCATION;

public abstract class State {

    protected DemoLitterAgent agent;

    public enum StateType {
        BATTERY_STATE,
        EXPLORE_STATE,
        COLLECT_STATE,
        DUMP_STATE;
    }

    private StateType stateType;

    public State(LitterAgent agent, StateType stateType) {
        this.agent = (DemoLitterAgent)agent;
        this.stateType = stateType;
    }

    public Point getDestination(ExploredMap exploredMap, Object object){

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

    public abstract Action Return(ExploredMap exploredMap);

}
