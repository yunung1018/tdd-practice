package tw.teddysoft.clean.usecase;

import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryAggregateRootRepositoryPeer;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryDomainEventRepositoryPeer;
import tw.teddysoft.clean.adapter.presenter.card.SingleCardPresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.board.SingleBoardPresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.lane.SingleStagePresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workflow.SingleWorkflowPresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workspace.SingleWorkspacePresenter;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.usecase.card.CardRepository;
import tw.teddysoft.clean.usecase.card.create.CreateCardInput;
import tw.teddysoft.clean.usecase.card.create.CreateCardOutput;
import tw.teddysoft.clean.usecase.card.create.CreateCardUseCase;
import tw.teddysoft.clean.usecase.domainevent.DomainEventRepository;
import tw.teddysoft.clean.usecase.domainevent.FlowEventRepository;
import tw.teddysoft.clean.usecase.domainevent.handler.*;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.home.HomeRepository;
import tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.CreateStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.CreateStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.CreateStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.CreateSwimlaneInput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.CreateSwimlaneOutput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.CreateSwimlaneUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowInput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceInput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceUseCase;
import tw.teddysoft.clean.usecase.user.UserRepository;

import static org.junit.Assert.assertEquals;

public class Context {

    public static final String USER_LOGIN_ID = "teddy@teddysoft.tw";
    public static final String USER_PASSWORD = "!@#124ASdfadyd08kdvF@tddd";
    public static final String USER_NAME = "Teddy Chen";
    public static final String USER_EMAIL = "teddy@teddysoft.tw";

    public static final String USER_ID = "USER-ID-8967";
    public static final String WORKSPACE_ID = "WORKSPACE-ID-5678";
    public static final String WORKSPACE_NAME = "Teddy's Workspace";
    public static final String WORKFLOW_NAME = "DEFAULT";

    public static final String SCRUM_BOARD_NAME = "Scrum Board";

    private final UserRepository userRepository;
    private final HomeRepository homeRepository;
    private final WorkspaceRepository workspaceRepository;
    private final WorkflowRepository workflowRepository;
    private final BoardRepository boardRepository;
    private final CardRepository cardRepository;
    private final DomainEventRepository storedEventRepository;
    private final FlowEventRepository flowEventRepository;

    private final UserEventHandler userEventHandler;
    private final WorkflowEventHandler workflowEventHandler;
    private final FlowEventHandler flowEventHandler;
    private final EventSourcingHandler eventSourcingHandler;
    private final BoardEventHandler boardEventHandler;
    private final WorkspaceEventHandler workspaceEventHandler;

    private final DomainEventBus eventBus;

    public static Context newInstance() {
        return new Context();
    }

    public Context(){
        this(  new UserRepository(new InMemoryAggregateRootRepositoryPeer()),
                new HomeRepository(new InMemoryAggregateRootRepositoryPeer()),
                new WorkspaceRepository(new InMemoryAggregateRootRepositoryPeer()),
                new BoardRepository(new InMemoryAggregateRootRepositoryPeer()),
                new WorkflowRepository(new InMemoryAggregateRootRepositoryPeer()),
                new CardRepository(new InMemoryAggregateRootRepositoryPeer()),
                new FlowEventRepository(new InMemoryDomainEventRepositoryPeer()),
                new DomainEventRepository(new InMemoryDomainEventRepositoryPeer()));
    }

    public Context(
            UserRepository userRepository,
            HomeRepository homeRepository,
            WorkspaceRepository workspaceRepository,
            BoardRepository boardRepository,
            WorkflowRepository workflowRepository,
            CardRepository cardRepository,
            FlowEventRepository flowEventRepository,
            DomainEventRepository storedEventRepository){

        this.userRepository = userRepository;
        this.homeRepository = homeRepository;
        this.workspaceRepository = workspaceRepository;
        this.boardRepository = boardRepository;
        this.workflowRepository = workflowRepository;
        this.cardRepository = cardRepository;
        this.flowEventRepository = flowEventRepository;
        this.storedEventRepository = storedEventRepository;

        this.eventBus = new DomainEventBus();

        this.userEventHandler = new UserEventHandler(this.getHomeRepository(), eventBus);
        this.workflowEventHandler = new WorkflowEventHandler(this.getBoardRepository(), this.getWorkflowRepository(), eventBus);
        this.flowEventHandler = new FlowEventHandler(flowEventRepository, eventBus);
        this.eventSourcingHandler = new EventSourcingHandler(storedEventRepository);
        this.boardEventHandler = new BoardEventHandler(workspaceRepository, workflowRepository, eventBus);
        this.workspaceEventHandler = new WorkspaceEventHandler(workspaceRepository, workflowRepository, eventBus);

    }


