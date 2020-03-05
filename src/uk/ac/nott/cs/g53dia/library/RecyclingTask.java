package uk.ac.nott.cs.g53dia.library;

import java.util.Random;

/**
 * A class representing a recycling disposal task
 *
 * @author Julian Zappala
 */

/*
 * Copyright (c) 2011 Julian Zappala (jxz@cs.nott.ac.uk)
 * 
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public class RecyclingTask extends Task {

	RecyclingTask(RecyclingBin b, Random r) {
		this.litterBin = b;
		amount = r.nextInt(MAX_AMOUNT) + 1;
	}

}
