package com.rev.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rev.model.User;
import com.rev.service.ServiceResult;
import static com.rev.util.Constants.*;

/* Servlet that maps every single request that the client makes */
//@WebServlet({"/*"})
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1159764852861289598L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceResult sr = ControllerDispatcher.process(request);
		String uri = sr.getUri(), 
				url = sr.getUri();	//both the same
		if (sr.getfOrR() == FORWARD) {
			request.getRequestDispatcher(uri).forward(request, response); //forward to uris
		}
		else {	
			response.sendRedirect(url);	//redirect to new url
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceResult sr = ControllerDispatcher.process(request);
		String uri = sr.getUri(),
				url = sr.getUri();	//both the same
		if (sr.getfOrR() == REDIRECT) {
			response.sendRedirect(url);	//redirect to new url
		}
		else {	
			request.getRequestDispatcher(uri).forward(request, response); //forward to uris

		}
	}
}
