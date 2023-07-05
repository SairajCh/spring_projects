package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.PostForm;
import in.ashokit.entity.Comment;

public interface UserBlogService {
	
	public Boolean savePost(PostForm form);
	
	public List<PostForm> getUserPosts(Integer userId);
	
	public List<Comment> getPostComments();

}
