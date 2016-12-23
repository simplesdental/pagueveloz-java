package com.simplesdental.helpers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.simplesdental.helpers.json.JsonUtils;

/**
 * Helper functions to handle JsonNode values.
 */
public class Json {
	private static final ObjectMapper defaultObjectMapper = JsonUtils.defaultMapper();
	private static volatile ObjectMapper objectMapper = null;

	/**
	 * Convert a JsonNode to a Java value
	 *
	 * @param json
	 *            Json value to convert.
	 * @param clazz
	 *            Expected Java value type.
	 */
	public static <A> A fromJson(JsonNode json, Class<A> clazz) {
		try {
			return mapper().treeToValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T fromJson(String jsonString, Class<T> class1) {
		return Json.fromJson(Json.parse(jsonString), class1);
	}

	public static <T> List<T> fromJsonList(String jsonString, Class<T> class1) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, class1);
		return mapper.readValue(jsonString, type);
	}

	// Ensures that there always is *a* object mapper
	private static ObjectMapper mapper() {
		if (objectMapper == null) {
			return defaultObjectMapper;
		} else {
			return objectMapper;
		}
	}

	/**
	 * Creates a new empty ObjectNode.
	 */
	public static ObjectNode newObject() {
		return mapper().createObjectNode();
	}

	/**
	 * Parse a InputStream representing a json, and return it as a JsonNode.
	 */
	public static JsonNode parse(java.io.InputStream src) {
		try {
			return mapper().readValue(src, JsonNode.class);
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	/**
	 * Parse a String representing a json, and return it as a JsonNode.
	 */
	public static JsonNode parse(String src) {
		try {
			return mapper().readValue(src, JsonNode.class);
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	/**
	 * Inject the object mapper to use.
	 *
	 * This is intended to be used when Play starts up. By default, Play will inject its own object mapper here, but this mapper can be overridden either by a custom plugin or from Global.onStart.
	 */
	public static void setObjectMapper(ObjectMapper mapper) {
		objectMapper = mapper;
	}

	/**
	 * Convert a JsonNode to its string representation.
	 */
	public static String stringify(JsonNode json) {
		return json.toString();
	}

	/**
	 * Convert an object to JsonNode.
	 *
	 * @param data
	 *            Value to convert in Json.
	 */
	public static JsonNode toJson(final Object data) {
		try {
			return mapper().valueToTree(data);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String toString(Object object) {
		return Json.toJson(object).toString();
	}

}
