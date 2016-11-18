package com.simplesdental.helpers.json.deserializers;

import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class EnumDeserializer extends JsonDeserializer<Enum> implements ContextualDeserializer {
	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		ctxt.setAttribute("EnumType:" + property.getName(), ctxt.getContextualType().getRawClass());
		return this;
	}

	@Override
	public Enum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Class clazz = (Class) ctxt.getAttribute("EnumType:" + p.getCurrentName());
		Field[] declaredFields = clazz.getDeclaredFields();

		for (Field field : declaredFields) {
			Enum enum_ = Enum.valueOf(clazz, field.getName());

			Field[] _declaredFields = enum_.getClass().getDeclaredFields();

			for (Field _field : declaredFields) {
				if (_field.getName().equals("value")) {
					try {
						if (((Integer) _field.get(enum_)).equals(p.getNumberValue())) {
							return enum_;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return null;
	}
}