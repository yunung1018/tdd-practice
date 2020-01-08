package tw.teddysoft.clean.usecase;

import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.FlowEvent;
import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.home.Home;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.domain.model.user.User;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.domain.usecase.repository.Repository;
import tw.teddysoft.clean.usecase.card.create.CreateCardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.CreateStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.CreateSwimlaneOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowInput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;

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

    public Repository<User> getUserRepository() {
        return context.getUserRepository();
    }

    public Repository<Home> getHomeRepository() {
        return context.getHomeRepository();
    }

    public Repository<DomainEvent> getStoredEventRepository() {
        return context.getStoredEventRepository();
    }

    public Repository<FlowEvent> getFlowEventRepository() {
        return context.getFlowEventRepository();
    }

    public Repository<Workspace> getWorkspaceRepository() {
        return context.getWorkspaceRepository();
    }
    public Repository<Board> getBoardRepository(){
        return context.getBoardRepository();
    }
    public Repository<Workflow> getWorkflowRepository(){
        return context.getWorkflowRepository();
    }
    public Repository<Card> getCardRepository(){
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
                newCreateWorkflowUseCase(Repository<Workflow> workflowRepository, DomainEventBus eventBus){

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

