package com.qa.data;

public class ChangePassword {

	 
		String currentPassword;
		String newPassword;

		
		public ChangePassword(String currentPassword, String newPassword)
		{
			this.currentPassword = currentPassword;
			this.newPassword = newPassword;
		}
		
		
		public String getcurrentPassword() {
			return currentPassword;
		}
		public void setcurrentPassword(String currentPassword) {
			this.currentPassword = currentPassword;
		}
		
		public String getnewPassword() {
			return newPassword;
		}
		public void setnewPassword(String newPassword) {
			this.newPassword = newPassword;
		}

		
}
