package com.simplesdental.helpers.request;

import java.io.IOException;
import java.io.OutputStream;

import com.google.api.client.http.HttpContent;

public class RequestContent implements HttpContent {

	private String type = "application/json";
	private String content = null;
	private long length = 0;

	public RequestContent(String content) {
		this.content = content;
		this.length = content.length();
	}

	@Override
	public long getLength() throws IOException {
		return this.content.getBytes("UTF-8").length;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public boolean retrySupported() {
		return false;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void writeTo(OutputStream out) throws IOException {
		byte[] bytes = this.content.getBytes("UTF-8");
		out.write(bytes, 0, bytes.length);
	}
};
