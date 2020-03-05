package uk.ac.nott.cs.g53dia.library;

import java.util.Random;

/**
 * An abstract base class for Tankers in the standard {@link Environment}.
 *
 * @author Julian Zappala
 */

/*
 * Copyright (c) 2003 Stuart Reeves Copyright (c) 2003-2005 Neil Madden
 * (nem@cs.nott.ac.uk). Copyright (c) 2011 Julian Zappala (jxz@cs.nott.ac.uk).
 * 
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public abstract class LitterAgent {
	// Fields used by the environment
	/**
	 * The initial battery level of the agent.
	 */
	int chargeLevel = MAX_CHARGE;

	/**
	 * The initial amount of waste carried by the agent
	 */
	int wasteLevel = 0;

	/**
	 * The total amount of waste disposed of
	 */
	int wasteDisposed = 0;
	
	/**
	 * The initial amount of recycling carried by the agent
	 */
	int recyclingLevel = 0;

	/**
	 * The total amount of recycling disposed of
	 */
	int recyclingDisposed = 0;


	/**
	 * The maximum amount of battery charge an agent can have. Note: this is assumed to be
	 * an even number.
	 */
	public final static int MAX_CHARGE = 500;

	/**
	 * The maximum amount of waste or recycling an agent can carry.
	 */
	public final static int MAX_LITTER = 200;

	/**
	 * The distance an agent can "see".
	 */
	public final static int VIEW_RANGE = 30;

	/**
	 * Location of central recharge point
	 */
	public final static Point RECHARGE_POINT_LOCATION = new Point(0, 0);

	/**
	 * The agent's current position in the environment.
	 */
	Point position = new Point(0, 0); // Default to origin

	/**
	 * Random number generator
	 */
	protected Random r;

	
	/**
	 * Sub-classes must implement this method to provide the "brains" for the LitterAgent.
	 * 
	 * @param view
	 *            The cells the agent can currently see.
	 * 	 * @param timestep
	 *            The current timestep.
	 * @return an action to perform
	 */
	public abstract Action senseAndAct(Cell[][] view, long timestep);

	/**
	 * Get the LitterAgent's current position in the environment.
	 */
	public Point getPosition() {
		return (Point) position.clone();
	}

	/**
	 * Get the cell currently occupied by the LitterAgent.
	 * 
	 * @param view
	 *            the cells the LitterAgent can currently see
	 * @return a reference to the cell currently occupied by this LitterAgent
	 */
	public Cell getCurrentCell(Cell[][] view) {
		return view[VIEW_RANGE][VIEW_RANGE];
	}

	/**
	 * Use charge - used by move actions/
	 */
	void useCharge(int c) throws IllegalActionException {
		if (chargeLevel <= c) {
			throw new OutOfBatteryException("LitterAgent: insufficient charge");
		} else {
			chargeLevel -= c;
		}
	}

	/**
	 * How much charge does this agent have?
	 */
	public int getChargeLevel() {
		return chargeLevel;
	}

	/**
	 * The amount of litter the the agent is currently carrying.
	 * 
	 */
	public int getLitterLevel() {
		return wasteLevel + recyclingLevel;
	}

	/**
	 * The amount of waste the the agent is currently carrying.
	 * 
	 */
	public int getWasteLevel() {
		return wasteLevel;
	}

	/**
	 * The amount of additional waste the tanker can carry.
	 * 
	 */
	public int getWasteCapacity() {
		return MAX_LITTER - wasteLevel;
	}
	
	/**
	 * The amount of recycling the the agent is currently carrying.
	 * 
	 */
	public int getRecyclingLevel() {
		return recyclingLevel;
	}

	/**
	 * The amount of additional waste the tanker can carry.
	 * 
	 */
	public int getRecyclingCapacity() {
		return MAX_LITTER - recyclingLevel;
	}



	/**
	 * Get the agent's current score
	 * 
	 * @return the agent's score
	 */
	// This needs to be public to allow logging by the test harness
	public int getScore() {
		return wasteDisposed + recyclingDisposed;
	}

}
