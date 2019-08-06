package Guests;

public class UpdateEmail {
	String emailVerifyCode;
	String email;
	
	public UpdateEmail(String emailVerifyCode, String email) {
		
		this.emailVerifyCode = emailVerifyCode;
		this.email = email;
	}

	public String getEmailVerifyCode() {
		return emailVerifyCode;
	}

	public void setEmailVerifyCode(String emailVerifyCode) {
		this.emailVerifyCode = emailVerifyCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
