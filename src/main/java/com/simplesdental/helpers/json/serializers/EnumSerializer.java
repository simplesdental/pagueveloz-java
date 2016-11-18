package com.simplesdental.helpers.json.serializers;

import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@SuppressWarnings("rawtypes")
public class EnumSerializer extends JsonSerializer<Enum> {
	@Override
	public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		Field[] declaredFields = value.getClass().getDeclaredFields();

		for (Field field : declaredFields) {
			if (field.getName().equals("value")) {
				try {
					gen.writeNumber((Integer) field.get(value));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
