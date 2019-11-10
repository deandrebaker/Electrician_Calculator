/***********************************************************
 * Author: Deandre Baker 567835
 * 
 * Assignment: Tech Cross Curricular
 * 
 * Description: An application for calculating various quantities relating to electrical circuits
 *
 * Date Start: 10/06/19
 * 
 * Date Completed: 17/06/19
 * 
 * Completion time: 5 periods
 * 
 * Honor Code: I pledge that this program represents my own program code. I
 * received help from no one in designing and debugging my program.
 **********************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// The main window which the application displays other JPanels and functions for the calculator
public class MainFrame extends JFrame implements ActionListener{
		
	JMenu units;
	JMenuItem save;

	public MainFrame(String title) {
		
		// Sets Window settings
		super.setTitle(title);
		setSize(300, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Creates the menu bar and adds the save and home menu items to the menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenuItem home = new JMenuItem("Home");
		JMenuItem saved = new JMenuItem("Saved");
		home.addActionListener(this);
		saved.addActionListener(this);		
		menuBar.add(home);		
		menuBar.add(saved);
		setJMenuBar(menuBar);
		
		// Creates the main panel component and adds it to the frame
		MainPanel mainPanel = new MainPanel(this);
		add(mainPanel);
		
		setVisible(true);
		
	}

	// Main method
	public static void main(String[] args) {
		
		// Initializes the calculation object and creates the MainFrame window
		new Calculate();
		MainFrame frame = new MainFrame("Electrician Calculators");	
		
	}
	
	// Runs actionPerformed method when an ActionEvent is created
	public void actionPerformed(ActionEvent evt) {
		
		
		String command = evt.getActionCommand();
		
		Component comp = getContentPane().getComponent(0);
		JMenuBar bar = getJMenuBar();		

		// Creates a new JMenuBar and adds the Home and Saved menu items to the menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenuItem home = new JMenuItem("Home");
		JMenuItem saved = new JMenuItem("Saved");
		home.addActionListener(this);
		saved.addActionListener(this);		
		menuBar.add(home);		
		menuBar.add(saved);
		setJMenuBar(menuBar);
		
		switch(command) {
		
		case "Home":
			
			// Removes the JPanel currently on the MainFrame and creates and adds a MainPanel object to the MainFrame
			remove(comp);
			MainPanel mainPanel = new MainPanel(this);
			add(mainPanel);
			break;
		
		case "Saved":
			
			// Removes the JPanel currently on the MainFrame and creates and adds a SavedPanel object to the MainFrame
			remove(comp);
			Saved savedPanel = new Saved(this);
			add(savedPanel);
			break;
			
		}
		
		setVisible(true);
		
	}
	
	
}
