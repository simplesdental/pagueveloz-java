package com.simplesdental.helpers;

import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

public class Utils {
	@SuppressWarnings("unchecked")
	public static String replace(String template, Object object) {
		StrSubstitutor sub = new StrSubstitutor(Utils.toMap(object));
		return sub.replace(template);
	}

	@SuppressWarnings("rawtypes")
	public static Map toMap(Object object) {
		return Json.fromJson(Json.toJson(object), Map.class);
	}
}
