package tw.teddysoft.clean.app.console;

import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;

public class DefaultWorkspace {

    private WorkspaceRepository workspaceRepository;
    private BoardRepository boardRepository;
    private WorkflowRepository workflowRepository;
    public static final String WORKSPACE_ID = "000-5678";
    public static final String SCRUM_BOARD_ID = "1";
    public static final String KANBAN_BOARD_GAME_ID = "2";

    public DefaultWorkspace(WorkspaceRepository workspaceRepository, BoardRepository boardRepository, WorkflowRepository workflowRepository){
        this.workspaceRepository = workspaceRepository;
        this.boardRepository = boardRepository;
        this.workflowRepository = workflowRepository;
    }

    public BoardRepository getBoardRepository(){
        return boardRepository;
    }

    public WorkflowRepository getWorkflowRepository(){
        return workflowRepository;
    }

    public void clearRepository(){
        workspaceRepository.findAll().clear();
        boardRepository.findAll().clear();
        workflowRepository.findAll().clear();
    }

    public void createScrumBoardStage() {
//        if (workflowRepository.findAll().size() > 0)
//            return;
//
//        Board board = new Board("Scrum Board", WORKSPACE_ID);
//        boardRepository.save(board);
//
//        CreateStageOfBoardUseCase createStageOfBoardUseCase = new CreateStageOfBoardUseCaseImpl(boardRepository, stageRepository);
//        CreateStageOfBoardInput input = CreateStageOfBoardUseCaseImpl.createInput();
//        input.setBoardId(board.getId());
//        input.setStageName("To Do");
//        CreateStageOfBoardOutput output = new SingleStageOfBoardPresenter();
//        createStageOfBoardUseCase.execute(input, output);
//
//        input.setStageName("Doing");
//        createStageOfBoardUseCase.execute(input, output);
//
//        input.setStageName("Done");
//        createStageOfBoardUseCase.execute(input, output);



//        AddStageUseCase addStageUC = new AddStageUseCaseImpl(stageRepository);
//        AddStageInput input = AddStageUseCaseImpl.createInput();
//        input.setStageName("To Do");
//        input.setBoardId(board.getId());
//        addStageUC.execute(input, new SingleStagePresenter());
//
//        input.setStageName("Doing");
//        addStageUC.execute(input, new SingleStagePresenter());
//
//        input.setStageName("Done");
//        addStageUC.execute(input, new SingleStagePresenter());
    }


    public void createKanbanBoardGameStage() {

//        if (workflowRepository.findAll().size() > 0)
//            return;
//
//
//        if (workflowRepository.findAll().size() > 0)
//            return;
//
//        Board board = new Board("Kanban Board", WORKSPACE_ID);
//        boardRepository.save(board);
//
//        CreateStageOfBoardUseCase createStageOfBoardUseCase = new CreateStageOfBoardUseCaseImpl(boardRepository, workflowRepository);
//        CreateStageOfBoardInput input = CreateStageOfBoardUseCaseImpl.createInput();
//        input.setBoardId(board.getId());
//        input.setStageName("Ready");
//        CreateStageOfBoardOutput output = new SingleStageOfBoardPresenter();
//        createStageOfBoardUseCase.execute(input, output);
//
//        input.setStageName("Analysis");
//        createStageOfBoardUseCase.execute(input, output);
//
//        input.setStageName("Development");
//        createStageOfBoardUseCase.execute(input, output);
//
//        input.setStageName("Test");
//        createStageOfBoardUseCase.execute(input, output);
//
//
//        input.setStageName("Ready to Deploy");
//        createStageOfBoardUseCase.execute(input, output);
//
//        input.setStageName("Deployed");
//        createStageOfBoardUseCase.execute(input, output);

//
//        AddStageUseCase addStageUC = new AddStageUseCaseImpl(stageRepository);
//        AddStageInput input = AddStageUseCaseImpl.createInput();
//        AddStageOutput output = new SingleStagePresenter();
//
//        input.setStageName("Ready");
//        input.setBoardId(KANBAN_BOARD_GAME_ID);
//        addStageUC.execute(input, new SingleStagePresenter());
//
//        input.setStageName("Analysis");
//        addStageUC.execute(input,output);
//        updateMiniStageName(output.getStageId(), output.getMiniStageId());
//        addDoneMiniStage(output.getStageId());
//
//        input.setStageName("Development");
//        addStageUC.execute(input, output);
//        updateMiniStageName(output.getStageId(), output.getMiniStageId());
//        addDoneMiniStage(output.getStageId());
//
//        input.setStageName("Test");
//        addStageUC.execute(input, output);
//
//        input.setStageName("Ready to Deploy");
//        addStageUC.execute(input, output);
//
//        input.setStageName("Deployed");
//        addStageUC.execute(input, output);
    }

//    private void addDoneMiniStage(String stageId) {
//        AddMiniStageUseCase addMiniStageUC = new AddMiniStageUseCaseImpl(workflowRepository);
//        AddMiniStageInput miniStageInput = AddMiniStageUseCaseImpl.createInput();
//
//        miniStageInput.setMiniStateName("Done");
//        miniStageInput.setStageId(stageId);
//
//        addMiniStageUC.execute(miniStageInput, null);
//    }
//
//    private void updateMiniStageName(String stageId, String miniStageId) {
//
//        UpdateMiniStageUseCase updateMiniStageUC = new UpdateMiniStageUseCaseImpl(workflowRepository);
//        UpdateMiniStageInput updateMiniStageInput = UpdateMiniStageUseCaseImpl.createInput();
//
//        updateMiniStageInput.setStageId(stageId);
//        updateMiniStageInput.setMiniStageId(miniStageId);
//        updateMiniStageInput.setMiniStageName("In Progress");
//
//        updateMiniStageUC.execute(updateMiniStageInput, null);
//    }


}
