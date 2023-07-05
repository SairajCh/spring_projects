package in.ashokit.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRestController {

	@GetMapping("/")
	public String load() {
		return "index";
	}
	
	@GetMapping("/cmsg")
	@ResponseBody
	public String getDropDownMsg(@RequestParam("cname") String cname) {

		String msg = "I am going to," + cname;

		// model.addAttribute("msg", msg);
		return msg;

	}

	@GetMapping("/msg")
	@ResponseBody
	public String getMsg(@RequestParam("name") String name) {

		String msg = "Hello," + name;

		// model.addAttribute("msg", msg);
		return msg;

	}

}
