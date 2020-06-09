package org.mydigitalschool.user;

public class User {

	private String name;
	private String key;
	
	public User() {
		
	}
	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
