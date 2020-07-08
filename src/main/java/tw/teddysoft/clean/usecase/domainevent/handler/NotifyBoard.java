package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.card.event.CardCreated;
import tw.teddysoft.clean.domain.model.card.event.CardDeleted;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.WorkflowCreated;
import tw.teddysoft.clean.domain.usecase.repository.Repository;


public class NotifyBoard {

    private Repository<Board> boardRepository;
    private DomainEventBus eventBus;

    public NotifyBoard(Repository boardRepository,
                       DomainEventBus eventBus){

        this.boardRepository = boardRepository;
        this.eventBus = eventBus;
    }

    @Subscribe
    public void whenWorkflowCreated(WorkflowCreated event) {
        System.out.println("NotifyBoard, event = " + event.detail());

        Board board = boardRepository.findById(event.getEntity().getBoardId());
        board.commitWorkflow(event.getEntity().getId());
        boardRepository.save(board);
        eventBus.postAll(board);
    }


}
