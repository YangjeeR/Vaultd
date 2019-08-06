package Guests;

public class LoginInputData {
	
	String userName;
	String password;
	String deviceType;
	String deviceId;	
	String deviceToken;
	
	public LoginInputData(String userName, String password, String deviceType, String deviceId, String deviceToken) {

		this.userName = userName;
		this.password = password;
		this.deviceType = deviceType;
		this.deviceId = deviceId;
		this.deviceToken = deviceToken;
	}

	public String getEmail() {
		return userName;
	}

	public void setEmail(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	

}
