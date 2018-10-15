package com.liyong.model;

public class User {

	private String id ;
	private String name ;
	private int valueNum;
	private String password;
	
	public User(String id,String name,int valueNum,String password){
		this.id = id;
		this.name = name ;
		this.valueNum = valueNum;
		this.password = password;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValueNum() {
		return valueNum;
	}
	public void setValueNum(int valueNum) {
		this.valueNum = valueNum;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
