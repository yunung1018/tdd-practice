package tw.teddysoft.clean.domain.model.kanbanboard.home;

import tw.teddysoft.clean.domain.model.AggregateRoot;
import tw.teddysoft.clean.domain.model.kanbanboard.home.event.HomeCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.home.event.WorkspaceCommitted;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.CommittedBoard;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.event.BoardCommitted;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Home extends AggregateRoot {

    private final String userId;
    private final Set<CommittedWorkspace> committedWorkspaces;

    public Home(String name, String userId) {
        super(name);
        this.userId = userId;
        committedWorkspaces = new HashSet<>();

        addDomainEvent(new HomeCreated(this.getId(), userId, name));
    }

    public void commitWorkspace(String workspaceId){
        committedWorkspaces.add(new CommittedWorkspace(this.getId(), workspaceId));

        addDomainEvent(new WorkspaceCommitted(this.getId(), workspaceId));
    }

    public Set<CommittedWorkspace> getCommittedWorkspaces() {
        return Collections.unmodifiableSet(committedWorkspaces);
    }

    public String getUserId() {
        return userId;
    }

}
