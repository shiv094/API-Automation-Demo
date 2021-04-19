package com.apidemo.automation.module.user.services;

import java.util.HashMap;
import java.util.Map;

import com.apidemo.automation.framework.config.BaseClass;
import com.apidemo.automation.framework.config.Endpoint;
import com.apidemo.automation.framework.exceptions.APIVerificationException;
import com.apidemo.automation.framework.logging.LoggerHelper;
import com.apidemo.automation.framework.utils.RequestHelper;
import com.apidemo.automation.module.user.pojo.UserPojoHelper;

import io.restassured.response.Response;

public class UserServices {
	private static final LoggerHelper LOG = new LoggerHelper(UserServices.class);

	public Response createUser(String userName, String email, String gender, String status) {
		LOG.info("Creating user");
		String payload = createUserPayloadString(userName, email, gender, status);
		LOG.info("User Detail Payload : " + payload);
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Bearer " + BaseClass.accessToken);
		Response createUserResp = RequestHelper.post(Endpoint.CREATE_USER, headers, null, null, payload);
		LOG.info("Respone after user creation : " + createUserResp.body().asString());
		return createUserResp;
	}

	public String createUserPayloadString(String userName, String email, String gender, String status) {
		Map<String, String> userData = new HashMap<String, String>();
		userData.put("user", userName);
		userData.put("gender", gender);
		userData.put("email", email);
		userData.put("status", status);
		return new UserPojoHelper().createUserPayload(userData);
	}

	public void verifyUserCreation(Response createUserResp) {
		LOG.info("Verifying user created successfully");
		String respBody = createUserResp.body().asString();
		if (createUserResp.getStatusCode() != 200 && createUserResp.getStatusCode() != 201) {
			throw new APIVerificationException("User Creation Failed!!! Status Code : " + createUserResp.getStatusCode()
					+ " Response Body : " + respBody);
		}
		if (createUserResp.jsonPath().getInt("code") != 200 && createUserResp.jsonPath().getInt("code") != 201) {
			throw new APIVerificationException(
					"User Creation Failed!!! Status Code : " + createUserResp.jsonPath().getInt("code")
							+ "  Failed Message : " + createUserResp.jsonPath().getString("data.message"));
		}
	}

	public void verifyUserFetch(Response getUserResp) {
		LOG.info("Verifying user data fetched successfully");
		String respBody = getUserResp.body().asString();
		if (getUserResp.getStatusCode() != 200) {
			throw new APIVerificationException("User fetching Failed!!! Status Code : " + getUserResp.getStatusCode()
					+ " Response Body : " + respBody);
		}
		if (getUserResp.jsonPath().getInt("code") != 200) {
			throw new APIVerificationException(
					"User fetcing Failed!!! Status Code : " + getUserResp.jsonPath().getInt("code")
							+ "  Failed Message : " + getUserResp.jsonPath().getString("data.message"));
		}
	}

	public Response getAllUserDetails() {
		LOG.info("Fetching all user details from server");
		Response getUserResp = RequestHelper.get(Endpoint.GET_ALL_USER_DETAILS, null, null, null);
		LOG.info("Response after User details fetch : " + getUserResp.body().asString());
		return getUserResp;
	}

	public Response updateUserDetails(Integer userId, String userName, String email, String status) {
		LOG.info("Updaing existing user details");
		String payload = createUserPayloadString(userName, email, null, status);
		LOG.info("Updated User Detail Payload : " + payload);
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Bearer " + BaseClass.accessToken);
		Map<String, Object> pathParams = new HashMap<String, Object>();
		pathParams.put("users", userId);
		Response createUserResp = RequestHelper.put(Endpoint.UPDATE_USER, headers, null, pathParams, payload);
		LOG.info("Respone after user updation : " + createUserResp.body().asString());
		return createUserResp;
	}

}
