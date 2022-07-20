package ntut.csie.sslab.kanban.main.config;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardUseCase;
import ntut.csie.sslab.kanban.board.usecase.service.CreateBoardService;
import ntut.csie.sslab.kanban.board.usecase.port.in.getcontent.GetBoardContentUseCase;
import ntut.csie.sslab.kanban.board.usecase.service.GetBoardContentService;
import ntut.csie.sslab.kanban.card.usecase.port.out.CardRepository;
import ntut.csie.sslab.kanban.card.usecase.port.in.create.CreateCardUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.CreateCardService;
import ntut.csie.sslab.kanban.card.usecase.port.in.delete.DeleteCardUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.DeleteCardUseService;
import ntut.csie.sslab.kanban.card.usecase.port.in.deadline.ChangeCardDeadlineUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.ChangeCardDeadlineService;
import ntut.csie.sslab.kanban.card.usecase.port.in.description.ChangeCardDescriptionUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.ChangeCardDescriptionService;
import ntut.csie.sslab.kanban.card.usecase.port.in.estimate.ChangeCardEstimateUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.ChangeCardEstimateService;
import ntut.csie.sslab.kanban.card.usecase.port.in.note.ChangeCardNoteUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.ChangeCardNoteService;
import ntut.csie.sslab.kanban.card.usecase.port.in.type.ChangeCardTypeUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.ChangeCardTypeService;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.GetCardUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.GetCardUseService;
import ntut.csie.sslab.kanban.board.usecase.service.NotifyBoardService;
import ntut.csie.sslab.kanban.workflow.usecase.service.NotifyWorkflowService;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.rename.RenameLaneUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.service.RenameLaneService;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.stage.create.CreateStageUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.service.CreateStageService;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.swimlane.create.CreateSwimLaneUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.service.CreateSwimLaneService;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.WorkflowRepository;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflowUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.service.CreateWorkflowService;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.GetWorkflowUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.service.GetWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;

@Configuration("KanbanUseCaseInjection")
public class UseCaseInjection {
    private BoardRepository boardRepository;
    private WorkflowRepository workflowRepository;
    private CardRepository cardRepository;
    private DomainEventBus eventBus;
    private ExecutorService executor;


    @Bean(name="createNotifyBoard")
    public NotifyBoardService createNotifyBoard() {
        return new NotifyBoardService(boardRepository, eventBus);
    }

    @Bean(name="createNotifyWorkflow")
    public NotifyWorkflowService createNotifyWorkflow() {
        return new NotifyWorkflowService(workflowRepository, eventBus);
    }


    @Bean(name="createBoardUseCase")
    public CreateBoardUseCase createBoardUseCase() {
        return new CreateBoardService(boardRepository, eventBus);
    }


    @Bean(name="createWorkflowUseCase")
    public CreateWorkflowUseCase createWorkflowUseCase() {
        return new CreateWorkflowService(workflowRepository, eventBus);
    }

    @Bean(name="getWorkflowUseCase")
    public GetWorkflowUseCase getWorkflowUseCase() {
        return new GetWorkflowService(workflowRepository);
    }

    @Bean(name="createStageUseCase")
    public CreateStageUseCase getCreateStageUseCase() {
        return new CreateStageService(workflowRepository, eventBus);
    }

    @Bean(name="createSwimLaneUseCase")
    public CreateSwimLaneUseCase getCreateSwimLaneUseCase() {
        return new CreateSwimLaneService(workflowRepository, eventBus);
    }


    @Bean(name="getBoardContentUseCase")
    public GetBoardContentUseCase getBoardContentUseCase() {
        return new GetBoardContentService(boardRepository, workflowRepository, cardRepository, eventBus);
    }

    @Bean(name="createCardUseCase")
    public CreateCardUseCase getCreateCardUseCase() {
        return new CreateCardService(cardRepository, eventBus);
    }

    @Bean(name="deleteCardUseCase")
    public DeleteCardUseCase getDeleteCardUseCase() {
        return new DeleteCardUseService(cardRepository, eventBus);
    }

    @Bean(name="changeCardDescriptionUseCase")
    public ChangeCardDescriptionUseCase getChangeCardDescriptionUseCase() {
        return new ChangeCardDescriptionService(cardRepository, eventBus);
    }

    @Bean(name="changeCardNoteUseCase")
    public ChangeCardNoteUseCase getChangeCardNoteUseCase() {
        return new ChangeCardNoteService(cardRepository, eventBus);
    }

    @Bean(name="changeCardEstimateUseCase")
    public ChangeCardEstimateUseCase getChangeCardEstimateUseCase() {
        return new ChangeCardEstimateService(cardRepository, eventBus);
    }

    @Bean(name="changeCardDeadlineUseCase")
    public ChangeCardDeadlineUseCase getChangeCardDeadlineUseCase() {
        return new ChangeCardDeadlineService(cardRepository, eventBus);
    }

    @Bean(name="changeCardTypeUseCase")
    public ChangeCardTypeUseCase getChangeCardTypeUseCase() {
        return new ChangeCardTypeService(cardRepository, eventBus);
    }

    @Bean(name="getCardUseCase")
    public GetCardUseCase getGetCardUseCase() {
        return new GetCardUseService(cardRepository);
    }


    @Bean(name="renameLaneUseCase")
    public RenameLaneUseCase getRenameLaneUseCase() {
        return new RenameLaneService(workflowRepository, eventBus);
    }

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Autowired
    public void setWorkflowRepository(WorkflowRepository workflowRepository) {
        this.workflowRepository = workflowRepository;
    }

    @Autowired
    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    @Autowired
    public void setEventBus(DomainEventBus eventBus) { this.eventBus = eventBus; }


}
