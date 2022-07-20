package ntut.csie.sslab.kanban.usecase;

import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBusAdapter;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.board.adapter.in.eventbus.NotifyBoardGoogleEventBusAdapter;
import ntut.csie.sslab.kanban.workflow.adapter.in.eventbus.NotifyWorkflowGoogleEventBusAdapter;
import ntut.csie.sslab.kanban.board.adapter.out.repository.BoardRepositorySpringJpaAdapter;
import ntut.csie.sslab.kanban.board.adapter.out.repository.BoardRepositoryPeer;
import ntut.csie.sslab.kanban.card.adapter.out.repository.CardRepositoryImpl;
import ntut.csie.sslab.kanban.card.adapter.out.repository.CardRepositoryPeer;
import ntut.csie.sslab.kanban.workflow.adapter.out.repository.WorkflowRepositoryImpl;
import ntut.csie.sslab.kanban.workflow.adapter.out.repository.WorkflowRepositoryPeer;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardInput;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardUseCase;
import ntut.csie.sslab.kanban.board.usecase.service.CreateBoardService;
import ntut.csie.sslab.kanban.board.usecase.port.in.getcontent.GetBoardContentUseCase;
import ntut.csie.sslab.kanban.board.usecase.service.GetBoardContentService;
import ntut.csie.sslab.kanban.card.usecase.port.out.CardRepository;
import ntut.csie.sslab.kanban.card.usecase.port.in.create.CreateCardInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.create.CreateCardUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.CreateCardService;
import ntut.csie.sslab.kanban.card.usecase.port.in.delete.DeleteCardInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.delete.DeleteCardUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.DeleteCardUseService;
import ntut.csie.sslab.kanban.card.usecase.port.in.deadline.ChangeCardDeadlineInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.deadline.ChangeCardDeadlineUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.ChangeCardDeadlineService;
import ntut.csie.sslab.kanban.card.usecase.port.in.description.ChangeCardDescriptionInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.description.ChangeCardDescriptionUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.ChangeCardDescriptionService;
import ntut.csie.sslab.kanban.card.usecase.port.in.estimate.ChangeCardEstimateInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.estimate.ChangeCardEstimateUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.ChangeCardEstimateService;
import ntut.csie.sslab.kanban.card.usecase.port.in.note.ChangeCardNoteUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.ChangeCardNoteService;
import ntut.csie.sslab.kanban.card.usecase.port.in.note.ChangeCardNoteInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.type.ChangeCardTypeInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.type.ChangeCardTypeUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.ChangeCardTypeService;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.GetCardUseCase;
import ntut.csie.sslab.kanban.card.usecase.service.GetCardUseService;
import ntut.csie.sslab.kanban.board.usecase.service.NotifyBoardService;
import ntut.csie.sslab.kanban.workflow.usecase.service.NotifyWorkflowService;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.rename.RenameLaneInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.rename.RenameLaneUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.service.RenameLaneService;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.stage.create.CreateStageInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.stage.create.CreateStageUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.service.CreateStageService;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.swimlane.create.CreateSwimLaneInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.swimlane.create.CreateSwimLaneUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.service.CreateSwimLaneService;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.WorkflowRepository;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflowUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.service.CreateWorkflowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= JpaApplicationTest.class)
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public abstract class AbstractSpringBootJpaTest {

    public static final long WAITING = 1000;

    public BoardRepository boardRepository;
    public WorkflowRepository workflowRepository;
    public CardRepository cardRepository;
    public DomainEventBus domainEventBus;
    public NotifyBoardGoogleEventBusAdapter notifyBoardAdapter;
    public NotifyWorkflowGoogleEventBusAdapter notifyWorkflowGoogleEventBusAdapter;
    public String teamId;
    public String teamName;
    public String projectId;
    public String projectName;
    public String boardId;
    public String workflowId;
    public String rootStageId;
    public String tagId;
    public String userId;
    public String username;
    public String boardName;


    @Autowired
    private BoardRepositoryPeer boardRepositoryPeer;

    @Autowired
    private WorkflowRepositoryPeer workflowRepositoryPeer;

    @Autowired
    private CardRepositoryPeer cardRepositoryPeer;



    @BeforeEach
    public void setUp() {

        boardRepository = new BoardRepositorySpringJpaAdapter(boardRepositoryPeer);
        workflowRepository = new WorkflowRepositoryImpl(workflowRepositoryPeer);
        cardRepository = new CardRepositoryImpl(cardRepositoryPeer);
        domainEventBus = new GoogleEventBusAdapter();

        teamId = UUID.randomUUID().toString();
        teamName = "ntut team";
        projectId = UUID.randomUUID().toString();
        projectName = "ezkanban project";
        boardId = UUID.randomUUID().toString();
        workflowId = UUID.randomUUID().toString();
        tagId = UUID.randomUUID().toString();
        rootStageId = UUID.randomUUID().toString();
        userId = UUID.randomUUID().toString();
        username = "Teddy";
        boardName = "Scrum Board";

        notifyBoardAdapter = new NotifyBoardGoogleEventBusAdapter(new NotifyBoardService(boardRepository, domainEventBus));
        notifyWorkflowGoogleEventBusAdapter = new NotifyWorkflowGoogleEventBusAdapter(new NotifyWorkflowService(workflowRepository, domainEventBus));

    }


    public CreateBoardUseCase newCreateBoardUseCase (){
        return new CreateBoardService(boardRepository, domainEventBus);
    }

    public GetBoardContentUseCase newGetBoardContentUseCase() {
        return new GetBoardContentService(boardRepository, workflowRepository, cardRepository, domainEventBus);
    }

    public CreateWorkflowUseCase newCreateWorkflowUseCase() {
        return new CreateWorkflowService(workflowRepository, domainEventBus);
    }

    public CreateStageUseCase newCreateStageUseCase() {
        return new CreateStageService(workflowRepository, domainEventBus);
    }

    public RenameLaneUseCase newRenameLaneUseCase() {
        return new RenameLaneService(workflowRepository, domainEventBus);
    }

    public CreateSwimLaneUseCase newCreateSwimLaneUseCase() {
        return new CreateSwimLaneService(workflowRepository, domainEventBus);
    }

    public CreateCardUseCase newCreateCardUseCase() {
        return new CreateCardService(cardRepository, domainEventBus);
    }

    public ChangeCardDeadlineUseCase newChangeCardDeadlineUseCase() {
        return new ChangeCardDeadlineService(cardRepository, domainEventBus);
    }

    public DeleteCardUseCase newDeleteCardUseCase() {
        return new DeleteCardUseService(cardRepository, domainEventBus);
    }

    public ChangeCardNoteUseCase newChangeCardNotesUseCase() {
        return new ChangeCardNoteService(cardRepository, domainEventBus);
    }


    public ChangeCardTypeUseCase newChangeCardTypeUseCase() {
        return new ChangeCardTypeService(cardRepository, domainEventBus);
    }

    public ChangeCardEstimateUseCase newChangeCardEstimateUseCase() {
        return new ChangeCardEstimateService(cardRepository, domainEventBus);
    }

    public ChangeCardDescriptionUseCase newChangeCardDescriptionUseCase() {
        return new ChangeCardDescriptionService(cardRepository, domainEventBus);
    }

    public GetCardUseCase newGetCardByIdUseCase() {
        return new GetCardUseService(cardRepository);
    }


    public String createBoard(String teamId, String name, String userId){
        CreateBoardUseCase createBoardUseCase = newCreateBoardUseCase();

        CreateBoardInput input = new CreateBoardInput();
        input.setTeamId(teamId);
        input.setName(name);
        input.setUserId(userId);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createBoardUseCase.execute(input));

        return viewModel.getId();
    }

    public String createWorkflow(String boardId, String workflowName, String userId, String username) {
        CreateWorkflowUseCase createWorkflowUseCase = newCreateWorkflowUseCase();

        CreateWorkflowInput input = new CreateWorkflowInput();
        input.setName(workflowName);
        input.setBoardId(boardId);
        input.setUserId(userId);
        input.setUsername(username);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createWorkflowUseCase.execute(input));

        return viewModel.getId();
    }

    public String createStage(
            String boardId,
            String userId,
            String username,
            String workflowId,
            String parentId,
            String name,
            int wipLimit,
            String laneType) {

        CreateStageUseCase createStageUseCase = newCreateStageUseCase();

        CreateStageInput input = new CreateStageInput();
        input.setBoardId(boardId);
        input.setWorkflowId(workflowId);
        input.setParentId(parentId);
        input.setName(name);
        input.setWipLimit(wipLimit);
        input.setLaneType(laneType);
        input.setUserId(userId);
        input.setUsername(username);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createStageUseCase.execute(input));

        return viewModel.getId();
    }

    public String createSwimLane(
            String workflowId,
            String parentId,
            String name,
            int wipLimit,
            String laneType,
            String userId,
            String username,
            String boardId) {

        CreateSwimLaneUseCase createSwimLaneUseCase = newCreateSwimLaneUseCase();

        CreateSwimLaneInput input = new CreateSwimLaneInput();
        input.setWorkflowId(workflowId);
        input.setParentId(parentId);
        input.setName(name);
        input.setWipLimit(wipLimit);
        input.setLaneType(laneType);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createSwimLaneUseCase.execute(input));

        return viewModel.getId();
    }

    public String renameLane(String workflowId, String laneId, String newName, String userId, String username){

        RenameLaneUseCase renameLaneUseCase = newRenameLaneUseCase();
        RenameLaneInput input = new RenameLaneInput();
        input.setWorkflowId(workflowId);
        input.setLaneId(laneId);
        input.setNewName(newName);
        input.setUserId(userId);
        input.setUsername(username);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(renameLaneUseCase.execute(input));

        return viewModel.getId();
    }

    public String createCard(String userId,
                             String workflowId,
                             String laneId,
                             String description,
                             String estimate,
                             String notes,
                             String deadline,
                             String type,
                             String username,
                             String boardId){

        CreateCardUseCase createCardUseCase = newCreateCardUseCase();
        CreateCardInput createCardInput = new CreateCardInput();
        createCardInput.setWorkflowId(workflowId);
        createCardInput.setLaneId(laneId);
        createCardInput.setUserId(userId);
        createCardInput.setDescription(description);
        createCardInput.setEstimate(estimate);
        createCardInput.setNote(notes);
        createCardInput.setDeadline(deadline);
        createCardInput.setType(type);
        createCardInput.setUsername(username);
        createCardInput.setBoardId(boardId);


        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createCardUseCase.execute(createCardInput));

        Card card = cardRepository.findById(viewModel.getId()).get();
        assertNotNull(card);
        assertEquals(userId, card.getUserId());
        assertEquals(laneId, card.getLaneId());
        assertEquals(description, card.getDescription());
        assertEquals(estimate, card.getEstimate());
        assertEquals(notes, card.getNotes());
        assertEquals(deadline, card.getDeadline());
        assertEquals(type, card.getType().toString());

        return viewModel.getId();
    }

    public void deleteCard(String workflowId, String laneId, String cardId, String userId, String username, String boardId) {
        DeleteCardUseCase deleteCardUseCase = newDeleteCardUseCase();

        DeleteCardInput input = new DeleteCardInput();
        input.setWorkflowId(workflowId);
        input.setLaneId(laneId);
        input.setCardId(cardId);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(deleteCardUseCase.execute(input));
    }

    public void changeCardDescription(String cardId, String newDescription, String boardId, String userId, String username){
        ChangeCardDescriptionUseCase changeCardDescriptionUseCase = newChangeCardDescriptionUseCase();

        ChangeCardDescriptionInput input = new ChangeCardDescriptionInput();
        input.setCardId(cardId);
        input.setDescription(newDescription);
        input.setBoardId(boardId);
        input.setUserId(userId);
        input.setUsername(username);

        CqrsCommandPresenter.newInstance().buildViewModel(changeCardDescriptionUseCase.execute(input));
    }

    public void changeCardEstimate(String cardId, String newEstimate, String boardId, String userId, String username){
        ChangeCardEstimateUseCase changeCardEstimateUseCase = newChangeCardEstimateUseCase();

        ChangeCardEstimateInput input = new ChangeCardEstimateInput();
        input.setCardId(cardId);
        input.setNewEstimate(newEstimate);
        input.setBoardId(boardId);
        input.setUserId(userId);
        input.setUsername(username);

        CqrsCommandPresenter.newInstance().buildViewModel(changeCardEstimateUseCase.execute(input));
    }


    public void changeCardDeadline(String cardId, String newDeadline, String boardId, String userId, String username) {
        ChangeCardDeadlineUseCase changeCardDeadlineUseCase = newChangeCardDeadlineUseCase();

        ChangeCardDeadlineInput input = new ChangeCardDeadlineInput();
        input.setCardId(cardId);
        input.setNewDeadline(newDeadline);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CqrsCommandPresenter.newInstance().buildViewModel(changeCardDeadlineUseCase.execute(input));
    }

    public void changeCardNotes(String cardId, String newNotes, String boardId, String userId, String username) {
        ChangeCardNoteUseCase changeCardNoteUseCase = newChangeCardNotesUseCase();

        ChangeCardNoteInput input = new ChangeCardNoteInput();
        input.setCardId(cardId);
        input.setNewNotes(newNotes);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CqrsCommandPresenter.newInstance().buildViewModel(changeCardNoteUseCase.execute(input));
    }

    public void changeCardType(String cardId, String newType, String boardId, String userId, String username) {

        ChangeCardTypeUseCase changeCardTypeUseCase = newChangeCardTypeUseCase();

        ChangeCardTypeInput input = new ChangeCardTypeInput();
        input.setCardId(cardId);
        input.setNewType(newType);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CqrsCommandPresenter.newInstance().buildViewModel(changeCardTypeUseCase.execute(input));
    }

    public BoardRepository getBoardRepository(){
        return boardRepository;
    }
}
