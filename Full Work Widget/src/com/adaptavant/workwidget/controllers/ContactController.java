package com.adaptavant.workwidget.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adaptavant.workwidget.services.ContactService;

@Controller
@RequestMapping("/")
public class ContactController {
	
	@RequestMapping(value="getReqContacts", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getRequiredContacts( @RequestBody String requestBody, @RequestParam("apiKey") String accountId ) {
		
		return ContactService.getRequiredContacts( accountId, requestBody );
		
	}

	@RequestMapping(value="getAllContacts", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getAllContacts( @RequestParam("accountId") String accountId ) {
		
		return ContactService.getAllContacts( accountId );
		
	}

}
