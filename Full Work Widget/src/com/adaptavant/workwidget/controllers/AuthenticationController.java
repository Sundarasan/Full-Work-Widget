package com.adaptavant.workwidget.controllers;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adaptavant.workwidget.factories.AccountFactory;
import com.adaptavant.workwidget.dto.Account;
import com.adaptavant.workwidget.dto.Contact;
import com.adaptavant.workwidget.services.AuthenticateService;

@Controller
@RequestMapping("/")
public class AuthenticationController {
	
	private static Logger logger = Logger.getLogger( AuthenticationController.class.getPackage().getName());

	@RequestMapping(value="authenticateAccount", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> authenticateAccount() {
		return AccountFactory.authenticateAccount("sundarasan.natarajan@a-cti.com");
	}
	
	@RequestMapping(value = "oauth2callback", method = RequestMethod.GET)
	public String OAuth2CallBackGoogle( @RequestParam("state") String state, @RequestParam("code") String code, HttpServletRequest req, HttpServletResponse resp ) {
		
		HttpSession session = req.getSession();
		
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> hashMapOfAccount =  AuthenticateService.validateUser(state, code, req, resp);
		
		if( hashMapOfAccount.containsKey("success") && (boolean) hashMapOfAccount.get("success") ) {
			
			Contact contact = (Contact) hashMapOfAccount.get("contact");
			Account account = (Account) hashMapOfAccount.get("account");
			
			session.setAttribute("account", account);
			session.setAttribute("contact", contact);
			
		}
		
		return "redirect:home";
		
	}
	
}
