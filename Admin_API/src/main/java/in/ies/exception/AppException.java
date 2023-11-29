package in.ies.exception;

import java.time.LocalDateTime;

public class AppException {

	private String excode;

	private String exDesc;
	
	private LocalDateTime exDate;

	public String getExcode() {
		return excode;
	}

	public void setExcode(String excode) {
		this.excode = excode;
	}

	public String getExDesc() {
		return exDesc;
	}

	public void setExDesc(String exDesc) {
		this.exDesc = exDesc;
	}

	public LocalDateTime getExDate() {
		return exDate;
	}

	public void setExDate(LocalDateTime exDate) {
		this.exDate = exDate;
	}

	
}
