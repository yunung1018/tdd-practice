package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.card.event.CardCreated;
import tw.teddysoft.clean.domain.model.card.event.CardDeleted;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.WorkflowCreated;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;


public class WorkflowEventHandler {

    private BoardRepository boardRepository;
    private WorkflowRepository workflowRepository;
    private DomainEventBus eventBus;

    public WorkflowEventHandler(BoardRepository boardRepository,
                                WorkflowRepository workflowRepository,
                                DomainEventBus eventBus){

        this.boardRepository = boardRepository;
        this.workflowRepository = workflowRepository;
        this.eventBus = eventBus;
    }

    @Subscribe
    public void handleEvent(WorkflowCreated event) {
        System.out.println("WorkflowEventHandler, event = " + event.detail());

        Board board = boardRepository.findById(event.getEntity().getBoardId());
        board.commitWorkflow(event.getEntity().getId());
        boardRepository.save(board);
        eventBus.postAll(board);
    }

    @Subscribe
    public void handleEvent(CardCreated event) {
        System.out.println("WorkflowEventHandler, event = " + event.detail());

        Workflow workflow = workflowRepository.findById(event.getEntity().getWorkflowId());
        workflow.commitCard(event.getEntity().getId(), event.getLaneId());
        workflowRepository.save(workflow);
        eventBus.postAll(workflow);
    }

    @Subscribe
    public void handleEvent(CardDeleted event) {
        System.out.println("WorkflowEventHandler, event = " + event.detail());

        Workflow workflow = workflowRepository.findById(event.getWorkflowId());
        workflow.uncommitCard(event.getCardId(), event.getLaneId());
        workflowRepository.save(workflow);
        eventBus.postAll(workflow);
    }
}
