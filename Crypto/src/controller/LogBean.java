package controller;

import java.util.List;

import model.*;

public class LogBean {

	private MongoDBClient mongoDBClient =  null;
	private String username = null;
	private List<LogStructure> logList = null;
	
	public LogBean () {
	}
	
	public String retrieveLogs() {
		mongoDBClient = new MongoDBClient("log");
		EventLogger eventLogger = EventLogger.getInstance();
		setLogList(mongoDBClient.findLogs());
		
		closeAndFreeMongoDBClient();
		eventLogger.logLogRetrieval(username);
		return "success";
	}

	private void closeAndFreeMongoDBClient() {
		mongoDBClient.close();
		mongoDBClient = null;
	}
	
	public MongoDBClient getMongoDBClient() {
		return mongoDBClient;
	}

	public void setMongoDBClient(MongoDBClient mongoDBClient) {
		this.mongoDBClient = mongoDBClient;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<LogStructure> getLogList() {
		return logList;
	}

	public void setLogList(List<LogStructure> logList) {
		this.logList = logList;
	}
}
