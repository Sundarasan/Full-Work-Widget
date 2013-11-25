package com.adaptavant.workwidget.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.adaptavant.workwidget.dto.Account;
import com.adaptavant.workwidget.dto.Contact;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping( value="home", method = RequestMethod.GET)
	public ModelAndView showHome( HttpServletRequest req, HttpServletResponse resp ) {
		
		HttpSession session = req.getSession(false);
		ModelAndView mav = new ModelAndView();
		
		if( session != null ) {
			
			mav.addObject("account", (Account) session.getAttribute("account"));
			mav.addObject("contact", (Contact) session.getAttribute("contact"));
			mav.setViewName( "hom" );
			
		} else {
			
			mav.setViewName("redirect:error");
			
		}
		
		return mav;
	}

}
