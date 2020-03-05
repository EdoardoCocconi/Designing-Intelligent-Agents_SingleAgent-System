package uk.ac.nott.cs.g53dia.library;

import java.lang.Math;

/**
 * A 2D position in the Environment.
 * 
 * @author Neil Madden
 */

/*
 * Copyright (c) 2005 Neil Madden.
 * Copyright (c) 2010 University of Nottingham.
 * 
 * See the file "license.terms" for information on usage and redistribution
 * of this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public class Point implements Cloneable {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public int distanceTo(Point p) {
    	return Math.max(Math.abs(x - p.getX()), Math.abs(y - p.getY()));
    }
   
    public boolean equals(Object o) {
        if (o == null) return false;
        Point p = (Point)o;
        return (p.getX() == x) && (p.getY() == y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Object clone() {
        return new Point(x,y);
    }

    /**
     * Override hashCode to make sure identical points produce identical
     * hashes.
     */
    public int hashCode() {
        return (((x & 0xff) << 16) + (y & 0xff));
    }
}

