package tw.teddysoft.clean.adapter.rest.kanbanboard;

import tw.teddysoft.clean.domain.model.kanbanboard.board.CommittedWorkflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BoardDto {
    private String id;
    private String workspaceId;
    private Set<Workflow> workflows;

    public BoardDto(String id) {
        this.id = id;
        workflows = new HashSet<>();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Set<Workflow> getWorkflows() {
        return workflows;
    }

    public void addWorkflows(Workflow workflow) {
        this.workflows.add(workflow);
    }
}
