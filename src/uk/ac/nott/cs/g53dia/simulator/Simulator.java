package uk.ac.nott.cs.g53dia.simulator;

import uk.ac.nott.cs.g53dia.agent.*;
import uk.ac.nott.cs.g53dia.library.*;
import java.util.Random;

/**
 * An example of how to simulate execution of a litter agent in the sample
 * (task) environment.
 * <p>
 * Creates a default {@link Environment}, a {@link DemoLitterAgent} and a GUI window
 * (a {@link LitterAgentViewer}) and executes the LitterAgent for DURATION days in the
 * environment.
 * 
 * @author Julian Zappala
 */

/*
 * Copyright (c) 2005 Neil Madden. Copyright (c) 2011 Julian Zappala
 * (jxz@cs.nott.ac.uk)
 * 
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public class Simulator {

	/**
	 * Time for which execution pauses so that GUI can update. Reducing this
	 * value causes the simulation to run faster.
	 */
	private static int DELAY = 100;

	/**
	 * Number of timesteps to execute.
	 */
	private static int DURATION = 10000;

	public static void main(String[] args) {
		// Note: to obtain reproducible behaviour, you can set the Random seed
		Random r = new Random();
		// Create an environment
		Environment env = new Environment(LitterAgent.MAX_CHARGE/2, r);
		// Create an agent
		LitterAgent agt = new DemoLitterAgent(r);
		// Create a GUI window to show the agent
		LitterAgentViewer tv = new LitterAgentViewer(agt);
		tv.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		// Start executing the agent
		while (env.getTimestep() < DURATION) {
			// Advance the environment timestep
			env.tick();
			// Update the GUI
			tv.tick(env);
			// Get the current view of the agent
			Cell[][] view = env.getView(agt.getPosition(), LitterAgent.VIEW_RANGE);
			// Let the agent choose an action
			Action act = agt.senseAndAct(view, env.getTimestep());
			// Try to execute the action
			try {
				act.execute(env, agt);
			} catch (OutOfBatteryException ofe) {
				System.err.println(ofe.getMessage());
				System.exit(-1);
			} catch (IllegalActionException afe) {
				System.err.println(afe.getMessage());
			}
			try {
				Thread.sleep(DELAY);
			} catch (Exception e) {
			}
		}
		System.out.println("Simulation completed at timestep " + env.getTimestep() + " , score: " + agt.getScore());
	}
}
