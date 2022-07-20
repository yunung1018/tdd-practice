package ntut.csie.sslab.kanban.board.usecase.create.in;

import ntut.csie.sslab.ddd.usecase.Input;

public class CreateBoardInput implements Input{
	private String name;
	private String userId;
	private String teamId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
}