    public UserRepository getUserRepository() {
        return userRepository;
    }

    public HomeRepository getHomeRepository() {
        return homeRepository;
    }

    public WorkspaceRepository getWorkspaceRepository() {
        return workspaceRepository;
    }

    public WorkflowRepository getWorkflowRepository() {
        return workflowRepository;
    }

    public BoardRepository getBoardRepository() {
        return boardRepository;
    }

    public CardRepository getCardRepository() {
        return cardRepository;
    }

    public DomainEventRepository getStoredEventRepository() {
        return storedEventRepository;
    }

    public FlowEventRepository getFlowEventRepository() {
        return flowEventRepository;
    }

    public void registerAllEventHandler(){
        eventBus.register(userEventHandler);
        eventBus.register(workflowEventHandler);
        eventBus.register(boardEventHandler);
        eventBus.register(workspaceEventHandler);
        eventBus.register(flowEventHandler);
        eventBus.register(eventSourcingHandler);
    }

    public DomainEventBus getDomainEventBus(){
        return eventBus;
    }

    public CreateBoardOutput doCreateBoardUseCase(String workspaceId, String boardName) {
        UseCase<CreateBoardInput, CreateBoardOutput> addBoardUC = new CreateBoardUseCase(
                boardRepository,
                eventBus);

        CreateBoardInput input = addBoardUC.createInput();
        CreateBoardOutput output = new SingleBoardPresenter();
        input.setWorkspaceId(workspaceId);
        input.setBoardName(boardName);

        addBoardUC.execute(input, output);
        return output;
    }

    public CreateWorkflowOutput doCreateWorkflowUseCase(String boardId, String name) {
        UseCase<CreateWorkflowInput, CreateWorkflowOutput> createWorkflowUC =
                new CreateWorkflowUseCase(
                        this.getWorkflowRepository(),
                        this.getDomainEventBus());

        CreateWorkflowInput input = createWorkflowUC.createInput();
        CreateWorkflowOutput output = new SingleWorkflowPresenter();
        input.setBoardId(boardId);
        input.setWorkflowName(name);

        createWorkflowUC.execute(input, output);
        return output;
    }


    public CreateStageOutput doCreateStageUseCase(String workflowId, String name, String parentId){

        UseCase<CreateStageInput, CreateStageOutput> createStageLaneUC =
                new CreateStageUseCase(this.getWorkflowRepository(), getDomainEventBus());

        CreateStageInput input = createStageLaneUC.createInput();
        CreateStageOutput output = new tw.teddysoft.clean.adapter.presenter.kanbanboard.lane.SingleStagePresenter();
        input.setWorkflowId(workflowId);
        input.setName(name);
        input.setParentId(parentId);

        createStageLaneUC.execute(input, output);

        return output;
    }

    public CreateSwimlaneOutput doCreateSwimlaneUseCase(String workflowId, String LaneName, String parentId){

        UseCase<CreateSwimlaneInput, CreateSwimlaneOutput> createSwimLaneUC =
                new CreateSwimlaneUseCase(this.getWorkflowRepository(), getDomainEventBus());

        CreateSwimlaneInput input = createSwimLaneUC.createInput();
        CreateSwimlaneOutput output = new SingleStagePresenter();

        input.setWorkflowId(workflowId);
        input.setParentId(parentId);
        input.setTitle(LaneName);

        createSwimLaneUC.execute(input, output);

        return output;
    }

    public CreateWorkspaceOutput doCreateWorkspaceUseCase(String userId, String workspaceName) {
        UseCase<CreateWorkspaceInput, CreateWorkspaceOutput> createWorkspaceUC =
                new CreateWorkspaceUseCase(this.getWorkspaceRepository());

        CreateWorkspaceInput input = createWorkspaceUC.createInput();
        CreateWorkspaceOutput output = new SingleWorkspacePresenter();
        input.setUserId(userId);
        input.setWorkspaceName(workspaceName);

        createWorkspaceUC.execute(input, output);

        return output;
    }

    public CreateCardOutput doCreateCardUseCase(String name, String workflowId, String laneId) {

        UseCase<CreateCardInput, CreateCardOutput> createCardUseCase =
                new CreateCardUseCase(this.getCardRepository(), this.getWorkflowRepository(), getDomainEventBus());

        CreateCardInput input = createCardUseCase.createInput() ;
        CreateCardOutput output = new SingleCardPresenter();
        input.setName(name);
        input.setWorkflowId(workflowId)
                .setLaneId(laneId);

        createCardUseCase.execute(input, output);
        return output;
    }


