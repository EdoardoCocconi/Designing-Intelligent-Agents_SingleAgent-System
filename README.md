![Nottingham University](Assets/University.png)

# Designing Intelligent Agents: Single Agent System

<br>

## Getting Started

The java source code of the agent can be found in the following folder:

The source code of the environment in which the agent operates can be found in the following folder:

To see the agent in action, run the file:

To measure the agent performance over 10 runs, run the file:

<br>

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

<br>

## Why the hierarchy is appropriate for the project

The chosen hierarchy is a successful representation of the priorities of the agent. In order: the agent preserves itself, completes tasks, disposes the litter once enough tasks have been completed, and moves if there is nothing to do in the current area.

The chosen task is always the one that will allow the agent to perform the greatest amount of points in the shortest amount of time. This could be improved by a more far-sighted approach, but it would be less responsive to newly spawned tasks, exponentially more computationally expensive, and more time consuming to program. Instead, this simple approach is easy to debug, easy for a human to understand, and gives more time for perfecting and tuning the algorithms.

This approach has the additional advantage that after some updates it can become the perfect starting point to implement multiple agents in the next project, which will be more computationally expensive.
As will be explained in question 7 the agent is likely to complete a cluster of tasks before moving to the next cluster. If every high value task was surrounded by many low value tasks, the agent would not understand that it has to change cluster if the latter is too far away. Also, the agent exploits the fact that points are awarded only at task completion. If points were awarded for partial completion, the agent would have to be changed so it does not avoid partial completion.

<br>

## Mapping

The agent does have an internal representation of the environment. The class that manages this internal representation is ExploredMap. The method updateMap adds the current view to a hash map with Point as key and Cell as value. This allows to add new tasks and unexplored regions to the map. updateMap is called at every timestep inside the senseAndAct method. The map allows the agent to know the location of the nearest explored recharge station at all times, even if it is outside its field of view. In the next coursework it might be used to communicate to other agents the location of clusters of tasks.

<br>

## Recharge

- The agent immediately goes to recharge if moving by one more cell would make it impossible to reach the closest recharge point known to the agent.
-	The agent immediately goes to recharge if the battery is between 90% and 50% and the distance to the recharge station is 3 or less than 3.
-	The agent immediately goes to recharge if the battery is below 50% and the distance to the recharge station is 4 or less than 4. The battery will never arrive to 0% due to the first condition.

The agent does not recharge if the battery can last until the end of the simulation without doing so. This allows to maximize the total number of points.

This is implemented in the isRechargeInRange method in the RechargeDetector class. The method outputs TRUE if a RechargeStation is present in the respective radius.

<br>

## Explore

The initial intention of the coursework was to explore the environment first, so more tasks start spawning, and the agent can record the position of the bins. The agent would have moved inside the explored environment where the probability of a task being present is higher. However, this initial exploration either produces an environment that is too small or wastes to many timesteps.

In the final version of the coursework, the exploration is only triggered when there is nothing else to do. During the exploration, the agent moves away from the origin if its distance from the origin is less than 30, and it gets closer to the origin when it is greater or equal to 30. The areas close to the origin have a higher probability to be already explored and therefore have a higher probability to contain more tasks over time.

This is implemented in the ExploreBehaviour class.

<br>

## Choosing the bin

The agent finds the task with the highest litter over distance ratio in the specified field of view. The field of view is inversely proportional to the current litter capacity of the agent. If the agent is 90% full, the field of view is reduced by 90%. Therefore, the agent won’t look for high value tasks that are far away because the agent could not complete them anyways. Instead, the agent prefers to go to the tasks close to it that can be completed before going to the station. 

The bin with the highest litter over distance ratio is found by the readSensor method of the LitterDetector class. The readSensor method is called in the sense method in DemoLitterAgent. When the field of view is so small that the agent does not see any task, or when the agent is full, the CollectBehaviour does not run anymore.

<br>

## Chosing the station

The agent goes to the closest station. This allows it to complete the current cluster of tasks before going to the next one. This is implemented in the readSensor method in the StationDetector class.
