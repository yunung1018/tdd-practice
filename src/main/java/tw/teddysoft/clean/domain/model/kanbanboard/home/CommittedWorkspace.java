package tw.teddysoft.clean.domain.model.kanbanboard.home;

import tw.teddysoft.clean.domain.model.ValueObject;

public class CommittedWorkspace extends ValueObject {

    private final String homeId;
    private final String workspaceId;

    public CommittedWorkspace(String homeId, String workspaceId) {
        super();
        this.homeId = homeId;
        this.workspaceId = workspaceId;
    }

    //TODO implement equals and hashCode

    public String getHomeId() {
        return homeId;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }
}
