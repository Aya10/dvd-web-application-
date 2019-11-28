package models;

public class User {

	String username;
	String Password;
	String api;

	public User(String username, String password, String api) {
		this.username = username;
		Password = password;
		this.api = api;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}
}
