package uk.ac.nott.cs.g53dia.library;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * A default implementation of the LitterAgentViewerIconFactory interface.
 * 
 * @author Neil Madden
 */

/*
 * Copyright (c) 2009 Neil Madden. Copyright (c) 2010 University of Nottingham.
 * 
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */

public class DefaultLitterAgentViewerIconFactory implements LitterAgentViewerIconFactory {
	static ImageIcon agentIcon;
	static ImageIcon rechargepointIcon;
	static ImageIcon recyclingstationIcon;
	static ImageIcon wastestationIcon;
	static ImageIcon recyclingbinIcon;
	static ImageIcon recyclingbinWithTaskIcon;
	static ImageIcon wastebinIcon;
	static ImageIcon wastebinWithTaskIcon;


	static {
		// Pre-load the images
		agentIcon = createImageIcon("images/agent.png");
		rechargepointIcon = createImageIcon("images/rechargepoint.png");
		recyclingstationIcon = createImageIcon("images/recyclingstation.png");
		wastestationIcon = createImageIcon("images/wastestation.png");
		recyclingbinIcon = createImageIcon("images/recyclingbin.png");
		recyclingbinWithTaskIcon = createImageIcon("images/recyclingbin_withtask.png");
		wastebinIcon = createImageIcon("images/wastebin.png");
		wastebinWithTaskIcon = createImageIcon("images/wastebin_withtask.png");
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL img = DefaultLitterAgentViewerIconFactory.class.getResource(path);
		if (img != null) {
			return new ImageIcon(img);
		} else {
			System.err.println("Couldn't load image: " + path);
			return null;
		}
	}

	public Icon getIconForCell(Cell cell) {
		if (cell == null) {
			return null;
		} else if (cell instanceof RechargePoint) {
			return rechargepointIcon;
		} else if (cell instanceof RecyclingStation) {
			return recyclingstationIcon;
		} else if (cell instanceof WasteStation) {
			return wastestationIcon;
		} else if (cell instanceof RecyclingBin) {
			if (((RecyclingBin) cell).getTask() == null) {
				return recyclingbinIcon;
			} else {
				return recyclingbinWithTaskIcon;
			}
		} else if (cell instanceof WasteBin) {
			if (((WasteBin) cell).getTask() == null) {
				return wastebinIcon;
			} else {
				return wastebinWithTaskIcon;
			}
		} else {
			return null;
		}
	}

	public Icon getIconForLitterAgent(LitterAgent litterAgent) {
		return agentIcon;
	}

}
