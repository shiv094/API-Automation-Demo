package com.apidemo.automation.module.user.pojo;

import java.util.Map;

import com.google.gson.Gson;

public class UserPojoHelper {

	public String createUserPayload(Map<String, String> userData) {
		UserPojo createUser = new UserPojo();
		createUser.withName(userData.get("user"))
				  .withGender(userData.get("gender"))
				  .withEmail(userData.get("email"))
				  .withStatus(userData.get("status"));
		return new Gson().toJson(createUser);
	}
}
