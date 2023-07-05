package in.ashokit.binding;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class PostForm {
	
	private String title;
	
	private String desc;
	
	@Lob
	private String content;

}
