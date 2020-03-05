package uk.ac.nott.cs.g53dia.library;

/**
 * Action which replenishes the charge in the tanker.
 *
 * @author Julian Zappala
 */

/*
 * Copyright (c) 2011 Julian Zappala (jxz@cs.nott.ac.uk)
 * 
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public class RechargeAction implements Action {

	public RechargeAction() {
	}

	public void execute(Environment env, LitterAgent agt) throws IllegalActionException {

		if (!(env.getCell(agt.getPosition()) instanceof RechargePoint)) {
			throw new IllegalActionException("RechargeAction: not at recharge point");
		}

		if (agt.chargeLevel >= LitterAgent.MAX_CHARGE) {
			// Not critical, but useful for debugging
			throw new IllegalActionException("RechargeAction: battery is fully charged");
		}

		agt.chargeLevel = LitterAgent.MAX_CHARGE;
	}

	public String toString() {
		return "RechargeAction";
	}
}
