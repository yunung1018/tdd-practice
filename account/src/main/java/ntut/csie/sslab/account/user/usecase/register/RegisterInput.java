package ntut.csie.sslab.account.user.usecase.register.in;

import ntut.csie.sslab.ddd.usecase.Input;

public class RegisterInput implements Input {

	private String username;
	private String email;
	private String password;
	private String nickname;
	private String role;
	private boolean isThirdParty;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isThirdParty() {
		return isThirdParty;
	}

	public void setThirdParty(boolean thirdParty) {
		isThirdParty = thirdParty;
	}
}
