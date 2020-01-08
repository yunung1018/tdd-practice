package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.WorkflowCreated;
import tw.teddysoft.clean.domain.usecase.repository.Repository;

public class WorkflowCommittedHandler {

    private Repository<Board> boardRepository;
    private EventBus eventBus;

    public WorkflowCommittedHandler(Repository boardRepository, EventBus eventBus){
        this.boardRepository = boardRepository;
        this.eventBus = eventBus;
    }

    public Class<WorkflowCreated> subscribedToEventType() {
        return WorkflowCreated.class;
    }

    @Subscribe
    public void handleEvent(WorkflowCreated domainEvent) {
        System.out.println("WorkflowCommittedHandler ++++++++++++++++++++++>");
        System.out.println("WorkflowCommitted ======> doming nothing");
    }
}
