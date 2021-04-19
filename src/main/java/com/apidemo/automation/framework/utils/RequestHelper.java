package com.apidemo.automation.framework.utils;

import java.util.Map;
import java.util.Objects;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestHelper {

	static RequestSpecification httpRequest;

	private static void logAll(RequestSpecification httpRequest) {
		if (Boolean.valueOf(System.getProperty("detailedLog"))) {
			httpRequest.log().all();
		}
	}

	public static Response get(String endpoint, Map<String, String> headers, Map<String, Object> queryParam,
			Map<String, Object> pathParams) {
		httpRequest = RestAssured.given();
		if (endpoint.isEmpty()) {
			throw new IllegalArgumentException("Endpoint can't be empty or null");
		}

		if (Objects.nonNull(headers)) {
			httpRequest.headers(headers);
		}

		if (Objects.nonNull(queryParam)) {
			httpRequest.queryParams(queryParam);
		}
		if (Objects.nonNull(pathParams)) {
			httpRequest.pathParams(pathParams);
		}
		logAll(httpRequest);
		Response response = httpRequest.request(Method.GET, endpoint);
		return response;
	}

	public static Response put(String endpoint, Map<String, String> headers, Map<String, Object> queryParam,
			Map<String, Object> pathParams, Object payload) {
		httpRequest = RestAssured.given();
		if (endpoint.isEmpty()) {
			throw new IllegalArgumentException("Endpoint can't be empty or null");
		}
		if (Objects.nonNull(headers)) {
			httpRequest.headers(headers);
		}
		if (Objects.nonNull(queryParam)) {
			httpRequest.queryParams(queryParam);
		}
		if (Objects.nonNull(pathParams)) {
			httpRequest.pathParams(pathParams);
		}
		if (Objects.nonNull(payload)) {
			httpRequest.body(payload);
		}
		logAll(httpRequest);
		Response response = httpRequest.request(Method.PUT, endpoint);
		return response;
	}

	public static Response post(String endpoint, Map<String, String> headers, Map<String, Object> queryParam,
			Map<String, Object> pathParams, Object payload) {
		httpRequest = RestAssured.given();
		if (endpoint.isEmpty()) {
			throw new IllegalArgumentException("Endpoint can't be empty or null");
		}
		if (Objects.nonNull(headers)) {
			httpRequest.headers(headers);
		}
		if (Objects.nonNull(queryParam)) {
			httpRequest.queryParams(queryParam);
		}
		if (Objects.nonNull(pathParams)) {
			httpRequest.pathParams(pathParams);
		}
		if (Objects.nonNull(payload)) {
			httpRequest.body(payload);
		}

		logAll(httpRequest);
		Response response = httpRequest.request(Method.POST, endpoint);
		return response;
	}

}
