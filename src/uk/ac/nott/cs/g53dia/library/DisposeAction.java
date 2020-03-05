package uk.ac.nott.cs.g53dia.library;

/**
 * Action that disposes of litter at a station.
 *
 * @author Neil Madden
 */

/*
 * Copyright (c) 2011 Julian Zapppala (jxz@cs.nott.ac.uk)
 * 
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public class DisposeAction implements Action {

	public DisposeAction() {
	}

	public void execute(Environment env, LitterAgent agt) throws IllegalActionException {

		if (agt.wasteLevel > 0) {
			if (!(env.getCell(agt.getPosition()) instanceof WasteStation)) {
				throw new IllegalActionException("DisposeAction: not at a waste station");
			} else {
				agt.wasteLevel = 0;
			}
		} else if (agt.recyclingLevel > 0) {
			if (!(env.getCell(agt.getPosition()) instanceof RecyclingStation)) {
				throw new IllegalActionException("DisposeAction: not at a recycling station");
			} else {
				agt.recyclingLevel = 0;
			}	
		} else {
			throw new IllegalActionException("DisposeAction: no litter to dispose of");
		}

	}

	public String toString() {
		return "DisposeAction";
	}
}
