package tw.teddysoft.clean.usecase;

import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryBoardRepository;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryWorkflowRepository;
import tw.teddysoft.clean.adapter.gateway.workitem.InMemoryCardRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.lane.SingleStagePresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workflow.SingleWorkflowPresenter;
import tw.teddysoft.clean.adapter.presenter.card.SingleCardPresenter;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.card.create.CreateCardInput;
import tw.teddysoft.clean.usecase.card.create.CreateCardOutput;
import tw.teddysoft.clean.usecase.card.create.CreateCardUseCase;
import tw.teddysoft.clean.usecase.card.create.impl.CreateCardUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.CreateStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.CreateStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.CreateStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.impl.CreateStageUseCaseImpl;
import tw.teddysoft.clean.usecase.card.CardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.CreateSwimlaneInput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.CreateSwimlaneOutput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.CreateSwimlaneUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.impl.CreateSwimlaneUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowInput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.impl.CreateWorkflowUseCaseImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class KanbanTestUtility {

    private WorkflowRepository workflowRepository;
    private BoardRepository boardRepository;
    private CardRepository cardRepository;

    public static final String USER_ID = "USER-8967";
    public static final String WORKSPACE_ID = "000-5678";

    public static final String KANBAN_STAGE_1_READY_NAME = "Ready";
    public static final String KANBAN_STAGE_NAME_2_ANALYSIS = "Analysis";

    private Board kanbanBoard;
    private Board scrumBoard;

    private Workflow kanbanDefaultWorkflow;
    private Workflow scrumDefaultWorkflow;

    public static final int TOTAL_WORKFLOW_NUMBER = 2;

    public KanbanTestUtility(){
        this(new InMemoryBoardRepository(), new InMemoryWorkflowRepository(), new InMemoryCardRepository());
    }

    public KanbanTestUtility(BoardRepository boardRepository,
                             WorkflowRepository workflowRepository,
                             CardRepository cardRepository){

        this.boardRepository = boardRepository;
        this.workflowRepository = workflowRepository;
        this.cardRepository = cardRepository;
    }

    public BoardRepository getBoardRepository(){
        return boardRepository;
    }
    public WorkflowRepository getWorkflowRepository(){
        return workflowRepository;
    }
    public CardRepository getCardRepository(){
        return cardRepository;
    }

    public Board getKanbanBoard(){
        return kanbanBoard;
    }

    public Board getScrumBoard(){
        return scrumBoard;
    }

//    public Stage finFirstStageByName(String name){
//        for(Stage each : stageRepository.findAll()){
//            if (each.getTitle().equals(name))
//                return each;
//        }
//        throw new RuntimeException("Stage not found. Name = " + name );
//    }

    public void createScrumBoardAndStage() {
        scrumBoard = createBoard("Scrum Board");

        CreateWorkflowInput input = CreateWorkflowUseCaseImpl.createInput();
        CreateWorkflowOutput output = new SingleWorkflowPresenter();
        input.setBoardId(scrumBoard.getId());
        input.setWorkflowName("Default Workflow");
        CreateWorkflowUseCase createWorkflowUC = new CreateWorkflowUseCaseImpl(workflowRepository);
        createWorkflowUC.execute(input, output);
        scrumDefaultWorkflow = workflowRepository.findById(output.getWorkflowId());
        scrumBoard.commitWorkflow(output.getWorkflowId());

        create_stage(output.getWorkflowId(), "To Do", null, workflowRepository);
        create_stage(output.getWorkflowId(), "Doing", null, workflowRepository);
        create_stage(output.getWorkflowId(), "Done", null, workflowRepository);
    }


    public void createCardOnScrumBoard(String [] cardTitles) throws WipLimitExceedException {
        System.out.println("==> " + scrumDefaultWorkflow.getStages().get(0).getId());
        createCard(cardTitles, scrumDefaultWorkflow, scrumDefaultWorkflow.getStages().get(0).getId());
    }


    public void createKanbanBoardAndStage() {
        kanbanBoard = createBoard("Kanban Board");

        CreateWorkflowInput input = CreateWorkflowUseCaseImpl.createInput();
        CreateWorkflowOutput output = new SingleWorkflowPresenter();
        input.setBoardId(kanbanBoard.getId());
        input.setWorkflowName("Default Workflow");
        CreateWorkflowUseCase createWorkflowUC = new CreateWorkflowUseCaseImpl(workflowRepository);
        createWorkflowUC.execute(input, output);
        kanbanDefaultWorkflow = workflowRepository.findById(output.getWorkflowId());
        kanbanBoard.commitWorkflow(output.getWorkflowId());

        create_stage(output.getWorkflowId(), KANBAN_STAGE_1_READY_NAME, null, workflowRepository);
        create_stage(output.getWorkflowId(), "Analysis", null, workflowRepository);
        create_stage(output.getWorkflowId(), "Development", null, workflowRepository);
        create_stage(output.getWorkflowId(), "Test", null, workflowRepository);
        create_stage(output.getWorkflowId(), "Ready to Deploy", null, workflowRepository);
        create_stage(output.getWorkflowId(), "Deployed", null, workflowRepository);

//        ready = stageRepository.findFirstByName("Ready");
//        analysis = stageRepository.findFirstByName("Analysis");
//        development = stageRepository.findFirstByName("Development");
//        test = stageRepository.findFirstByName("Test");
//        readyToDeploy = stageRepository.findFirstByName("Ready to Deploy");
//        deployed = stageRepository.findFirstByName("Deployed");
    }

    public Board createBoard(String name) {
        Board board = new Board(name, WORKSPACE_ID);
        boardRepository.save(board);
        return board;
    }

    public void createCardOnKanbanBoard(String[] cardTitles) throws WipLimitExceedException {
        createCard(cardTitles, kanbanDefaultWorkflow, kanbanDefaultWorkflow.getStages().get(0).getId());
    }

    private void createCard(String[] cardTitles, Workflow workflow, String laneId) throws WipLimitExceedException {
        for (String each : cardTitles) {
            createCard(workflow.getId(), laneId, each, this.getCardRepository(), this.getWorkflowRepository());
        }
    }

    public Workflow getKanbanDefaultWorkflow() {
        return kanbanDefaultWorkflow;
    }

    public Workflow getScrumDefaultWorkflow() {
        return scrumDefaultWorkflow;
    }

    public static String create_workflow(String boardId, String title, WorkflowRepository repository) {
        CreateWorkflowUseCase createWorkflowUC = new CreateWorkflowUseCaseImpl(repository);

        CreateWorkflowInput input = CreateWorkflowUseCaseImpl.createInput();
        CreateWorkflowOutput output = new SingleWorkflowPresenter();
        input.setBoardId(boardId);
        input.setWorkflowName(title);

        createWorkflowUC.execute(input, output);
        return output.getWorkflowId();
    }


    public static String create_stage(String workflowId, String title, String parentId, WorkflowRepository repository){

        CreateStageUseCase createStageLaneUC = new CreateStageUseCaseImpl(repository);
        CreateStageInput input = CreateStageUseCaseImpl.createInput();
        CreateStageOutput output = new tw.teddysoft.clean.adapter.presenter.kanbanboard.lane.SingleStagePresenter();
        input.setWorkflowId(workflowId);
        input.setTitle(title);
        input.setParentId(parentId);

        createStageLaneUC.execute(input, output);

        return output.getId();
    }


    public static String create_swimlane(String workflowId, String LaneName, String parentId, WorkflowRepository repository){

        CreateSwimlaneUseCase createSwimLaneUC = new CreateSwimlaneUseCaseImpl(repository);

        CreateSwimlaneInput input = CreateSwimlaneUseCaseImpl.createInput();
        CreateSwimlaneOutput output = new SingleStagePresenter();

        input.setWorkflowId(workflowId);
        input.setParentId(parentId);
        input.setTitle(LaneName);

        createSwimLaneUC.execute(input, output);

        return output.getId();
    }



    public static String createCard(String workflowId, String landId, String title, CardRepository carRepository, WorkflowRepository workflowRepository){
        CreateCardOutput output = doCreateCardUseCase(workflowId, landId, title, carRepository, workflowRepository);
        return output.getId();
    }



    public static CreateCardOutput doCreateCardUseCase(String workflowId, String landId, String title, CardRepository cardRepository, WorkflowRepository workflowRepository){

        CreateCardUseCase createCardUseCase = new CreateCardUseCaseImpl(cardRepository, workflowRepository);
        CreateCardInput input = CreateCardUseCaseImpl.createInput() ;
        CreateCardOutput output = new SingleCardPresenter();

        input.setName(title);
        input.setWorkflowId(workflowId);
        input.setLaneId(landId);

        createCardUseCase.execute(input, output);

        return output;
    }

    public String createCardInScrumbaordTodoStage(String title) {
        Workflow workflow = getScrumDefaultWorkflow();
        Lane todoStage = workflow.getStages().get(0);

        CreateCardUseCase createCardUseCase = new CreateCardUseCaseImpl(getCardRepository(), getWorkflowRepository());
        CreateCardInput input = CreateCardUseCaseImpl.createInput() ;
        CreateCardOutput output = new SingleCardPresenter();
        input.setName(title);
        input.setWorkflowId(workflow.getId())
                .setLaneId(todoStage.getId());

        createCardUseCase.execute(input, output);
        return output.getId();
    }

    public Lane getReady() {
        return kanbanDefaultWorkflow.getStages().get(0);
    }

    public Lane getAnalysis() {
        return kanbanDefaultWorkflow.getStages().get(1);
    }

    public Lane getDevelopment() {
        return kanbanDefaultWorkflow.getStages().get(2);
    }

    public Lane getTest() {
        return kanbanDefaultWorkflow.getStages().get(3);
    }

    public Lane getReadyToDeploy() {
        return kanbanDefaultWorkflow.getStages().get(4);
    }

    public Lane getDeployed() {
        return kanbanDefaultWorkflow.getStages().get(5);
    }


//    @Test
//    public static void commit_card_to_workflow(Workflow workflow, Card card, ) {
//        Workflow scrumDefaultWorkflow = util.getScrumDefaultWorkflow();
//        Lane backlogStage = scrumDefaultWorkflow.getStages().get(0);
//
//        Card buyCoffee = new Card("Bue a cup of coffee");
//        util.getCardRepository().save(buyCoffee);
//
//        assertEquals(0, backlogStage.getCommittedCards().size());
//        assertEquals(Card.NOT_ASSIGNED, buyCoffee.getWorkflowId());
//        assertEquals(Card.NOT_ASSIGNED, buyCoffee.getLaneId());
//
//        CommitCardUseCase commitCardUseCase = new CommitCardUseCaseImpl(util.getWorkflowRepository(), util.getCardRepository());
//        CommitCardInput input = CommitCardUseCaseImpl.createInput();
//        CommitCardOutput output = new SingleCardPresenter();
//
//        input.setWorkflowId(scrumDefaultWorkflow.getId())
//                .setLaneId(backlogStage.getId())
//                .setCardId(buyCoffee.getId());
//
//        commitCardUseCase.execute(input, output);
//
//        assertEquals(1, backlogStage.getCommittedCards().size());
//        assertEquals(scrumDefaultWorkflow.getId(), buyCoffee.getWorkflowId());
//        assertEquals(backlogStage.getId(), buyCoffee.getLaneId());
//    }
}

