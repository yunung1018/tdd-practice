package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.WorkflowCreated;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;

import java.util.ArrayList;
import java.util.List;

public class WorkflowCommittedHandler {

    private BoardRepository boardRepository;
    private EventBus eventBus;

    public WorkflowCommittedHandler(BoardRepository boardRepository, EventBus eventBus){
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
