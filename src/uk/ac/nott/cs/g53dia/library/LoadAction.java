package uk.ac.nott.cs.g53dia.library;

import java.lang.Math;

/**
 * Action that loads litter into the agent from a litter bin.
 *
 * @author Julian Zappala
 */

/*
 * Copyright (c) 2011 Julian Zappala (jxz@cs.nott.ac.uk)
 * 
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public class LoadAction implements Action {

	Task task;
	int amount;

	public LoadAction(Task t) {
		task = t;
	}

	public void execute(Environment env, LitterAgent agt) throws IllegalActionException {

		if (!(agt.getPosition().equals(task.getPosition()))) {
			throw new IllegalActionException("LoadAction: not at litter bin");
		}

		if (task.isComplete()) {
			throw new IllegalActionException("LoadActuib: task already complete");
		}
		
		if (agt.getLitterLevel() >= LitterAgent.MAX_LITTER) {
			throw new IllegalActionException("LoadAction: litter storage is full");
		}

		if (task instanceof WasteTask) {
			if (agt.recyclingLevel > 0) {
				throw new IllegalActionException("LoadAction: cannot load waste while carrying recycling");
			}
			amount = Math.min(agt.getWasteCapacity(), task.getRemaining());
			agt.wasteLevel += amount;
			task.dispose(amount);
			
			if (task.isComplete()) {
				agt.wasteDisposed += task.amount;
			}
		} else {
			// this is a recycling task
			if (agt.wasteLevel > 0) {
				throw new IllegalActionException("LoadAction: cannot load recycling while carrying waste");
			}
			amount = Math.min(agt.getRecyclingCapacity(), task.getRemaining());
			agt.recyclingLevel += amount;
			task.dispose(amount);
			
			if (task.isComplete()) {
				agt.recyclingDisposed += task.amount;
			}
		}
	}

	public String toString() {
		return "LoadAction";
	}
}
