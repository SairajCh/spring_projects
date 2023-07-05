package in.ashokit.binding;

import lombok.Data;

@Data
public class UnlockForm {

	private String userEmail;
	
	private String userPwd;
	
	private String newPwd;

	private String confirmNewPwd;

}

