package model;

import java.text.*;
import java.util.Calendar;

public class EventLogger {
	private static EventLogger eventLoggerInstance = null;
	private MongoDBClient mongoDBClient = null;
	private LogStructure log = null;
	
	private EventLogger() {
		mongoDBClient = new MongoDBClient("log");
		log = new LogStructure();
	}
	
	public static EventLogger getInstance() {
		if(eventLoggerInstance == null)
			eventLoggerInstance = new EventLogger();
		return eventLoggerInstance;
	}
	
	public void logSuccessfulLoginEvent(String username) {
		setLogTimeAndUserAndClearMessage(username);
		log.message = "Successful login";
		mongoDBClient.insertLog(log);
	}
	
	private void setLogTimeAndUserAndClearMessage(String username) {
		log.username = username;
		log.logTime = getCurrentTimeString();
		log.message = "";
	}
	
	public void logNoSuchUserLoginEvent(String username) {
		setLogTimeAndUserAndClearMessage(username);
		log.message = "User " + username + " is not registered";
		mongoDBClient.insertLog(log);
	}

	public void logWrongPasswordLoginEvent(String username) {
		setLogTimeAndUserAndClearMessage(username);
		log.message = "Login attempt with wrong password";
		mongoDBClient.insertLog(log);
	}
	
	public void logEncryptEvent
	(String username, DataToBeEncryptedStructure dtbes) {
		
	}
	
	public void logDecryptEvent
	(String username, DataToBeDecryptedStructure dtbds) {
		
	}
	
	public void finalize() {
		if(mongoDBClient != null)
		{
			mongoDBClient.close();
			mongoDBClient = null;
		}
	}
	
	private String getCurrentTimeString() {
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String now = dateFormat.format(calendar.getTime());
		return now;
	}
}
