package com.example.demo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns="/test", name="customServlet")
public class CustomServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(CustomServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException
    {
		logger.debug("[Servlet doGet]");
		//doGet(req, resp);
		resp.getWriter().write("get");
    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException 
	{
		logger.debug("[Servlet doPost]");
		//doPost(req, resp);
		resp.getWriter().write("post");
	}
	
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException 
	{
		logger.debug("[Servlet doHead]");
		doHead(req, resp);
	}
}
