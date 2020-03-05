package uk.ac.nott.cs.g53dia.library;
/**
 * Interface implemented by all actions.
 *
 * <p>Each tanker is required to return a single instance of an implementation of this interface at each
 * cycle (via the {@link LitterAgent#senseAndAct senseAndAct} method of the {@link LitterAgent} class). This action contains
 * the actual code to allow the LitterAgent to do work.</p>
 *
 * @author Neil Madden.
 */
/*
 * Copyright (c) 2003 Stuart Reeves
 * Copyright (c) 2003-2005 Neil Madden (nem@cs.nott.ac.uk)
 * 
 * See the file "license.terms" for information on usage and redistribution
 * of this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */
public interface Action {

    /**
     * Execute the action for this LitterAgent in this Environment.
     * @throws IllegalActionException The action couldn't be performed.
     * @param litterAgent The LitterAgent trying to perform this action.
     * @param env The Environment that the LitterAgent inhabits.
     * @return Whether the action failed.
     */
    public abstract void execute(Environment env, LitterAgent agt) 
        throws IllegalActionException;
}

