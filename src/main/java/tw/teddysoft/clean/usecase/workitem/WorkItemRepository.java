package tw.teddysoft.clean.usecase.workitem;

import tw.teddysoft.clean.domain.model.workitem.WorkItem;

import java.util.List;

public interface WorkItemRepository {

    List<WorkItem> findAll();
    WorkItem findById(String id);

    WorkItem findFirstByName(String name);

    WorkItem save(WorkItem arg);
    boolean remove(WorkItem arg);

    List<WorkItem> findByStageId(String StageId);

}
