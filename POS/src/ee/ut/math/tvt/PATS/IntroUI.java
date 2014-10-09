package ee.ut.math.tvt.PATS;

import java.awt.*;  
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

import javax.swing.*;   

import org.omg.CORBA.portable.InputStream;

// Create a simple GUI window 
public class IntroUI { 
	
	String name;
	String member1;
	String member2;
	String member3;
	String teamname;
	String leader;
	String leadermail;
	
	public IntroUI(String name) {
		super();
		this.name = name;
	}
	
	
		 
		public void getPropValues() throws IOException {
	 
			String result = "";
			Properties prop = new Properties();
			String propFileName = "application.properties";
	 
			InputStream inputStream = (InputStream) getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
	 
			Date time = new Date(System.currentTimeMillis());
	 
			// get the property value and print it out
			
			String member1 = prop.getProperty("team.member1");
			String member2 = prop.getProperty("team.member2");
			String member3 = prop.getProperty("team.member3");
			String teamname = prop.getProperty("team.name");
			String leader = prop.getProperty("team.leader");;
			String leadermail= prop.getProperty("team.leaderemail");;
	 
			
		}
	

	protected void start() {  
		try {
			getPropValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Create and set up the window.        
		JFrame frame = new JFrame("Simple GUI");
		frame.setTitle(teamname);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
		JLabel textLabel = new JLabel("I'm a label in the window",SwingConstants.CENTER);        
		//label3 = new JLabel();
		textLabel.setPreferredSize(new Dimension(300, 100));        
		frame.getContentPane().add(textLabel, BorderLayout.CENTER);         
		//Display the window.        
		frame.setLocationRelativeTo(null);        
		frame.pack();       
		frame.setVisible(true); 
	}     
	 
}
