package com.ashu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashu.binding.LoginForm;
import com.ashu.binding.SignUpForm;
import com.ashu.binding.UnlockForm;
import com.ashu.services.UserServices;

@Controller
public class UserController {

	@Autowired
	private UserServices userService;

	@PostMapping("/signup")
	public String handleSignup(@ModelAttribute("user") SignUpForm form, Model model) {
		boolean status = userService.signUp(form);

		if (status) {
			model.addAttribute("succMsg", "Check Your Mail");
		} else {
			model.addAttribute("errMsg", "Email Already registered, Please choose different Mailid");
		}

		return "signup";

	}

	@GetMapping("/login")
	public String loginPage(Model model) {

		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm form, Model model) {
		String status = userService.login(form);
		if (status.contains("Success")) {
			return "redirect:/dashboard";
		}
		model.addAttribute("errMsg", status);
		return "login";
	}
	
	
	
	
	

	@GetMapping("/signup")
	public String signUp(Model model) {

		model.addAttribute("user", new SignUpForm());
		return "signup";
	}

	@GetMapping("/forgot")
	public String forgotPassword() {
		return "forgotPwd";
	}
	
	@PostMapping("/forgot")
	public String forgotPasswrd(@RequestParam("email") String email, Model model) {
		
		String status = userService.forgotPassword(email);
		
		model.addAttribute("msg", status);
		
		return "forgotPwd";
	}
	


	
	
	

	@GetMapping("/unlock")
	public String unlockAcc(@RequestParam String email, Model model) {
		UnlockForm unockForm = new UnlockForm();
		unockForm.setEmail(email);
		model.addAttribute("unlock", unockForm);

		return "unlock";
	}

	@PostMapping("/unlock")
	public String unlockAccount(@ModelAttribute("unlock") UnlockForm form, Model model) {

		System.out.println(form);

		if (form.getNewPwd().equals(form.getConfirmPwd())) {
			boolean status = userService.unlockAccount(form);

			if (status) {
				model.addAttribute("succMsg", "Your Account is Unlocked");
			} else {
				model.addAttribute("errMsg", "Your Temporory Password is Incorrect, Check Your Mail");
			}

		} else {
			model.addAttribute("errMsg", "New Password and and Confirm Password are not same");
		}

		return "unlock";

	}

}
