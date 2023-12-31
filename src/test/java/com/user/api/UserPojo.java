package com.user.api;



import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class UserPojo {
	
	
	private Integer id;
	private String name;
	private String email;
	private String gender;
	private String status;
	
	public UserPojo(String name, String email, String gender, String status) {
		
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	
public UserPojo(Integer id ,String name, String email, String gender, String status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
}


