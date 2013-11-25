package com.adaptavant.workwidget.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.adaptavant.workwidget.constants.FinalVariables;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String startingPoint() {
		return "redirect:index";
	}
	
	@RequestMapping( value="index", method = RequestMethod.GET)
	public ModelAndView showIndexPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject( "googleOAuthUrl", FinalVariables.getGoogleAuthHelper().buildLoginUrl() );
		mav.setViewName( "index" );
		return mav;
	}

}