package tw.teddysoft.clean.adapter.gateway.kanbanboard;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryWorkflowRepository implements WorkflowRepository {

    private List<Workflow> workflows;

    public InMemoryWorkflowRepository(){
        workflows = new ArrayList<Workflow>();
    }

    public List<Workflow> findAll() {
        return workflows;
    }

    public Workflow findById(String id) {
        for(Workflow each : workflows){
            if (each.getId().equalsIgnoreCase(id))
                return each;
        }
        throw new RuntimeException("Cannot find workflow with id : " + id);
    }

    public Workflow findFirstByName(String name) {
        for(Workflow each : workflows){
            if (each.getTitle().equals(name))
                return each;
        }
        throw new RuntimeException("Cannot find workflow with name : " + name);
    }


    public Workflow save(Workflow arg) {
        if (workflows.contains(arg)) {
            return arg;
        }
        else if (workflows.add(arg)){
            return arg;
        }
        else
            return null;
    }

    public boolean remove(Workflow arg) {
        return workflows.remove(arg);
    }

    public List<Workflow> findByBoardId(String boardId) {
        List<Workflow> results = new ArrayList<Workflow>();

        for(Workflow each : workflows){
            if (each.getBoardId().equalsIgnoreCase(boardId))
                results.add(each);
        }
        return results;
    }
}
