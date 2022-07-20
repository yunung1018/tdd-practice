package ntut.csie.sslab.account.users.adapter.get;

import ntut.csie.sslab.account.users.usecase.get.UserDto;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;

public class UserBasicViewModel extends CommonViewModel {
    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
