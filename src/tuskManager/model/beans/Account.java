package tuskManager.model.beans;

public class Account {
	protected String email, pw;
	
	public Account(String email, String pw) {
		this.email = email;
		this.pw = pw;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return pw;
	}
	
}
