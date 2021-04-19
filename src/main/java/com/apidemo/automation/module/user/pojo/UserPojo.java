package com.apidemo.automation.module.user.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class UserPojo {

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("gender")
	@Expose
	private String gender;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("status")
	@Expose
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserPojo withName(String name) {
		this.name = name;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserPojo withGender(String gender) {
		this.gender = gender;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserPojo withEmail(String email) {
		this.email = email;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserPojo withStatus(String status) {
		this.status = status;
		return this;
	}

}