    public void createBusinessProcessMaintenanceStage(Workflow workflow){
        // create a stage like : https://reurl.cc/1QEryG

        create_top_stage(workflow.getId(), "Operations - Business Process Maintenance");
        Lane operation = workflow.getStages().get(0);

        String productionProblemId = createSwimlane(workflow.getId(), "Production Problem", operation.getId());
        createStage(workflow.getId(), "New", productionProblemId);
        String workingID = createStage(workflow.getId(), "Working", productionProblemId);
        createStage(workflow.getId(), "Find Cause", workingID);
        createStage(workflow.getId(), "Fix Cause", workingID);
        createStage(workflow.getId(), "Done", productionProblemId);

        String plannedBusinessNeedId = createSwimlane(workflow.getId(), "Planned Business Need", operation.getId());
        String due2Months = createStage(workflow.getId(), "Due 2 months", plannedBusinessNeedId);
        createSwimlane(workflow.getId(), "High Impact", due2Months);
        createSwimlane(workflow.getId(), "Low Impact", due2Months);
        String due1Month = createStage(workflow.getId(), "Due 1 month", plannedBusinessNeedId);
        createSwimlane(workflow.getId(), "High Impact", due1Month);
        createSwimlane(workflow.getId(), "Low Impact", due1Month);
        String due1week = createStage(workflow.getId(), "Due 1 week", plannedBusinessNeedId);
        createSwimlane(workflow.getId(), "High Impact", due1week);
        createSwimlane(workflow.getId(), "Low Impact", due1week);
        String inWork = createStage(workflow.getId(), "In Work", plannedBusinessNeedId);
        createSwimlane(workflow.getId(), "High Impact", inWork);
        createSwimlane(workflow.getId(), "Low Impact", inWork);
        String done = createStage(workflow.getId(), "Done", plannedBusinessNeedId);
        createSwimlane(workflow.getId(), "High Impact", done);
        createSwimlane(workflow.getId(), "Low Impact", done);

        String routineId = createSwimlane(workflow.getId(), "Routine", operation.getId());

        String unplannedId = createSwimlane(workflow.getId(), "Unplanned", operation.getId());
        createStage(workflow.getId(), "New", unplannedId);
        createStage(workflow.getId(), "Committed", unplannedId);
        createStage(workflow.getId(), "In Work", unplannedId);
        createStage(workflow.getId(), "Test", unplannedId);
        createStage(workflow.getId(), "Done", unplannedId);

        String platformImprovementsId = createSwimlane(workflow.getId(), "Platform Improvements", operation.getId());
        createStage(workflow.getId(), "New", platformImprovementsId);
        createStage(workflow.getId(), "Committed", platformImprovementsId);
        createStage(workflow.getId(), "In Work", platformImprovementsId);
        createStage(workflow.getId(), "Test", platformImprovementsId);
        createStage(workflow.getId(), "Done", platformImprovementsId);

        workflow.dumpLane();
        assertEquals(36, workflow.getTotalLaneSize());
    }

    public void createScrumStage(Workflow workflow){

        create_top_stage(workflow.getId(), "To Do");
        create_top_stage(workflow.getId(), "Doing");
        create_top_stage(workflow.getId(), "Done");

    }

    public void crateKanbanBoardGame(Workflow workflow){

        create_top_stage(workflow.getId(), "Backlog");
        Lane backlog = workflow.getStages().get(0);

        create_top_stage(workflow.getId(), "Ready");
        Lane ready = workflow.getStages().get(1);

        String analysisId= create_top_stage(workflow.getId(), "Analysis");
            createStage(workflow.getId(), "In Progress", analysisId);
            createStage(workflow.getId(), "Done", analysisId);

        String developmentId= create_top_stage(workflow.getId(), "Development");
        createStage(workflow.getId(), "In Progress", developmentId);
        createStage(workflow.getId(), "Done", developmentId);

        create_top_stage(workflow.getId(), "Test");
        create_top_stage(workflow.getId(), "Ready to Deploy");
        create_top_stage(workflow.getId(), "Deployed");
    }

    private String createWorkflow(String boardId, String name) {
        return this.doCreateWorkflowUseCase(boardId, name).getWorkflowId();
    }

    private String create_top_stage(String workflowId, String name){
        return createStage(workflowId, name, null);
    }

    private String createStage(String workflowId, String name, String parentId){
        return this.doCreateStageUseCase(workflowId, name, parentId).getId();
    }

    private String createSwimlane(String workflowId, String name, String parentId){
        return this.doCreateSwimlaneUseCase(workflowId, name, parentId).getId();
    }

}
