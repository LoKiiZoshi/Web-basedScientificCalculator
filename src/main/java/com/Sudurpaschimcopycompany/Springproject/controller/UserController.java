package com.Sudurpaschimcopycompany.Springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Sudurpaschimcopycompany.Springproject.model.User;
import com.Sudurpaschimcopycompany.Springproject.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService us;

	@GetMapping("/")
	public String signup() {
		return "SignupForm";
	}

	@GetMapping("/getLoginForm")
	public String openlogin() {
		return "UserLoginForm";
	}

	@GetMapping("/getsingup")
	public String getsignup() {
		return "SignupForm";

	}

	@PostMapping("/newregester")
	public ModelAndView newUserRegister(@ModelAttribute User user) {

		boolean exists = us.checkUserExists(user.getUsername());

		if (!exists) {
			System.out.println("Username:" + user.getUsername());
			user.setRole("ROLE_NORMAL");
			this.us.signup(user);
			System.out.println("new user created with password:" + user.getPassword());
			ModelAndView successView = new ModelAndView("UserLoginForm");
			return successView;
		} else {
			System.out.println("new user not created - username taken:" + user.getUsername());
			ModelAndView failureView = new ModelAndView("SignupForm");
			failureView.addObject("msg", user.getUsername() + " is taken. please choose a different username.");
			return failureView;
		}
	}

	@PostMapping("/loginuser")
	public String postlogin(@ModelAttribute User user, Model model, HttpSession session) {
		User u = us.loginUser(user.getUsername(), user.getPassword());

		if (u != null) {
			session.setAttribute("validuser", u);

			return "IndexForm";
		} else {
			model.addAttribute("error", "user not found!");
			return "UserLoginForm";
		}
	}

}
