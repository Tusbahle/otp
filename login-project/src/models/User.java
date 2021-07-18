package models;

public class User {
	private String id;
	private String username;
	private String mobile;
	private String email;

	public User() {

	}

	public User(String id, String username, String mobile, String email) {
		this.id = id;
		this.username = username;
		this.mobile = mobile;
		this.email = email;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + getId() + "\n " + "Username: " + getUsername() + "\n " + "Mobile: " + getMobile() + "\n "
				+ "Email: " + getEmail();
	}
}
