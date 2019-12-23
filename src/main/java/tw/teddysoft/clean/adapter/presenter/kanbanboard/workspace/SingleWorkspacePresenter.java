package tw.teddysoft.clean.adapter.presenter.kanbanboard.workspace;

import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;

public class SingleWorkspacePresenter implements CreateWorkspaceOutput {

    private String workspaceId;
    private String userId;

    @Override
    public String getWorkspaceId() {
        return workspaceId;
    }

    @Override
    public void setWorkspaceId(String workspaceId) {
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
        sb.append("Workspace id: ").append(getWorkspaceId()).append("; ");
        sb.append("User id: ").append(getUserId()).append(".");
        return sb.toString();
    }
}
