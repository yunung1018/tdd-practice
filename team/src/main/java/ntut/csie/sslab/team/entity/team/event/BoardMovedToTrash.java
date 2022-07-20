package ntut.csie.sslab.team.entity.team.event;

import ntut.csie.sslab.ddd.model.common.DateProvider;
import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.team.entity.team.BoardId;
import ntut.csie.sslab.team.entity.team.TeamId;

public class BoardMovedToTrash extends DomainEvent {
    private final TeamId teamId;
    private final BoardId boardId;

    public BoardMovedToTrash(TeamId teamId, BoardId boardId) {
        super(DateProvider.now());
        this.teamId = teamId;
        this.boardId = boardId;
    }

    public TeamId teamId() {
        return teamId;
    }

    public BoardId boardId() {
        return boardId;
    }
}
