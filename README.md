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
