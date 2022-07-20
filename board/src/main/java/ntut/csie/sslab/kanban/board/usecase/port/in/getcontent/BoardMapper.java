package ntut.csie.sslab.kanban.board.usecase.port.in;

import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.board.entity.BoardMember;
import ntut.csie.sslab.kanban.board.entity.CommittedWorkflow;

import java.util.ArrayList;
import java.util.List;

public class BoardMapper {

	public static BoardDto toDto(Board board) {
		BoardDto dto = new BoardDto();
		dto.setBoardId(board.getBoardId());
		dto.setProjectId(board.getTeamId());
		dto.setTitle(board.getName());
		List<BoardMemberDto> boardMemberDtos = new ArrayList<>();
		for(BoardMember boardMember: board.getBoardMembers()){
			boardMemberDtos.add(BoardMemberMapper.toDto(boardMember));
		}

		List<CommittedWorkflowDto> committedWorkflowDtos = new ArrayList<>();
		for(CommittedWorkflow committedWorkflow : board.getCommittedWorkflows()) {
			committedWorkflowDtos.add(CommittedWorkflowMapper.toDto(committedWorkflow));
		}
		dto.setCommittedWorkflows(committedWorkflowDtos);

		dto.setBoardMembers(boardMemberDtos);
		return dto;
	}

	public static List<BoardDto> toDto(List<Board> boards) {
		List<BoardDto> allBoardDtos = new ArrayList<>();
		for(Board board: boards) {
			allBoardDtos.add(BoardMapper.toDto(board));
		}
		return allBoardDtos;
	}
}
