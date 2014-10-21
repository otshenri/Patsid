package ee.ut.math.tvt.PATS;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.ui.ConsoleUI;
import ee.ut.math.tvt.salessystem.ui.SalesSystemUI;

public class Intro {

	static IntroUI intro;
	private static final Logger log = Logger.getLogger(Intro.class);
	private static final String MODE = "console";

	public static void main(String[] args) {
		final Logger LOGGER = Logger.getLogger(Intro.class);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss")
				.format(Calendar.getInstance().getTime());

		LOGGER.info("Class Intro start -" + timeStamp);
		final SalesDomainController domainController = new SalesDomainControllerImpl();

		if (args.length == 1 && args[0].equals(MODE)) {
			log.debug("Mode: " + MODE);

			ConsoleUI cui = new ConsoleUI(domainController);
			cui.run();
		} else {

			IntroUI introUI = new IntroUI("FEGIT");
			introUI.start();
			introUI.setVisible(true);
			introUI.setAlwaysOnTop(true);
			LOGGER.info("SUPERFEGIT");
			final SalesSystemUI ui = new SalesSystemUI(domainController);
			ui.setVisible(true);
			LOGGER.info("SUPERFEGIT2");
			introUI.setAlwaysOnTop(false);
			try {
				Thread.sleep(3000);
				LOGGER.info("sleep over");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			introUI.setVisible(false);
			ui.setVisible(true);
			LOGGER.info("LoPP");
		}



	}

}
