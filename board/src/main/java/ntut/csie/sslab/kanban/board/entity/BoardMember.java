package ntut.csie.sslab.kanban.board.entity;

import ntut.csie.sslab.ddd.model.ValueObject;

public class BoardMember implements ValueObject {
	private final String boardId;
	private final String userId;
	private final BoardMemberType memberType;

	BoardMember(BoardMemberType memberType, String boardId, String userId) {
		this.memberType = memberType;
		this.boardId = boardId;
		this.userId = userId;
	}

	public String getBoardId() {
		return boardId;
	}
	public String getUserId() {
		return userId;
	}

	public BoardMemberType getMemberType() {
		return memberType;
	}

}
