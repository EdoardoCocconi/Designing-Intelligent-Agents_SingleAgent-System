package uk.ac.nott.cs.g53dia.library;

import java.awt.*;
import javax.swing.*;

/**
 * A simple user interface for watching an individual LitterAgent.
 *
 * @author Neil Madden.
 */
/*
 * Copyright (c) 2003 Stuart Reeves Copyright (c) 2003-2005 Neil Madden
 * (nem@cs.nott.ac.uk). Copyright (c) 2011 Julian Zappala (jxz@cs.nott.ac.uk).
 * See the file "license.terms" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */
public class LitterAgentViewer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2810783821678793885L;
	LitterAgentViewerIconFactory iconfactory;
	JLabel[][] cells;
	JLabel tstep, charge, pos, waste, disposed, score;
	LitterAgent agt;
	final static int SIZE = (LitterAgent.VIEW_RANGE * 2) + 1, ICON_SIZE = 25, PSIZE = SIZE * ICON_SIZE;

	public LitterAgentViewer(LitterAgent agt) {
		this(agt, new DefaultLitterAgentViewerIconFactory());
	}

	public LitterAgentViewer(LitterAgent agt, LitterAgentViewerIconFactory fac) {
		this.agt = agt;
		this.iconfactory = fac;
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		// Create the cell viewer
		cells = new JLabel[SIZE][SIZE];
		JLayeredPane lp = new JLayeredPane();
		JPanel p = new JPanel(new GridLayout(SIZE, SIZE));
		p.setBackground(Color.WHITE);

		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				cells[x][y] = new JLabel();
				p.add(cells[x][y]);
			}
		}

		lp.add(p, new Integer(0));
		p.setBounds(0, 0, PSIZE, PSIZE);
		// Create a LitterAgent label
		JLabel LitterAgentLabel = new JLabel(iconfactory.getIconForLitterAgent(agt));
		lp.add(LitterAgentLabel, new Integer(1)); // Add above the background
		lp.setSize(new Dimension(PSIZE, PSIZE));
		LitterAgentLabel.setBounds(PSIZE / 2 - ICON_SIZE / 2, PSIZE / 2 - ICON_SIZE / 2, ICON_SIZE, ICON_SIZE);
		c.add(lp, BorderLayout.CENTER);

		// Create some labels to show info about the LitterAgent and environment
		JPanel infop = new JPanel(new GridLayout(2, 4));
		infop.add(new JLabel("Timestep:"));
		tstep = new JLabel("0");
		infop.add(tstep);
		infop.add(new JLabel("Charge:"));
		charge = new JLabel("200");
		infop.add(charge);
		infop.add(new JLabel("Position:"));
		pos = new JLabel("(0,0)");
		infop.add(pos);
		infop.add(new JLabel("Score:"));
		score = new JLabel("0");
		infop.add(score);
		

		c.add(infop, BorderLayout.SOUTH);
		// infop.setPreferredSize(new Dimension(200,100));

		setSize(PSIZE, PSIZE + 50);
		setTitle("LitterAgent Viewer");
		setVisible(true);
	}

	public void setLitterAgent(LitterAgent agt) {
		this.agt = agt;
	}

	public void tick(Environment env) {
		Cell[][] view = env.getView(agt.getPosition(), LitterAgent.VIEW_RANGE);
		pos.setText(agt.getPosition().toString());
		tstep.setText(new String("" + env.getTimestep()));
		charge.setText(new String("" + agt.getChargeLevel()));
		score.setText("" + agt.getScore());
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				Icon cur = iconfactory.getIconForCell(view[x][y]);
				cells[x][y].setIcon(cur);
			}
		}
	}
}
