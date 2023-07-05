package in.ashokit.binding;

import lombok.Data;

@Data
public class ForgotPwdForm {
	private String username;
	private String newPwd;
	private String confirmPwd;

}
