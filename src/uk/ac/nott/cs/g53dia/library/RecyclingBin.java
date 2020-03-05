package uk.ac.nott.cs.g53dia.library;

import java.util.Random;

/**
 * An environment cell which contains a waste bin (source of tasks).
 * 
 * @author Julian Zappala
 */

/*
 * Copyright (c) 2010 Julian Zappala (jxz@cs.nott.ac.uk)
 * 
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public class RecyclingBin extends LitterBin implements Cloneable {

	RecyclingBin(Point pos) {
		super(pos);
	}

	RecyclingBin(Point pos, Random r) {
		super(pos, r);
	}

	protected void generateTask() {
		if (this.task == null) {
			if (r.nextDouble() < NEW_TASK_PROBABILITY) {
				this.task = new RecyclingTask(this, r);
			}
		}
	}

	public RecyclingTask getTask() {
		return (RecyclingTask) this.task;
	}

	protected RecyclingBin clone() {
		RecyclingBin b = new RecyclingBin(this.getPoint(), this.r);
		b.task = this.task;
		return b;
	}
	
}
