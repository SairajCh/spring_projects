package in.ies.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ies.bindings.DashboardCard;
import in.ies.bindings.LoginForm;
import in.ies.bindings.UserAccountForm;
import in.ies.constants.AppConstants;
import in.ies.service.AccountService;
import in.ies.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService service;

	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		String status = userService.login(loginForm);

		if (status.equals(AppConstants.SUCCESS)) {
			return "redirect:/dashboard?email=" + loginForm.getEmail();

		} else {
			return status;
		}

	}

	@GetMapping("/dashboard")
	public ResponseEntity<DashboardCard> buildDashboar(@RequestParam("email") String email) {

		UserAccountForm user = userService.getUserByEmail(email);
		
		DashboardCard dashboardCard = userService.fetchDashboardInfo();
		
		dashboardCard.setUser(user);

		return new ResponseEntity<>(dashboardCard, HttpStatus.OK);

	}

}
