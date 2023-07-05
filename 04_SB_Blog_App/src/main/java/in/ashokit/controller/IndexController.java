package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.ashokit.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogRepo blogRepo;

	@GetMapping("/")
	public String indexPage(Model model) {
		
		blogRepo
		
		
		return "index";
	}
}
