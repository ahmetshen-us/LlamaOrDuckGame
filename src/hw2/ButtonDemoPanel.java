package hw2;

//Import all the necessary libraries
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ButtonDemoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	// Create all the necessary variables then they will be initialized in the
	// constructor
	private JButton bDuck, bImage, bLlama, restart;
	private JLabel score, finale;
	private int clicked = 0;
	// This time can be changed for difficulty level purpose
	private int elapsedSeconds = 5;
	private Timer timer;
	private Clip clip;
	// Put all the images into the IMAGES array
	// Length is 14
	private ImageIcon[] IMAGES = {
			createImageIcon("duck.gif"),
			createImageIcon("duck1.jpg"), createImageIcon("duck_2.jpg"),
			createImageIcon("duck_3.jpg"), createImageIcon("duck4.jpg"),
			createImageIcon("duck5.jpg"), createImageIcon("duck6.jpg"),
			createImageIcon("lamma.gif"), createImageIcon("llama.jpg"),
			createImageIcon("lama_2.jpg"), createImageIcon("lama_3.jpg"),
			createImageIcon("lama4.jpg"), createImageIcon("lama5.jpg"),
			createImageIcon("lama6.jpg")
//			createImageIcon("llama.jpg")
			};

	/**
	 * ButtonDemoPanel Class constructor creates and defines the necessary
	 * graphics
	 */
	public ButtonDemoPanel() {

		bDuck = new JButton("Duck"); // create a button for duck
		bDuck.setMnemonic(KeyEvent.VK_D); // put a line under "D"

		bLlama = new JButton("Llama");// create a button for Llamma
		bLlama.setMnemonic(KeyEvent.VK_L);// put a line under "L"

		bImage = new JButton(IMAGES[(int) (Math.random() * (IMAGES.length-1))]);

		score = new JLabel("Score: " + clicked);
		finale = new JLabel("Click Duck for duck and Llama for Llama ");
		restart = new JButton("Restart");

		// Add Components to this container, using the BorderLayout
		this.setLayout(new BorderLayout());
		this.add(bImage, BorderLayout.CENTER); // put images to the center
		// Put the buttons to the south of BorderLayout
		JPanel south = new JPanel();
		this.add(south, BorderLayout.SOUTH);
		// Use the GridLayout in the south of BorderLayout
		south.setLayout(new GridLayout(2, 2));
		// Add the buttons to the panel
		south.add(bDuck);
		south.add(bLlama);
		south.add(score);
		south.add(restart);
		playSound();
		// Disable the restart button
		restart.setEnabled(false);
		// Show the directions and final score in the north of BorderLayout
		JPanel north = new JPanel();
		this.add(north, BorderLayout.NORTH);
		north.add(finale);

		// Create an action listener object to be used in Timer.

		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// print the elapsedSeconds to see time left if required
				// System.out.println(elapsedSeconds);
				elapsedSeconds--;
				if (elapsedSeconds == 0) {
					// finish the game if time is up
					gameOver();

				}
			}

		};

		timer = new Timer(1000, action);
		timer.start();
		bDuck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isDuck()) {
					// if the image is duck when Duck button is pressed call
					// update() method
					update();

				} else if (!isDuck()) {
					// if the image is duck when Duck button is pressed finish
					// game
					gameOver();

				}
			}
		});
		bLlama.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isDuck()) {
					// if the image is not duck when Llama button is pressed
					// call
					// update() method

					update();

				} else if (isDuck()) {
					// if the image is duck when Llama button is pressed finish
					// game
					gameOver();
				}
			}
		});

	}

	public void gameOver() {
		// enable the restart button when game is over
		restart.setEnabled(true);
		// disable both Llama and Duck buttons when game is over
		bLlama.setEnabled(false);
		bDuck.setEnabled(false);
		// Stop the music also the timer
		clip.stop();
		timer.stop();
		// Show final score to the player
		finale.setText("YOUR FINAL SCORE is Score :" + clicked);
		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clip.stop();
				playSound();
				clicked = 0;
				score.setText("Score: " + clicked);
				elapsedSeconds = 5;
				// Put a random image
				bImage.setIcon(IMAGES[(int) (Math.random() * (IMAGES.length-1))]);
				// start timer
				timer.start();
				// Llama and Duck buttons are enable when game restarts and
				// restart button is disabled
				bLlama.setEnabled(true);
				bDuck.setEnabled(true);
				restart.setEnabled(false);
				// Show the instructions to play the game
				finale.setText("Click Duck for duck and Llama for Llama ");

			}
		});
	}

	/*
	 * This method plays song when game is in action and returns nothing and
	 * does not take any parameters
	 */
	public void playSound() {

		try {
			// Open an audio input stream.
			File soundFile = new File(
					"src/song.wav");
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.out.println("Problem playing file ");
			System.out.println(e);
		}
	}

	/*
	 * This method check if the image is duck or not and does not take any
	 * parameters
	 * 
	 * @return true if the image is Duck otherwise false
	 */
	public boolean isDuck() {
		if ((bImage.getIcon() == IMAGES[0] || bImage.getIcon() == IMAGES[1]
				|| bImage.getIcon() == IMAGES[2]
				|| bImage.getIcon() == IMAGES[3]
				|| bImage.getIcon() == IMAGES[4]
				|| bImage.getIcon() == IMAGES[5] || bImage.getIcon() == IMAGES[6])
				&& elapsedSeconds > 0) {
			return true;
		} else if ((bImage.getIcon() == IMAGES[7]
				|| bImage.getIcon() == IMAGES[8]
				|| bImage.getIcon() == IMAGES[9]
				|| bImage.getIcon() == IMAGES[10]
				|| bImage.getIcon() == IMAGES[11]
				|| bImage.getIcon() == IMAGES[12] || bImage.getIcon() == IMAGES[13])
				&& elapsedSeconds > 0) {
			return false;
		}
		return false;
	}

	/*
	 * This method updates clicked times, elapsedSeconds, and score text; also
	 * changes the image
	 */
	public void update() {
		clicked++;
		elapsedSeconds = 5;
		score.setText("Score: " + clicked);
		bImage.setIcon(IMAGES[(int) (Math.random() * 13)]);
	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid. A good
	 * encapsulation for the validation and retrival of the image file. error
	 */
	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ButtonDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
