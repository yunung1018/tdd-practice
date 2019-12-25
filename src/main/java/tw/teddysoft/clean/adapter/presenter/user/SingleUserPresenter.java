package tw.teddysoft.clean.adapter.presenter.user;

import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;
import tw.teddysoft.clean.usecase.user.RegisterUserOutput;

public class SingleUserPresenter implements RegisterUserOutput {

    private String userId;

    @Override
    public void setUserId(String id) {
        this.userId = id;

    }

    @Override
    public String getUserId() {
        return userId;
    }
}
