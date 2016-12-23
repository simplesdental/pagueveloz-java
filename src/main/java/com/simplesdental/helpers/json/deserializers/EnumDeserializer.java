package com.simplesdental.helpers.json.deserializers;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class EnumDeserializer extends JsonDeserializer<Enum> implements ContextualDeserializer {
	public static HashMap<String, Object> enumsTypes = new HashMap();

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		enumsTypes.put("EnumType:" + property.getName(), ctxt.getContextualType().getRawClass());
		return this;
	}

	@Override
	public Enum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String enumKey = "EnumType:" + p.getCurrentName();
		Class clazz = (Class) enumsTypes.get(enumKey);

		Field[] declaredFields = clazz.getDeclaredFields();

		for (Field field : declaredFields) {
			Enum enum_ = Enum.valueOf(clazz, field.getName());

			Field[] _declaredFields = enum_.getClass().getDeclaredFields();

			for (Field _field : declaredFields) {
				if (_field.getName().equals("value")) {

					if (field.getName().equals(p.getText())) {
						return enum_;
					}

					try {
						if (((Integer) _field.get(enum_)).equals(p.getNumberValue())) {
							return enum_;
						}
					} catch (Exception e) {
						continue;
					}
				}
			}
		}

		return null;
	}
}