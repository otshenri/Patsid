package ee.ut.math.tvt.PATS;

import java.awt.*;  
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Properties;

import javax.swing.*;   

// Create a simple GUI window 
public class IntroUI { 
	
	 String name;
	 String member1;
	 String member2;
	 String member3;
	 String teamname;
	 String leader;
	 String leadermail;
	 String version;
	
	public IntroUI(String name) {
		super();
		this.name = name;
	}
	
	
		 
		public void getPropValues() throws IOException {
	 
			
			Properties prop = new Properties();
			String propFileName = "application.properties";
	 
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
	 
			
	 
			// get the property value and save it to strings
			
			member1 = prop.getProperty("team.member1");
			member2 = prop.getProperty("team.member2");
			member3 = prop.getProperty("team.member3");
			teamname = prop.getProperty("team.name");
			leader = prop.getProperty("team.leader");;
			leadermail= prop.getProperty("team.leaderemail");;
			propFileName = "version.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
			version = prop.getProperty("build.number");
			
			
	 
			
		}
	

	protected void start() {  
		try {
			getPropValues();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Create and set up the window.        
		JFrame frame = new JFrame("teamname");
		frame.setTitle("Intro");
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		JLabel textlabel2 = new JLabel("Team: "+teamname);
		JLabel textlabel1 = new JLabel("Members: "+member1+", "+member2+", "+member3);      
		JLabel textlabel3 = new JLabel("Leader: " + leader+" ("+leadermail+")");
		JLabel textlabel4 = new JLabel("Version: "+version);
		ImageIcon image = new ImageIcon("res/logo.jpg");
		JLabel imageLabel = new JLabel(image);
		imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		textlabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		textlabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		textlabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
		textlabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
		Container konteiner = frame.getContentPane();
		konteiner.setLayout(new BoxLayout(konteiner, BoxLayout.PAGE_AXIS));
		konteiner.add(imageLabel);
		konteiner.add(textlabel2);
		konteiner.add(textlabel1);
		konteiner.add(textlabel3);
		konteiner.add(textlabel4);
		
		
		
		//frame.getContentPane().add(imageLabel, BorderLayout.CENTER);         
		//Display the window.        
		frame.setLocationRelativeTo(null);        
		frame.pack();       
		frame.setVisible(true); 
	}     
	 
}
