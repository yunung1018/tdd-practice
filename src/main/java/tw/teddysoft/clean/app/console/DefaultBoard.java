package tw.teddysoft.clean.app.console;

import tw.teddysoft.clean.adapter.presenter.kanbanboard.board.SingleStageOfBoardPresenter;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.CreateStageOfBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.CreateStageOfBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.CreateStageOfBoardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.board.impl.CreateStageOfBoardUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.add.AddMiniStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.add.AddMiniStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.add.impl.AddMiniStageUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.update.UpdateMiniStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.update.UpdateMiniStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.update.impl.UpdateMiniStageUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;

public class DefaultBoard {

    private BoardRepository boardRepository;
    private StageRepository stageRepository;
    public static final String SCRUM_BOARD_ID = "1";
    public static final String KANBAN_BOARD_GAME_ID = "2";

    public DefaultBoard(BoardRepository boardRepository, StageRepository stageRepository){
        this.boardRepository = boardRepository;
        this.stageRepository = stageRepository;
    }

    public BoardRepository getBoardRepository(){
        return boardRepository;
    }

    public StageRepository getStageRepository(){
        return stageRepository;
    }

    public void clearRepository(){
        boardRepository.findAll().clear();
        stageRepository.findAll().clear();
    }

    public void createScrumBoardStage() {
        if (stageRepository.findAll().size() > 0)
            return;

        Board board = new Board("Scrum Board");
        boardRepository.save(board);

        CreateStageOfBoardUseCase createStageOfBoardUseCase = new CreateStageOfBoardUseCaseImpl(boardRepository, stageRepository);
        CreateStageOfBoardInput input = CreateStageOfBoardUseCaseImpl.createInput();
        input.setBoardId(board.getId());
        input.setStageName("To Do");
        CreateStageOfBoardOutput output = new SingleStageOfBoardPresenter();
        createStageOfBoardUseCase.execute(input, output);

        input.setStageName("Doing");
        createStageOfBoardUseCase.execute(input, output);

        input.setStageName("Done");
        createStageOfBoardUseCase.execute(input, output);



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

        if (stageRepository.findAll().size() > 0)
            return;


        if (stageRepository.findAll().size() > 0)
            return;

        Board board = new Board("Kanban Board");
        boardRepository.save(board);

        CreateStageOfBoardUseCase createStageOfBoardUseCase = new CreateStageOfBoardUseCaseImpl(boardRepository, stageRepository);
        CreateStageOfBoardInput input = CreateStageOfBoardUseCaseImpl.createInput();
        input.setBoardId(board.getId());
        input.setStageName("Ready");
        CreateStageOfBoardOutput output = new SingleStageOfBoardPresenter();
        createStageOfBoardUseCase.execute(input, output);

        input.setStageName("Analysis");
        createStageOfBoardUseCase.execute(input, output);

        input.setStageName("Development");
        createStageOfBoardUseCase.execute(input, output);

        input.setStageName("Test");
        createStageOfBoardUseCase.execute(input, output);


        input.setStageName("Ready to Deploy");
        createStageOfBoardUseCase.execute(input, output);

        input.setStageName("Deployed");
        createStageOfBoardUseCase.execute(input, output);

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

    private void addDoneMiniStage(String stageId) {
        AddMiniStageUseCase addMiniStageUC = new AddMiniStageUseCaseImpl(stageRepository);
        AddMiniStageInput miniStageInput = AddMiniStageUseCaseImpl.createInput();

        miniStageInput.setMiniStateName("Done");
        miniStageInput.setStageId(stageId);

        addMiniStageUC.execute(miniStageInput, null);
    }

    private void updateMiniStageName(String stageId, String miniStageId) {

        UpdateMiniStageUseCase updateMiniStageUC = new UpdateMiniStageUseCaseImpl(stageRepository);
        UpdateMiniStageInput updateMiniStageInput = UpdateMiniStageUseCaseImpl.createInput();

        updateMiniStageInput.setStageId(stageId);
        updateMiniStageInput.setMiniStageId(miniStageId);
        updateMiniStageInput.setMiniStageName("In Progress");

        updateMiniStageUC.execute(updateMiniStageInput, null);
    }


}
