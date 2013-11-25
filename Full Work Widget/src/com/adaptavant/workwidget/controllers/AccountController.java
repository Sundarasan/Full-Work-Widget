package com.adaptavant.workwidget.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class AccountController {
	
	@RequestMapping(value="getAll", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getAccount( @RequestBody String requestBody, @RequestParam("code") String code, @RequestParam("state") String state ) {
		
		return new HashMap<String, Object>();
		
	}

}
