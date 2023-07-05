package in.ashokit.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import in.ashokit.binding.PostForm;
import in.ashokit.entity.Comment;
import in.ashokit.entity.Post;
import in.ashokit.entity.User;
import in.ashokit.repo.PostRepo;
import in.ashokit.repo.UserRepo;

public class UserBlogServiceImpl implements UserBlogService {
	
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private HttpSession session;

	@Override
	public List<Comment> getPostComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostForm> getUserPosts(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean savePost(PostForm form) {
		// TODO Auto-generated method stub
		Integer userId = (Integer) session.getAttribute("userId");
		
		Post postEntity = new Post();
		BeanUtils.copyProperties(form, postEntity);
		
		User userEntity = userRepo.findById(userId).get();
		
		postEntity.setUser(userEntity);
		postRepo.save(postEntity);
		
		
		return true;
	}
}
