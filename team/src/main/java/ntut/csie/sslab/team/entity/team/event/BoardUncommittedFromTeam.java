package ntut.csie.sslab.team.entity.team.event;

import ntut.csie.sslab.ddd.model.common.DateProvider;
import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.team.entity.team.BoardId;
import ntut.csie.sslab.team.entity.team.TeamId;

public class BoardUncommittedFromTeam extends DomainEvent {
    private final TeamId teamId;
    private final BoardId boardId;
    private final String boardName;

    public BoardUncommittedFromTeam(TeamId teamId, BoardId boardId, String boardName) {
        super(DateProvider.now());
        this.teamId = teamId;
        this.boardId = boardId;
        this.boardName = boardName;
    }

    public TeamId teamId() {
        return teamId;
    }

    public BoardId boardId() {
        return boardId;
    }

    public String boardName() {
        return boardName;
    }
}
