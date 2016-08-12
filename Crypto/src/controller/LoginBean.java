package controller;

import model.*;

public class LoginBean {

	private MongoDBClient mongoDBClient =  null;
	private String username = null;
	private String password = null;
	
	public LoginBean () {
	}
	
	public String queryDBAndLogin() {
		mongoDBClient = new MongoDBClient(username, password);
		UserDataStructure userDataStructure = null;
		try {
			userDataStructure = mongoDBClient.findFirstOccurrenceByUsername(username);
		} catch (DataNotFoundException e) {
			return "nousername";
		}
		
		if(userDataStructure.password.equals(password))
			return "success";
		
		return "invalidpassword";
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
