package ee.ut.math.tvt.PATS;

import java.awt.*;  

import javax.swing.*;   

// Create a simple GUI window 
public class IntroUI { 
	
	String name;
	
	public IntroUI(String name) {
		super();
		this.name = name;
	}

	protected void start() {        
		//Create and set up the window.        
		JFrame frame = new JFrame("Simple GUI");
		frame.setTitle(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
		JLabel textLabel = new JLabel("I'm a label in the window",SwingConstants.CENTER);        
		
		textLabel.setPreferredSize(new Dimension(300, 100));        
		frame.getContentPane().add(textLabel, BorderLayout.CENTER);         
		//Display the window.        
		frame.setLocationRelativeTo(null);        
		frame.pack();       
		frame.setVisible(true); 
	}     
	 
}
