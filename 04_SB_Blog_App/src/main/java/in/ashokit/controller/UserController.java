package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignupForm;
import in.ashokit.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/signup")
	public String signUpPage(Model model) {

		SignupForm form = new SignupForm();

		model.addAttribute("formObj", form);

		return "signup";

	}

	@PostMapping("/signup")
	public String signUphandle(@ModelAttribute("formObj") SignupForm form, Model model) {

		Boolean status = userService.signUp(form);

		if (status) {
			model.addAttribute("succMsg", "Successful registration");
		}

		model.addAttribute("errMsg", "Invalid credentialls");

		return "signup";

	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		
		LoginForm form = new LoginForm();
		
		model.addAttribute("formObj",form);
		return "login";
		
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("formObj")LoginForm form,Model model) {
		
		Boolean status = userService.login(form);
		if(status) {
			return "redirect:/dashboard";
		}
		model.addAttribute("errMsg", "Invalid credentials");
		return "login";
		
	}

	
	
	
}
