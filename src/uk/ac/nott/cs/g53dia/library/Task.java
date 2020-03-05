package uk.ac.nott.cs.g53dia.library;

import java.util.Random;

/**
 * A class representing a waste disposal task
 *
 * @author Julian Zappala
 */

/*
 * Copyright (c) 2011 Julian Zappala (jxz@cs.nott.ac.uk)
 * 
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public abstract class Task {
	/**
	 * The maximum amount of litter that must be disposed of in a single task
	 */

	public static final int MAX_AMOUNT = LitterAgent.MAX_LITTER;

	LitterBin litterBin;
	int amount;
	int disposed = 0;
	boolean completed = false;

	/**
	 * Get the position of the litterBin from which the litter should be collected
	 * 
	 */

	public Point getPosition() {
		return litterBin.getPoint();
	}

	/**
	 * Get the amount of litter to be disposed of
	 * 
	 */

	public int getAmount() {
		return amount;
	}

	/**
	 * How much litter must be disposed of to complete the task?
	 * 
	 */
	public int getRemaining() {
		return amount - disposed;
	}

	/**
	 * Is this task completed?
	 * 
	 */
	public boolean isComplete() {
		return disposed >= amount;
	}

	protected void setAmount(int a) {
		this.amount = a;
	}

	protected void dispose(int d) {
		disposed += d;
		if (isComplete()) {
			this.litterBin.removeTask();
		}
	}
}
