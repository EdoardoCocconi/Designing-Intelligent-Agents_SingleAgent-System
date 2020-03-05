package uk.ac.nott.cs.g53dia.library;

import java.util.Random;

/**
 * A default CellFactory which populates the environment with litter bins, stations 
 * and recharge points.
 * 
 * @author Neil Madden
 */

/*
 * Copyright (c) 2005 Neil Madden. Copyright (c) 2010 University of Nottingham.
 * Copyright (c) 2011 Julian Zappala (jxz@cs.nott.ac.uk)
 * 
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public class DefaultCellFactory implements CellFactory {
	/**
	 * Default litter bin density
	 */
	final static double DEFAULT_BIN_DENSITY = 0.008;

	/**
	 * Default station density.
	 */
	final static double DEFAULT_STATION_DENSITY = 0.004;

	/**
	 * Default proportion of litter bins that hold waste and stations that accept waste
	 */
	final static double DEFAULT_WASTE_RATIO = 0.5;

	/**
	 * Default recharge point density
	 */
	final static double DEFAULT_RECHARGE_DENSITY = 0.0004;

	/**
	 * Random number generator used to determine the contents of each cell, and
	 * when a litterBin generates a task.
	 */
	Random r;

	/**
	 * Create a DefaultCellFactory with a specified random number generator.	 * 
	 * @param r
	 *            random number generator
	 */
	public DefaultCellFactory(Random r) {
		this.r = r;
	}

	/**
	 * Create a DefaultCellFactory.
	 * 
	 */
	public DefaultCellFactory() {
		this.r = new Random();
	}

	/**
	 * Create new cells and wells in the environment.
	 * 
	 * @param env
	 *            environment to which the cell is to be added
	 * @param pos
	 *            position of the new cell
	 */

	public void generateCell(Environment env, Point pos) {
		if (pos.x == 0 & pos.y == 0) {
			env.putCell(new RechargePoint(pos));
		} else if (r.nextDouble() < DEFAULT_RECHARGE_DENSITY) {
			env.putCell(new RechargePoint(pos));
		} else if (r.nextDouble() < DEFAULT_STATION_DENSITY) {
			if (r.nextDouble() < DEFAULT_WASTE_RATIO) {
				env.putCell(new WasteStation(pos));
			} else {
				env.putCell(new RecyclingStation(pos));
			}
		} else if (r.nextDouble() < DEFAULT_BIN_DENSITY) {
			if (r.nextDouble() < DEFAULT_WASTE_RATIO) {
				env.putCell(new WasteBin(pos, r));
			} else {
				env.putCell(new RecyclingBin(pos, r));
			}
			env.litterBins.add((LitterBin) env.getCell(pos));
		} else {
			env.putCell(new EmptyCell(pos));
		}
	}
}
