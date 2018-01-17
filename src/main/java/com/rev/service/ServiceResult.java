package com.rev.service;

public class ServiceResult {
	private String fOrR;
	private String uri;
	
	public ServiceResult(String uri, String fOrR) {
		this.uri = uri;
		this.fOrR = fOrR;
	}

	public String getfOrR() {
		return fOrR;
	}

	public String getUri() {
		return uri;
	}

	public void setfOrR(String fOrR) {
		this.fOrR = fOrR;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
}
