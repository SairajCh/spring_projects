package in.ashokit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.ashokit.binding.PostForm;
import in.ashokit.repo.PostRepo;
import in.ashokit.service.UserBlogService;

@Controller
public class DashboarbController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserBlogService userBlogService;

	@Autowired
	private PostRepo postRepo;

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		List<PostForm> userPosts = userBlogService.getUserPosts(userId);
		model.addAttribute("userPosts", userPosts);

		return "dashboard";

	}
	
	@GetMapping("/viewpost")
	public String view_Post(Model model) {
		
		
		
		return "view-post";
	}
	
	
	
	
	
	
	
	
	

}
