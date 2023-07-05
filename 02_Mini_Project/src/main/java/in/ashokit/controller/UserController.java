package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.constants.AppConstants;
import in.ashokit.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String signUpPage(Model model) {

		model.addAttribute("user", new SignUpForm());
		return "signup";

	}

	@PostMapping("/signup")
	public String handleSighnUp(@ModelAttribute("user") SignUpForm form, Model model) {

		boolean status = userService.signUp(form);

		if (status) {
			model.addAttribute(AppConstants.STR_SUCCESS, "Account created,Check your mail");
		} else {
			model.addAttribute(AppConstants.ERR_MSG, "Choose unique email");
		}
		return "signup";

	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {

		UnlockForm unlockFormObj = new UnlockForm();
		unlockFormObj.setEmail(email);

		model.addAttribute(AppConstants.STR_UNLOCK, unlockFormObj);

		return "unlock";

	}

	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute(AppConstants.STR_UNLOCK) UnlockForm unlock, Model model) {

		
		if (unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
			boolean status = userService.unlockAccount(unlock);
			if (status) {
				model.addAttribute(AppConstants.STR_SUCCESS, "Your account unlocked successfully");
			} else {
				model.addAttribute(AppConstants.ERR_MSG, "Given temporary password is incorrect,check your email");
			}
		} else {
			model.addAttribute(AppConstants.ERR_MSG, "New password and confirm password should be same");

		}

		return AppConstants.STR_UNLOCK;
	}

	@GetMapping("/login")
	public String loginPage(Model model) {

		model.addAttribute("loginForm", new LoginForm());

		return "login";

	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {

		String status = userService.login(loginForm);

		if (status.contains("success")) {
			// display dashboard
			
			

			return "redirect:/dashboard";
		}

		model.addAttribute(AppConstants.ERR_MSG, status );
		
		return "login";

	}

	@GetMapping("/forgot")
	public String forgotPwdPage() {

		return "forgotpwd";

	}

	
	@PostMapping("/forgotPwd")
	public String forgotPwdPage(@RequestParam("email") String email,Model model) {
		
		
		
		String status = userService.forgotPwd(email);
		model.addAttribute("msg", status);
		return "forgotpwd";

	}

}
