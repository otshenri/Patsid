package ee.ut.math.tvt.PATS;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class Intro {

	static IntroUI intro;

	public static void main(String[] args) {
		final Logger LOGGER = Logger.getLogger(Intro.class);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss")
				.format(Calendar.getInstance().getTime());

		LOGGER.info("Class Intro start -" + timeStamp);

		intro = new IntroUI("Intro Window");
		intro.start();

	}

}
