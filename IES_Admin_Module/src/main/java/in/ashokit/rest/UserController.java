package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.LoginForm;
import in.ashokit.service.IesUserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	private IesUserServiceImpl iesUserService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		
		String loginStatus = iesUserService.login(loginForm);
		
		
		if(loginStatus.equals("admin")) {
			return "redirect:/dashboard/{}";
		}
		
		return new ResponseEntity<String>(loginStatus,HttpStatus.ACCEPTED);
		
	}
	
	
	
	

}
