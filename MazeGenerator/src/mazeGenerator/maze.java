package mazeGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class maze {
	
	
	
	public static void main(String args[]) {
		
		JFrame frame = new JFrame("Maze");
		frame.setSize(new Dimension(800,800));
		frame.getContentPane().setBackground(new Color(255,255,255));
		
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());
		
		generate g = new generate();
		frame.add(g);
		frame.setVisible(true);
	}
	
}
