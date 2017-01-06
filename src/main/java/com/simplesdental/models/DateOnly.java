package com.simplesdental.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonValue;

public class DateOnly {
	private Integer date;
	private Integer year;
	private Integer month;

	public DateOnly(String dateString) {
		String[] splitedDate = dateString.split("/");
		this.year = Integer.valueOf(splitedDate[2]);
		this.month = Integer.valueOf(splitedDate[1]);
		this.date = Integer.valueOf(splitedDate[0]);
	}

	public Integer getDate() {
		return date;
	}

	public Integer getMonth() {
		return month;
	}

	public Integer getYear() {
		return year;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Date toDateObject() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date);
		return calendar.getTime();
	}

	@JsonValue
	public String toJSON() {
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		return formater.format(toDateObject());
	}
}
