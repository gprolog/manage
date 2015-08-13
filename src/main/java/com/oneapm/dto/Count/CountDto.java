package com.oneapm.dto.Count;

public class CountDto {
	private String email;
	private int number;
	private long userId;
	private String id;
	private String event;
	public CountDto(String email, int number, long userId, String id,String event) {
		super();
		this.email = email;
		this.number = number;
		this.userId = userId;
		this.id = id;
		this.event = event;
	}
	public CountDto() {
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	
}
