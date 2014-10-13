package ee.ut.math.tvt.PATS;

import org.apache.log4j.Logger;


public class Intro {
	
	static IntroUI intro;
	public static void main(String[] args) {  
		final Logger LOGGER = Logger.getLogger(Intro.class);
		LOGGER.info("application start");    
		
		
		intro = new IntroUI ("Intro Window");
		intro.start();
	
	}
	
}
