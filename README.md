# Designing Intelligent Agents: Single Agent System

## Getting Started






The java source code of the agent can be found in the following folder:

The source code of the environment in which the agent operates can be found in the following folder:

To see the agent in action, run the file:

To measure the agent performance over 10 runs, run the file:

## Architecture

The agent has a reactive architecture with hierarchical control. The hierarchy is implemented in the Sense method of DemoLitterAgent as a series of if-conditions. The higher the priority the earlier the condition is checked. If a condition is met, the corresponding behavior is triggered. The behaviors are listed here from highest priority to lowest priority:

-	**RechargeBehaviour:** if the agent is on the target RechargeStation perform a RechargeAction, otherwise MoveTowards RechargeStation.
-	**CollectBehaviour:** if the agent is on the target LitterBin perform a LoadAction, otherwise MoveTowards LitterBin.
-	**DisposeBehaviour:** if the agent is on the target Station perform a DisposeAction, otherwise MoveTowards Station.
-	**ExploreBehaviour:**
If the agent is at distance <= 30 from the origin get away from the origin, else MoveTowards origin.

The Sense method receives percepts from 3 Sensors:

-	**RechargeDetector:** detects recharge stations inside a radius that is related to how much battery the agent has already lost. If there are recharge stations in this radius, RechargeBehaviour is triggered.
-	**LitterDetector:** detects the bin with the highest litter over distance ratio within the specified field of view.
-	**StationDetector:** detects the closest station.

The sense method runs at every timestep inside senseAndAct and selects a Behaviour. If nothing is detected, the ExploreBehaviour is selected. The selected behaviors is carried out by the Act method inside senseAndAct.

## Why the hierarchy is appropriate for the project

The chosen hierarchy is a successful representation of the priorities of the agent. In order: the agent preserves itself, completes tasks, disposes the litter once enough tasks have been completed, and moves if there is nothing to do in the current area.

The chosen task is always the one that will allow the agent to perform the greatest amount of points in the shortest amount of time. This could be improved by a more far-sighted approach, but it would be less responsive to newly spawned tasks, exponentially more computationally expensive, and more time consuming to program. Instead, this simple approach is easy to debug, easy for a human to understand, and gives more time for perfecting and tuning the algorithms.

This approach has the additional advantage that after some updates it can become the perfect starting point to implement multiple agents in the next project, which will be more computationally expensive.
As will be explained in question 7 the agent is likely to complete a cluster of tasks before moving to the next cluster. If every high value task was surrounded by many low value tasks, the agent would not understand that it has to change cluster if the latter is too far away. Also, the agent exploits the fact that points are awarded only at task completion. If points were awarded for partial completion, the agent would have to be changed so it does not avoid partial completion.

## Mapping

The agent does have an internal representation of the environment. The class that manages this internal representation is ExploredMap. The method updateMap adds the current view to a hash map with Point as key and Cell as value. This allows to add new tasks and unexplored regions to the map. updateMap is called at every timestep inside the senseAndAct method. The map allows the agent to know the location of the nearest explored recharge station at all times, even if it is outside its field of view. In the next coursework it might be used to communicate to other agents the location of clusters of tasks.
