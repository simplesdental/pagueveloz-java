package com.simplesdental.helpers.json;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.simplesdental.helpers.json.deserializers.EnumDeserializer;
import com.simplesdental.helpers.json.serializers.EnumSerializer;

public class JsonUtils {
	public static final ObjectMapper defaultMapper() {
		ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
		SimpleModule module = new SimpleModule("EnumModule");
		module.addSerializer(Enum.class, new EnumSerializer());
		module.addDeserializer(Enum.class, new EnumDeserializer());
		mapper.registerModule(module);
		return mapper;
	}
}
