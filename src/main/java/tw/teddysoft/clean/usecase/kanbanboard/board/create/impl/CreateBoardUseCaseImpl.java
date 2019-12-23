package tw.teddysoft.clean.usecase.kanbanboard.board.create.impl;

import tw.teddysoft.clean.adapter.presenter.kanbanboard.workflow.SingleWorkflowPresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workspace.SingleWorkspacePresenter;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowInput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.impl.CreateWorkflowUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;

public class CreateBoardUseCaseImpl implements CreateBoardUseCase {

    private WorkspaceRepository workspaceRepository;
    private BoardRepository boardRepository;
    private CreateWorkflowUseCase createWorkflowUC;

    public CreateBoardUseCaseImpl(WorkspaceRepository workspaceRepository,
                                  BoardRepository boardRepository,
                                    CreateWorkflowUseCase createWorkflowUC){

        this.workspaceRepository = workspaceRepository;
        this.boardRepository = boardRepository;
        this.createWorkflowUC = createWorkflowUC;
    }

    @Override
    public void execute(CreateBoardInput input, CreateBoardOutput output) {
//        Board board = new Board(input.getBoardName(), input.getWorkspaceId());
//        boardRepository.save(board);

        Board board = createBoard(input);
        createWorkflow(board.getId(), "Default Workflow");

//        CreateWorkflowInput createWorkflowInput = CreateWorkflowUseCaseImpl.createInput();
//        CreateWorkflowOutput createWorkflowOutput = new SingleWorkflowPresenter();
//        createWorkflowInput.setBoardId(board.getId()).setWorkflowName("Default Workflow");
//        createWorkflowUC.execute(createWorkflowInput, createWorkflowOutput);
//
//        Workspace workspace = workspaceRepository.findById(input.getWorkspaceId());
//        workspace.commitBoard(board.getId());
//        workspaceRepository.save(workspace);

        commitBoardToWorkspace(input.getWorkspaceId(), board.getId());

        output.setBoardId(board.getId());
        output.setBoardName(board.getName());
    }

    public static CreateBoardInput createInput(){
        return new CreateBoardInputImpl();
    }

    private Board createBoard(CreateBoardInput input){
        Board board = new Board(input.getBoardName(), input.getWorkspaceId());
        boardRepository.save(board);
        return board;
    }

    private void createWorkflow(String boardId, String workflowName){
        CreateWorkflowInput createWorkflowInput = CreateWorkflowUseCaseImpl.createInput();
        CreateWorkflowOutput createWorkflowOutput = new SingleWorkflowPresenter();
        createWorkflowInput.setBoardId(boardId).setWorkflowName(workflowName);
        createWorkflowUC.execute(createWorkflowInput, createWorkflowOutput);
    }

    private void commitBoardToWorkspace(String workspaceId, String boardId){
        Workspace workspace = workspaceRepository.findById(workspaceId);
        workspace.commitBoard(boardId);
        workspaceRepository.save(workspace);
    }


    private static class CreateBoardInputImpl implements CreateBoardInput {
        private String workspaceId;
        private String boardName;

        @Override
        public CreateBoardInput setBoardName(String boardName) {
            this.boardName = boardName;
            return this;
        }

        @Override
        public String getBoardName() {
            return boardName;
        }

        @Override
        public CreateBoardInput setWorkspaceId(String workspaceId) {
            this.workspaceId = workspaceId;
            return this;
        }

        @Override
        public String getWorkspaceId() {
            return workspaceId;
        }

    }

}
