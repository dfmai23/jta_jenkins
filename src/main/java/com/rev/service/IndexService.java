package com.rev.service;

import static com.rev.util.Constants.FORWARD;

import javax.servlet.http.HttpServletRequest;

public class IndexService {
	public static ServiceResult index(HttpServletRequest request) {
		return new ServiceResult("index.html", FORWARD);
	}
}
