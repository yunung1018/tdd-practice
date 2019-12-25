package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.WorkflowCreated;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;

import java.util.ArrayList;
import java.util.List;

public class WorkflowCreatedHandler {

    private BoardRepository boardRepository;
    private DomainEventBus eventBus;

    public WorkflowCreatedHandler(BoardRepository boardRepository, DomainEventBus eventBus) {
        this.boardRepository = boardRepository;
        this.eventBus = eventBus;
    }

    public Class<WorkflowCreated> subscribedToEventType() {
        return WorkflowCreated.class;
    }

    @Subscribe
    public void handleEvent(WorkflowCreated domainEvent) {

        System.out.println("WorkflowCreatedHandler ******************>");

        Board board = boardRepository.findById(domainEvent.getEntity().getBoardId());
        System.out.println("Board domain event size  = " + board.getDomainEvents().size());
//        board.clearDomainEvents();
//        System.out.println("Clear Board domain event");
//        System.out.println("Board domain event size  = " + board.getDomainEvents().size());

        board.commitWorkflow(domainEvent.getEntity().getId());
        boardRepository.save(board);
        System.out.println("    ======> commit the workflow to its board");

        System.out.println("Board domain event size  = " + board.getDomainEvents().size());

        eventBus.postAll(board);

    }
}
