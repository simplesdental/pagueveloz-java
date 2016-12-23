package com.simplesdental.test.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.simplesdental.helpers.Json;
import com.simplesdental.test.mocks.Mock;
import com.simplesdental.test.mocks.MockEnum;

public class JsonTest {
	private Mock mockInstance = new Mock(MockEnum.Tipo6, "bar", 123);
	private String jsonString = "{\"tipo\":6,\"foo\":\"bar\",\"foo_int\":123}";
	private String jsonEnumString = "{\"tipo\":\"Tipo6\",\"foo\":\"bar\",\"foo_int\":123}";

	@Test
	public void fromJson() {
		JsonNode parsed = Json.parse(jsonString);
		Mock mock = Json.fromJson(parsed, Mock.class);

		assertEquals(mock.foo_int, mockInstance.foo_int);
		assertEquals(mock.tipo, mockInstance.tipo);
		assertEquals(mock.foo, mockInstance.foo);

	}

	@Test
	public void fromJsonWithEnumString() {
		JsonNode parsedEnumString = Json.parse(jsonEnumString);
		Mock mock = Json.fromJson(parsedEnumString, Mock.class);

		assertEquals(mock.tipo, mockInstance.tipo);
	}

	@Test
	public void toJson() {
		JsonNode jsonNode = Json.toJson(mockInstance);
		assertEquals(jsonString, jsonNode.toString());
	}
}
