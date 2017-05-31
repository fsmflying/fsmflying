package com.fsmflying.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpJsonResult {
	private int result = 1;
	private Map<String, Object> data;
	private String message;
	private List<?> rows;

	public HttpJsonResult() {
		super();
		this.data = new HashMap<String, Object>();
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
