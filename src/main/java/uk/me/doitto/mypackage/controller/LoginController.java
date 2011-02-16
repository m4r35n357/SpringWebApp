package uk.me.doitto.mypackage.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.throwaway.ThrowawayController;

import uk.me.doitto.mypackage.service.InetService;

public class LoginController implements ThrowawayController {
	
	public ModelAndView execute() throws Exception {
		return new ModelAndView("login", "inetAddress", InetService.getInetAddress());
	}
}
