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

public class WasteBin extends LitterBin implements Cloneable {

	WasteBin(Point pos) {
		super(pos);
	}

	WasteBin(Point pos, Random r) {
		super(pos, r);
	}

	protected void generateTask() {
		if (this.task == null) {
			if (r.nextDouble() < NEW_TASK_PROBABILITY) {
				this.task = new WasteTask(this, r);
			}
		}
	}

	public WasteTask getTask() {
		return (WasteTask) this.task;
	}

	protected WasteBin clone() {
		WasteBin b = new WasteBin(this.getPoint(), this.r);
		b.task = this.task;
		return b;
	}
	
}
