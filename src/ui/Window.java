package ui;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import engine.Game;

public class Window extends Canvas{
	JFrame frame;
	public Window(int width, int height, String title, Game game) {
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}

	public void changeTitle(String title) {
		frame.setTitle(title);
	}
	
}
//final version