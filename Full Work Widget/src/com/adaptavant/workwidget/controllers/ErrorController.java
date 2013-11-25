package com.adaptavant.workwidget.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ErrorController {
	
	@RequestMapping( value="error", method = RequestMethod.GET)
	public String showErrorPage() {
		return "error";
	}

}