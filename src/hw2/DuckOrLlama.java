package hw2;

import javax.swing.*;

/**
 * Author: Ahmet Sen 2-13-2014
 */

/*
 * This class has the main method and starts the program execute
 */
public class DuckOrLlama extends JFrame {
	private static final long serialVersionUID = 1L;

	// Class constructor creates the frame and sets the frame main operations.
	public DuckOrLlama() {
		super("Llama/Duck Game");

		// create the ButtonDemoPanel and add it to the frame
		this.add(new ButtonDemoPanel());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Display the window.
		this.setSize(600, 600);
		// this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new DuckOrLlama();

	}
}
    



