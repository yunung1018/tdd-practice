package tw.teddysoft.clean.adapter.gateway.workitem;

import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
import tw.teddysoft.clean.domain.model.workitem.WorkItem;
import tw.teddysoft.clean.usecase.workitem.WorkItemRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryWorkItemRepository implements WorkItemRepository {

    private List<WorkItem> workItems;

    public InMemoryWorkItemRepository(){
        workItems = new ArrayList<WorkItem>();
    }

    @Override
    public List<WorkItem> findAll() {
        return workItems;
    }

    @Override
    public WorkItem findById(String id) {
        for(WorkItem each : workItems){
            if (each.getId().equalsIgnoreCase(id))
                return each;
        }
        throw new RuntimeException("Cannot find workitem with id : " + id);
    }

    @Override
    public WorkItem findFirstByName(String name) {
        for(WorkItem each : workItems){
            if (each.getName().equals(name))
                return each;
        }
        throw new RuntimeException("Cannot find workitem with name : " + name);
    }

    @Override
    public WorkItem save(WorkItem arg) {
        if (workItems.contains(arg))
            return arg;
        else if (workItems.add(arg))
            return arg;
        else
            return null;
    }

    @Override
    public boolean remove(WorkItem arg) {
        return workItems.remove(arg);
    }

    @Override
    public List<WorkItem> findByStageId(String StageId) {
        throw  new RuntimeException("Not Implemented.");
    }
}
