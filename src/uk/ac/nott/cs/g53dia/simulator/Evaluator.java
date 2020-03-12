// Written by Haoyang Wang
// Modified by nza, bsl, Simon Castle-Green

package uk.ac.nott.cs.g53dia.simulator;

import uk.ac.nott.cs.g53dia.library.*;
import uk.ac.nott.cs.g53dia.agent.*;
import java.text.*;
import java.util.Random;

public class Evaluator {

    private static int DURATION = 10000;
    private static int NRUNS = 10;
    private static int SEED = 0;

    public static void main(String[] args) {

	long score = 0;
	DecimalFormat df = new DecimalFormat("0.000E00");

	// run the agent for NRUNS times and compute the average score
	for (int i = 0; i < NRUNS; i++) {
		String error = "";
	    Random r = new Random((long) i + SEED);
	    // Create an environment
	    Environment env = new Environment(LitterAgent.MAX_CHARGE/2, r);
		// Create an agent
		LitterAgent agt = new DemoLitterAgent(r);
		// Start executing the agent
		run:
		while (env.getTimestep() < DURATION) {
			// Advance the environment timestep
			env.tick();
			// Get the current view of the agent
			Cell[][] view = env.getView(agt.getPosition(), LitterAgent.VIEW_RANGE);
			// Let the agent choose an action
			Action act = agt.senseAndAct(view, env.getTimestep());
			// Try to execute the action
			try {
				act.execute(env, agt);
			} catch (OutOfBatteryException obe) {
				error = " " + obe.getMessage() + " at timestep " + env.getTimestep();
				break run;
			} catch (IllegalActionException ile) {
				System.err.println(ile.getMessage());
			} catch (Exception e) {
				error = " " + e.getMessage() + " at timestep " + env.getTimestep();
				break run;
			}
		}	
	    System.out.println("Run: " + i + "score: " + df.format(agt.getScore()) + error);
	    score = score + agt.getScore();
	}
	System.out.println("\nTotal average score: " + df.format(score / NRUNS));
    }
}
