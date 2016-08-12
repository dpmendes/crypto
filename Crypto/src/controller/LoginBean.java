package controller;

import model.*;

public class LoginBean {

	private MongoDBClient mongoDBClient =  null;
	private String username = null;
	private String password = null;
	
	public LoginBean () {
	}
	
	public String queryDBAndLogin() {
		mongoDBClient = new MongoDBClient("login");
		EventLogger eventLogger = EventLogger.getInstance();
		UserDataStructure userDataStructure = null;
		try {
			userDataStructure = mongoDBClient.findFirstOccurrenceByUsername(username);
		} catch (DataNotFoundException e) {
			closeAndFreeMongoDBClient();
			eventLogger.logNoSuchUserLoginEvent(username);
			return "nousername";
		}
		
		if(userDataStructure.password.equals(password))
		{
			closeAndFreeMongoDBClient();
			eventLogger.logSuccessfulLoginEvent(username);
			return "success";
		}
		
		closeAndFreeMongoDBClient();
		eventLogger.logWrongPasswordLoginEvent(username);
		return "invalidpassword";
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
