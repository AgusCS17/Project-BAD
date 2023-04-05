package model;

	

public class User {

	
	private String id, email;
	private boolean isLogin;

	public User(String id, String email, boolean isLogin) {
		super();
		this.id = id;
		this.email = email;
		this.isLogin = isLogin;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	
	
	
	
}
