package ntut.csie.sslab.team.entity.team.event;

import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.ddd.model.common.DateProvider;
import ntut.csie.sslab.team.entity.team.TeamId;
import ntut.csie.sslab.team.entity.team.TeamName;


public class TeamRenamed extends DomainEvent {
    private final TeamId teamId;
    private final TeamName name;

    public TeamRenamed(TeamId id, TeamName name) {
        super(DateProvider.now());
        teamId = id;
        this.name = name;
    }

    public TeamId teamId() {
        return teamId;
    }

    public TeamName name() {
        return name;
    }
}
