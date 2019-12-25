package tw.teddysoft.clean.adapter.presenter.kanbanboard.home;

import tw.teddysoft.clean.usecase.kanbanboard.home.create.CreateHomeOutput;

public class SingleHomePresenter implements CreateHomeOutput {

    private String workspaceId;
    private String userId;

    @Override
    public String getHomeId() {
        return workspaceId;
    }

    @Override
    public void setHomeId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Workspace id: ").append(getHomeId()).append("; ");
        sb.append("User id: ").append(getUserId()).append(".");
        return sb.toString();
    }
}
