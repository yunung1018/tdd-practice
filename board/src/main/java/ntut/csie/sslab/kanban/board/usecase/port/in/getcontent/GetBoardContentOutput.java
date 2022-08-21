package ntut.csie.sslab.kanban.board.usecase.port.in.getcontent;

import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.CardDto;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.WorkflowDto;

import java.util.ArrayList;
import java.util.List;

public class GetBoardContentOutput extends CqrsOutput {
    private String boardId;
    private List<BoardMemberDto> boardMemberDtos;
    private List<WorkflowDto> workflowDtos;
    private List<CommittedWorkflowDto> committedWorkflowDtos;
    private List<CardDto> cardDtos;

    public GetBoardContentOutput() {
        boardMemberDtos = new ArrayList<>();
        workflowDtos = new ArrayList<>();
        committedWorkflowDtos = new ArrayList<>();
        cardDtos = new ArrayList<>();
    }

    public String getBoardId() {
        return boardId;
    }

    public GetBoardContentOutput setBoardId(String boardId) {
        this.boardId = boardId;
        return this;
    }

    public List<BoardMemberDto> getBoardMemberDtos() {
        return boardMemberDtos;
    }

    public GetBoardContentOutput setBoardMemberDtos(List<BoardMemberDto> boardMemberDtos) {
        this.boardMemberDtos = boardMemberDtos;
        return this;
    }

    public List<WorkflowDto> getWorkflowDtos() {
        return workflowDtos;
    }

    public GetBoardContentOutput setWorkflowDtos(List<WorkflowDto> workflowDtos) {
        this.workflowDtos = workflowDtos;
        return this;
    }
    public List<CommittedWorkflowDto> getCommittedWorkflowDtos() {
        return committedWorkflowDtos;
    }

    public GetBoardContentOutput setCommittedWorkflowDtos(List<CommittedWorkflowDto> committedWorkflowDtos) {
        this.committedWorkflowDtos = committedWorkflowDtos;
        return this;
    }

    public List<CardDto> getCardDtos() {
        return cardDtos;
    }

    public GetBoardContentOutput setCardDtos(List<CardDto> cardDtos) {
        this.cardDtos = cardDtos;
        return this;
    }
}
