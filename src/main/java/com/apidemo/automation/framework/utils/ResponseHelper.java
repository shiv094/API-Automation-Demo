package com.apidemo.automation.framework.utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.apidemo.automation.framework.exceptions.JsonNodeNotFoundException;

import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;

public class ResponseHelper {

	private static Response response;

	/**
	 * Takes api response as argument.
	 * 
	 * @param resp response of api.
	 * @return Class Object
	 */
	public static ResponseHelper resp(Response resp) {
		response = resp;
		return new ResponseHelper();
	}

	private void throwJsonNodeNotFoundException(String jsonPath, Response response) {
		throw new JsonNodeNotFoundException("\"" + jsonPath + "\"" + " node not found in the response"
				+ "   Response : \n" + response.body().asString());
	}

	private <T> void throwJsonNodeNotFoundExceptionForNullValues(T value, String jsonPath, Response response) {
		throw new JsonNodeNotFoundException("Path doesn't exist in the response OR Value is Null for given FilePath : "
				+ jsonPath + " || Value : " + value + "  || Response : " + response.body().asString());
	}

	/**
	 * Get the result of an Object path expression as a string.
	 *
	 * @param path The Object path.
	 * @return The object matching the Object path. A
	 *         {@link java.lang.ClassCastException} will be thrown if the object
	 *         cannot be casted to the expected type.
	 */
	public String getString(String path) {
		String value = null;
		try {
			value = response.jsonPath().getString(path);
		} catch (NullPointerException | IllegalArgumentException | JsonPathException ne) {
			throwJsonNodeNotFoundException(path, response);
		}
		return value;
	}

	/**
	 * Get the result of an Object path expression as an int.
	 *
	 * @param path The Object path.
	 * @return The int matching the Object path. A
	 *         {@link java.lang.ClassCastException} will be thrown if the object
	 *         cannot be casted to the expected type.
	 */
	public int getInt(String path) {
		int value = 0;
		try {
			value = response.jsonPath().getInt(path);
		} catch (NullPointerException | IllegalArgumentException | JsonPathException ne) {
			throwJsonNodeNotFoundException(path, response);
		}
		return value;
	}

	public boolean getBoolean(String path) {
		boolean value = false;
		try {
			value = response.jsonPath().getBoolean(path);
		} catch (NullPointerException | IllegalArgumentException | JsonPathException ne) {
			throwJsonNodeNotFoundException(path, response);
		}
		return value;
	}

	public long getLong(String path) {
		long value = 0;
		try {
			value = response.jsonPath().getLong(path);
		} catch (NullPointerException | IllegalArgumentException | JsonPathException ne) {
			throwJsonNodeNotFoundException(path, response);
		}
		return value;
	}

	public <K, V> Map<K, V> getMap(String path) {
		Map<K, V> value = null;
		try {
			value = response.jsonPath().getMap(path);
		} catch (NullPointerException | IllegalArgumentException | JsonPathException ne) {
			throwJsonNodeNotFoundException(path, response);
		}
		return value;
	}

	public float getFloat(String path) {
		float value = 0;
		try {
			value = response.jsonPath().getFloat(path);
		} catch (NullPointerException | IllegalArgumentException | JsonPathException ne) {
			throwJsonNodeNotFoundException(path, response);
		}
		return value;
	}

	public double getDouble(String path) {
		double value = 0;
		try {
			value = response.jsonPath().getDouble(path);
		} catch (NullPointerException | IllegalArgumentException | JsonPathException ne) {
			throwJsonNodeNotFoundException(path, response);
		}
		return value;
	}

	public int getStatusCode() {
		return response.getStatusCode();
	}

	public <T> T get(String path) {
		T value = null;
		try {
			value = response.jsonPath().get(path);
			if (Objects.isNull(value)) {
				throwJsonNodeNotFoundExceptionForNullValues(value, path, response);
			}
		} catch (NullPointerException | IllegalArgumentException | JsonPathException ne) {
			throwJsonNodeNotFoundException(path, response);
		}
		return value;
	}

	public <T> List<T> getList(String path) {
		List<T> value = null;
		try {
			value = response.jsonPath().getList(path);
		} catch (NullPointerException | IllegalArgumentException | JsonPathException ne) {
			throwJsonNodeNotFoundException(path, response);
		}
		return value;
	}

	/**
	 * returns response body as String
	 * 
	 * @return
	 */
	public String asString() {
		return response.body().asString();
	}

	/**
	 * examples1: response.path("players.find { it.jerseyNumber == 20 }.name")
	 * examples2: response.path(findAll {it.status!='success'}.processName)
	 */
	public <T> T path(String groovyExpression) {
		T value = null;
		try {
			value = response.path(groovyExpression);
			if (Objects.isNull(value)) {
				throwJsonNodeNotFoundExceptionForNullValues(value, groovyExpression, response);
			}
		} catch (NullPointerException | IllegalArgumentException | JsonPathException ne) {
			throwJsonNodeNotFoundException(groovyExpression, response);
		}
		return value;
	}

}
