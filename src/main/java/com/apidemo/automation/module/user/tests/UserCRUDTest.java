package com.apidemo.automation.module.user.tests;

import org.testng.annotations.Test;

import com.apidemo.automation.framework.config.BaseClass;
import com.apidemo.automation.framework.config.Endpoint;
import com.apidemo.automation.framework.logging.LoggerHelper;
import com.apidemo.automation.framework.utils.DateTimeHelper;
import com.apidemo.automation.module.user.services.UserServices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserCRUDTest extends BaseClass {
	private static final LoggerHelper LOG = new LoggerHelper(UserCRUDTest.class);
	private UserServices userServices;

	@Test(priority = 1)
	public void test1GetFetchListOfUsers() {
		userServices = new UserServices();
		Response getUserResp = userServices.getAllUserDetails();
		userServices.verifyUserFetch(getUserResp);
	}

	@Test(priority = 4)
	public void test2PostCreateUser() {
		userServices = new UserServices();
		String date = DateTimeHelper.currentdate("dMMYYHHmmss");
		Response createUserResp = userServices.createUser("shiba" + date, "shiba" + date + "@gmail.com", "Male",
				"Active");
		userServices.verifyUserCreation(createUserResp);
	}

	@Test(priority = 3)
	public void test2PostCreateUser_BDD() {
		LOG.info("Creating user");
		userServices = new UserServices();
		/* payload creation */
		String date = DateTimeHelper.currentdate("dMMYYHHmmss");
		String payload = userServices.createUserPayloadString("sammy" + date, date + "@gmail.com", "Male", "Active");
		LOG.info("User Detail Payload : " + payload);
		/* create User API hit */
		LOG.info("Creating user");
		Response createUserResp = RestAssured
				.given()
				.auth().oauth2(accessToken).contentType(ContentType.JSON)
				.when()
				.body(payload).post(Endpoint.CREATE_USER)
				.then().extract().response();
		LOG.info("Create User Response : " + createUserResp.body().asString());
		userServices.verifyUserCreation(createUserResp);
	}

	@Test(priority = 2)
	public void test3Put_UpdateUser() {
		userServices = new UserServices();
		String date = DateTimeHelper.currentdate("dMMYYHHmmss");
		/* creating User */
		Response createUserResp = userServices.createUser("shiba" + date, "shiba" + date + "@gmail.com", "Male",
				"Active");
		int userId = createUserResp.jsonPath().getInt("data.id");
		userServices.verifyUserCreation(createUserResp);
		/* Updating the above created User */
		Response updatedUserResp = userServices.updateUserDetails(userId, "sammy_updated" + date,
				"sammy" + date + "@gmail.com", "Active");
		userServices.verifyUserCreation(updatedUserResp);
	}

}
