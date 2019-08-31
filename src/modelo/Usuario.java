package modelo;

import java.math.BigDecimal;

public class Usuario {

	private int userID; 
	private String userName; 
	private String	userEmail;
	private BigDecimal	userPhone; 
	private String	userLogin; 
	private String	userPassword; 
	private boolean	userRoot;
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public BigDecimal getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(BigDecimal userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public boolean isUserRoot() {
		return userRoot;
	}
	public void setUserRoot(boolean userRoot) {
		this.userRoot = userRoot;
	}


  
    
    


    

    
    
}
