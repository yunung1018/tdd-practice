package ntut.csie.sslab.team.entity.team;

import ntut.csie.sslab.ddd.model.Entity;

public class Board implements Entity<BoardId> {

    private final BoardId boardId;
    private TeamId teamId;
    private ProjectId projectId;
    private String name;

    public Board(BoardId boardId, TeamId teamId, ProjectId projectId, String name) {
       this.boardId = boardId;
       this.name = name;
       this.teamId = teamId;
       this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamId getTeamId() {
        return teamId;
    }

    public ProjectId getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectId projectId) {
        this.projectId = projectId;
    }

    @Override
    public BoardId getId() {
        return boardId;
    }
}
