package tw.teddysoft.clean.usecase;

import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryBoardRepository;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryStageRepository;
import tw.teddysoft.clean.adapter.gateway.workitem.InMemoryWorkItemRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.board.SingleStageOfBoardPresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.stage.SingleStagePresenter;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
import tw.teddysoft.clean.domain.model.workitem.WorkItem;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.CreateStageOfBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.CreateStageOfBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.CreateStageOfBoardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.board.impl.CreateStageOfBoardUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.impl.AddStageUseCaseImpl;
import tw.teddysoft.clean.usecase.workitem.WorkItemRepository;

public class KanbanTestUtility {

    private StageRepository stageRepository;
    private BoardRepository boardRepository;
    private WorkItemRepository workItemRepository;

    public static final String KANBAN_STAGE_1_READY_NAME = "Ready";
    public static final String KANBAN_STAGE_NAME_2_ANALYSIS = "Analysis";

    private Board kanbanBoard;
    private Board scrumBoard;

    private Stage ready = null;
    private Stage analysis = null;
    private Stage development = null;
    private Stage test = null;
    private Stage readyToDeploy = null;
    private Stage deployed = null;

    public static final int TOTAL_STAGE_NUMBER = 9;

    public KanbanTestUtility(){
        this(new InMemoryBoardRepository(), new InMemoryStageRepository(), new InMemoryWorkItemRepository());
    }

    public KanbanTestUtility(BoardRepository boardRepository, StageRepository stageRepository, WorkItemRepository workItemRepository){
        this.boardRepository = boardRepository;
        this.stageRepository = stageRepository;
        this.workItemRepository = workItemRepository;
    }

    public BoardRepository getBoardRepository(){
        return boardRepository;
    }
    public StageRepository getStageRepository(){
        return stageRepository;
    }
    public WorkItemRepository getWorkItemRepository(){
        return workItemRepository;
    }

    public Board getKanbanBoard(){
        return kanbanBoard;
    }

    public Board getScrumBoard(){
        return scrumBoard;
    }

    public Stage finFirstStageByName(String name){
        for(Stage each : stageRepository.findAll()){
            if (each.getName().equals(name))
                return each;
        }
        throw new RuntimeException("Stage not found. Name = " + name );
    }

    public void createScrumBoardAndStage() {
        scrumBoard = createBoard("Scrum Board");
        AddStageUseCase addStageUC = new AddStageUseCaseImpl(stageRepository);
        AddStageInput input = AddStageUseCaseImpl.createInput();

        input.setStageName("To Do");
        input.setBoardId(scrumBoard.getId());
        addStageUC.execute(input, new SingleStagePresenter());

        input.setStageName("Doing");
        addStageUC.execute(input, new SingleStagePresenter());

        input.setStageName("Done");
        addStageUC.execute(input, new SingleStagePresenter());
    }


    public void createWorkItemOnScrumBoard(String [] worItemNames) throws WipLimitExceedException {
        createWorkItem(worItemNames, stageRepository.findFirstByName("To Do"));
    }


    public void createKanbanBoardAndStage() {
        kanbanBoard = createBoard("Kanban Board");

        CreateStageOfBoardUseCase createStageOfBoardUseCase = new CreateStageOfBoardUseCaseImpl(boardRepository, stageRepository);
        CreateStageOfBoardInput input = CreateStageOfBoardUseCaseImpl.createInput();
        input.setBoardId(kanbanBoard.getId());
        input.setStageName(KANBAN_STAGE_1_READY_NAME);

        CreateStageOfBoardOutput output = new SingleStageOfBoardPresenter();
        createStageOfBoardUseCase.execute(input, output);

        input.setStageName("Analysis");
        createStageOfBoardUseCase.execute(input, new SingleStageOfBoardPresenter());

        input.setStageName("Development");
        createStageOfBoardUseCase.execute(input, new SingleStageOfBoardPresenter());

        input.setStageName("Test");
        createStageOfBoardUseCase.execute(input, new SingleStageOfBoardPresenter());

        input.setStageName("Ready to Deploy");
        createStageOfBoardUseCase.execute(input, new SingleStageOfBoardPresenter());

        input.setStageName("Deployed");
        createStageOfBoardUseCase.execute(input, new SingleStageOfBoardPresenter());

        ready = stageRepository.findFirstByName("Ready");
        analysis = stageRepository.findFirstByName("Analysis");
        development = stageRepository.findFirstByName("Development");
        test = stageRepository.findFirstByName("Test");
        readyToDeploy = stageRepository.findFirstByName("Ready to Deploy");
        deployed = stageRepository.findFirstByName("Deployed");
    }

    public Board createBoard(String name) {
        Board board = new Board(name);
        boardRepository.save(board);
        return board;
    }

    public void createWorkItemOnKanbanBoard(String[] worItemNames) throws WipLimitExceedException {
        createWorkItem(worItemNames, ready);
    }

    private void createWorkItem(String[] worItemNames, Stage firstStage) throws WipLimitExceedException {
        for (String each : worItemNames) {
            WorkItem workItem = new WorkItem(
                    each,
                    firstStage.getId(),
                    firstStage.getDefaultMiniStage().getId(),
                    firstStage.getDefaultSwimLaneOfMiniStage().getId());

            workItemRepository.save(workItem);

            firstStage.commitWorkItemToSwimLaneById(firstStage.getDefaultSwimLaneOfMiniStage().getId(),
                    workItem.getId());
        }
    }

    public Stage getReady() {
        return ready;
    }

    public Stage getAnalysis() {
        return analysis;
    }

    public Stage getDevelopment() {
        return development;
    }

    public void setDevelopment(Stage development) {
        this.development = development;
    }

    public Stage getTest() {
        return test;
    }

    public Stage getReadyToDeploy() {
        return readyToDeploy;
    }

    public Stage getDeployed() {
        return deployed;
    }
}

