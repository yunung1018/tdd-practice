package ntut.csie.sslab.team.usecase.create;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.team.entity.team.Team;
import ntut.csie.sslab.team.entity.team.TeamBuilder;
import ntut.csie.sslab.team.usecase.TeamRepository;

public class CreateTeamUseCaseImpl implements CreateTeamUseCase {
    private final TeamRepository teamRepository;
    private final DomainEventBus domainEventBus;

    public CreateTeamUseCaseImpl(TeamRepository teamRepository, DomainEventBus domainEventBus) {
        this.teamRepository = teamRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsOutput execute(CreateTeamInput input) {
        Team team = TeamBuilder.newInstance()
                .teamName(input.getTeamName())
                .userId(input.getUserId())
                .build();

        teamRepository.save(team);
        domainEventBus.postAll(team);

        return CqrsOutput.create().setId(team.getId().toString()).setExitCode(ExitCode.SUCCESS);
    }

}
