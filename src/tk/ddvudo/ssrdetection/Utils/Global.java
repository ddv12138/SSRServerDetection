package tk.ddvudo.ssrdetection.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Global {

	private Global() {
	}

	public static Global getInstance() {
		return new Global();
	}

	public Logger getLogger() {
		return LogManager.getRootLogger();
	}
}
