package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.agent.State;
import uk.ac.nott.cs.g53dia.library.*;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.ceil;
import static uk.ac.nott.cs.g53dia.library.LitterAgent.RECHARGE_POINT_LOCATION;

public class CollectState extends State {


    double maxCapacity = LitterAgent.MAX_LITTER;


    public CollectState(LitterAgent agent) {
        super(agent, StateType.COLLECT_STATE);

    }


    public Point closestLitter(ExploredMap exploredMap) {

        int currentWaste = agent.getWasteLevel();
        int currentRecycling = agent.getRecyclingLevel();
        double currentLitter = agent.getLitterLevel();
        double currentCapacity = maxCapacity - currentLitter;
        double capacityPercentage = currentCapacity / maxCapacity;
        int size = (int) ceil(30 * capacityPercentage);

        Point position = agent.getPosition();
        Cell[][] view = exploredMap.getView(position, size);
        Point destination = new Point(99999999, 99999999);
        LitterBin currentBin = null;




        for (Cell[] row : view) {
            for (Cell cell : row) {
                if (currentWaste != 0 && currentRecycling == 0) {
                    if (cell instanceof WasteBin) {
                        WasteBin wasteBin = (WasteBin) cell;
                        if (wasteBin.getTask() != null /*&& currentCapacity >= wasteBin.getTask().getRemaining()*/) {
                            if (agent.getPosition().distanceTo(cell.getPoint()) < agent.getPosition().distanceTo(destination)) {
                                destination = cell.getPoint();
                                currentBin = (LitterBin) cell;
                            }
                        }
                    }
                } else if (currentWaste == 0 && currentRecycling != 0) {
                    if (cell instanceof RecyclingBin) {
                        RecyclingBin recyclingBin = (RecyclingBin) cell;
                        if (recyclingBin.getTask() != null /*&& currentCapacity >= recyclingBin.getTask().getRemaining()*/) {
                            if (agent.getPosition().distanceTo(cell.getPoint()) < agent.getPosition().distanceTo(destination)) {
                                destination = cell.getPoint();
                                currentBin = (LitterBin) cell;
                            }
                        }
                    }
                } else {
                    if (cell instanceof LitterBin) {
                        LitterBin litterBin = (LitterBin) cell;
                        if (litterBin.getTask() != null) {
                            if (agent.getPosition().distanceTo(cell.getPoint()) < agent.getPosition().distanceTo(destination)) {
                                destination = cell.getPoint();
                                currentBin = (LitterBin) cell;
                            }
                        }
                    }
                }
            }
        }

        return destination;

    }


    public Action Return(ExploredMap exploredMap) {

        Point destination = closestLitter(exploredMap);
        double currentLitter = agent.getLitterLevel();
        Point originalDestination = new Point(99999999, 99999999);

        if (currentLitter == maxCapacity || destination.equals(originalDestination) && currentLitter != 0) {
            agent.setNextState(StateType.DUMP_STATE);
            return new DumpState(agent).Return(exploredMap);
        } else if (agent.getPosition().equals(destination)) {
            LitterBin currentBin = (LitterBin)exploredMap.map.get(destination);
            return new LoadAction(currentBin.getTask());
        }

        return new MoveTowardsAction(destination);

    }


}


