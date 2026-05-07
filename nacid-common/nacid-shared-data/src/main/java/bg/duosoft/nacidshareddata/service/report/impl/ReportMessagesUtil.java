package bg.duosoft.nacidshareddata.service.report.impl;

import bg.duosoft.nacidshareddata.exception.ReportException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by ISIS705 on 12/05/2015.
 */
public class ReportMessagesUtil {

	private static final String MESSAGES = "/messages_";
	private static final String PROPERTIES = ".properties";
	private static final String MESSAGES_NOLOCALE_PROPERTIES = "/messages.properties";

	public static Properties readMessages(String templatePath, String languageCode) {
		File messagesFile = resolveMessagesPropertiesFile(templatePath, languageCode);
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(new FileInputStream(messagesFile), "UTF8"));
		} catch (IOException e) {
			throw new ReportException("Error reading messages", e);
		}
		return properties;
	}

	private static File resolveMessagesPropertiesFile(String templatePath, String languageCode) {
		String messageFile = templatePath + MESSAGES + languageCode + PROPERTIES;
		String messageFileNoLocale = templatePath + MESSAGES_NOLOCALE_PROPERTIES;
		File propertyFile = new File(messageFile);

		if(!propertyFile.exists()){
			propertyFile = new File(messageFileNoLocale);
		}
		return propertyFile;
	}
}
