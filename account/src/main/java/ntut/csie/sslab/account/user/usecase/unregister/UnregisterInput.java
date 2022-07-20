package ntut.csie.sslab.account.user.usecase.unregister;

import ntut.csie.sslab.ddd.usecase.Input;

public class UnregisterInput implements Input {

	private String userId;
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
