package com.simplesdental.helpers;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

public class Utils {
	public static String dateToISOFormat(Date date) {
		ISO8601DateFormat iso8601DateFormat = new ISO8601DateFormat();
		return iso8601DateFormat.format(date);
	}

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
