package ntut.csie.sslab.kanban.workflow.usecase.port.out;

import java.util.HashMap;
import java.util.Map;

import ntut.csie.sslab.kanban.workflow.entity.Workflow1;

public class WorkflowRepository1 {
    private Map<String, Workflow1> workflows = new HashMap<>();

    public void save(Workflow1 workflow) {
        workflows.put(workflow.getId(), workflow);
    }
    public Workflow1 findById(String id) {
        return workflows.get(id);
    }

}
