package uk.ac.nott.cs.g53dia.library;

import java.util.Random;

/**
 * An environment cell which contains a litterBin (source of tasks).
 * 
 * @author Julian Zappala
 */

/*
 * Copyright (c) 2010 Julian Zappala (jxz@cs.nott.ac.uk)
 * 
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public abstract class LitterBin extends DefaultCell {

	final static double NEW_TASK_PROBABILITY = 0.001;
	protected Task task;
	protected Random r;

	LitterBin(Point pos) {
		super(pos);
		this.r = new Random();
	}

	LitterBin(Point pos, Random r) {
		super(pos);
		this.r = r;
	}

	protected abstract void generateTask();

	public abstract Task getTask();

	protected void removeTask() {
		this.task = null;
	}

	public boolean equals(Object o) {
		LitterBin s = (LitterBin) o;
		if (this.getPoint().equals(s.getPoint())) {
			return true;
		} else {
			return false;
		}
	}
}
