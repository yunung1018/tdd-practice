package ntut.csie.sslab.kanban.board.adapter.presenter.getcontent;

import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.board.usecase.port.in.BoardMemberDto;
import ntut.csie.sslab.kanban.usecase.workflow.WorkflowDto;

import java.util.List;

public class BoardContentBasicViewModel extends CommonViewModel {
    private String boardId;
    private List<WorkflowDto> workflows;
    private List<BoardMemberDto> boardMembers;

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public List<WorkflowDto> getWorkflows() {
        return workflows;
    }

    public void setWorkflows(List<WorkflowDto> workflows) {
        this.workflows = workflows;
    }

    public List<BoardMemberDto> getBoardMembers() {
        return boardMembers;
    }

    public void setBoardMembers(List<BoardMemberDto> boardMembers) {
        this.boardMembers = boardMembers;
    }
}
