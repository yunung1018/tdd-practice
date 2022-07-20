package ntut.csie.sslab.kanban.board.adapter.out;

import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.board.entity.BoardMemberType;

import java.util.ArrayList;
import java.util.List;

public class BoardMapper {

	public static List<Board> toDomain(List<BoardData> datas) {
		List<Board> boards = new ArrayList<>();
		datas.forEach(x -> boards.add(toDomain(x)));
		return boards;
	}

	public static Board toDomain(BoardData data) {

		Board board = new Board(data.getTeamId(), data.getBoardId(), data.getName(), "");
		for(BoardMemberData boardMemberData : data.getBoardMemberDatas()) {
			BoardMemberType boardMemberType = BoardMemberType.Member;
			switch (boardMemberData.getMemberType()) {
				case("Manager"):
					boardMemberType = BoardMemberType.Manager;
					break;
				case("Member"):
					boardMemberType = BoardMemberType.Member;
					break;
			}
			board.becameBoardMember(boardMemberType, boardMemberData.getUserId());
		}

		if(data.getCommittedWorkflowDatas() != null)
			for (CommittedWorkflowData committedWorkflowData : data.getCommittedWorkflowDatas()) {
				board.commitWorkflow(committedWorkflowData.getWorkflowId());
			}

		board.clearDomainEvents();
		return board;
	}

	public static BoardData toData(Board board) {
		BoardData data = new BoardData(
				board.getTeamId(),
				board.getBoardId(),
				board.getName());

		data.setCommittedWorkflowDatas(CommittedWorkflowMapper.
				toData(board.getCommittedWorkflows()));

		data.setBoardMemberDatas(BoardMemberMapper.transformToData(board.getBoardMembers()));
		
		return data;
	}
}
