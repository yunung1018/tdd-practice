package ntut.csie.sslab.account.user.usecase.get;

import ntut.csie.sslab.ddd.usecase.Input;

public class GetUserInput implements Input {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
