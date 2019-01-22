package tk.ddvudo.ssrdetection.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

public final class Global {

	static {
		try {
			ConfigurationSource source;
			String relativePath = "src" + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator") + "log4j2.xml";
			File log4jFile = new File( System.getProperty("user.dir") + System.getProperty("file.separator") + relativePath);
			if (log4jFile.exists()) {
				source = new ConfigurationSource(new FileInputStream(log4jFile), log4jFile);
				Configurator.initialize(null, source);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Global() {
	}

	public static Global getInstance() {
		return new Global();
	}

	public Logger getLogger() {
		return LogManager.getRootLogger();
	}
}
