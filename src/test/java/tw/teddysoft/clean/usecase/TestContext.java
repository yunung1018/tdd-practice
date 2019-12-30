package tw.teddysoft.clean.usecase;

import tw.teddysoft.clean.adapter.gateway.kanbanboard.*;
import tw.teddysoft.clean.adapter.presenter.card.SingleCardPresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.lane.SingleStagePresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workflow.SingleWorkflowPresenter;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.usecase.card.CardRepository;
import tw.teddysoft.clean.usecase.card.create.CreateCardInput;
import tw.teddysoft.clean.usecase.card.create.CreateCardOutput;
import tw.teddysoft.clean.usecase.card.create.CreateCardUseCase;
import tw.teddysoft.clean.usecase.domainevent.DomainEventRepository;
import tw.teddysoft.clean.usecase.domainevent.FlowEventRepository;
import tw.teddysoft.clean.usecase.domainevent.handler.*;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.CreateBoardUseCaseWithEventHandlerTest;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardOutput;
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
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceUseCaseTest;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;
import tw.teddysoft.clean.usecase.user.UserRepository;

public class TestContext {

    public static final String SCRUM_BOARD_NAME = "Scrum Board";
//    public static final String KANBAN_BOARD_NAME = "Kanban Board";

    private final Context context;

    public String workspaceId;
    public String boardId;

    private String defaultWorkspaceId;

    public static final String KANBAN_STAGE_1_READY_NAME = "Ready";
    public static final String KANBAN_STAGE_NAME_2_ANALYSIS = "Analysis";

    private Workflow kanbanDefaultWorkflow;
    private Workflow scrumDefaultWorkflow;


    public static final int TOTAL_WORKFLOW_NUMBER = 2;

    public static TestContext newInstance() {
        return new TestContext();
    }

    public TestContext(){
        context = Context.newInstance();
    }

    public void registerAllEventHandler(){
        context.registerAllEventHandler();
    }

    public UserRepository getUserRepository() {
        return context.getUserRepository();
    }

    public HomeRepository getHomeRepository() {
        return context.getHomeRepository();
    }

    public DomainEventRepository getStoredEventRepository() {
        return context.getStoredEventRepository();
    }

    public FlowEventRepository getFlowEventRepository() {
        return context.getFlowEventRepository();
    }

    public WorkspaceRepository getWorkspaceRepository() {
        return context.getWorkspaceRepository();
    }
    public BoardRepository getBoardRepository(){
        return context.getBoardRepository();
    }
    public WorkflowRepository getWorkflowRepository(){
        return context.getWorkflowRepository();
    }
    public CardRepository getCardRepository(){
        return context.getCardRepository();
    }



//    public FlowEventHandler getFlowEventHandler() {
//        return flowEventHandler;
//    }
//
//    public WorkflowEventHandler getWorkflowEventHandler(){
//        return workflowEventHandler;
//    }

    public DomainEventBus getDomainEventBus(){
        return context.getDomainEventBus();
    }

    public static UseCase<CreateWorkflowInput, CreateWorkflowOutput>
                newCreateWorkflowUseCase(WorkflowRepository workflowRepository, DomainEventBus eventBus){

        return new CreateWorkflowUseCase(workflowRepository, eventBus);
    }

    public CreateWorkspaceOutput doCreateWorkspaceUseCase(String userId, String workspaceName) {
        return context.doCreateWorkspaceUseCase(userId, workspaceName);
    }

    public Workspace getDefaultWorkspace(){
        return this.getWorkspaceRepository().findAll().get(0);
    }

    public CreateBoardOutput doCreateBoardUseCase(String workspaceId, String boardName) {
        return context.doCreateBoardUseCase(workspaceId, boardName);
    }

    public CreateWorkflowOutput doCreateWorkflowUseCase(String boardId, String name) {
        return context.doCreateWorkflowUseCase(boardId, name);
    }


    public CreateStageOutput doCreateStageUseCase(String workflowId, String stageName, String parentId){
        return context.doCreateStageUseCase(workflowId, stageName, parentId);
    }

    public CreateSwimlaneOutput doCreateSwimlaneUseCase(String workflowId, String laneName, String parentId){

        return context.doCreateSwimlaneUseCase(workflowId, laneName, parentId);
    }

    public CreateCardOutput doCreateCardUseCase(String name, String workflowId, String laneId) {
        return context.doCreateCardUseCase(name, workflowId, laneId);
    }

}

