package ntut.csie.sslab.team.usecase.rename;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.team.entity.team.Team;
import ntut.csie.sslab.team.entity.team.TeamId;
import ntut.csie.sslab.team.entity.team.TeamName;
import ntut.csie.sslab.team.usecase.TeamRepository;

public class RenameTeamUseCaseImpl implements RenameTeamUseCase {
    private final TeamRepository teamRepository;
    private final DomainEventBus domainEventBus;

    public RenameTeamUseCaseImpl(TeamRepository teamRepository, DomainEventBus domainEventBus) {
        this.teamRepository = teamRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsOutput execute(RenameTeamInput input) {
        Team team = teamRepository.findById(TeamId.valueOf(input.getTeamId())).orElse(null);
        CqrsOutput output = CqrsOutput.create();

        if(null == team) {
            output.setExitCode(ExitCode.FAILURE)
                    .setMessage("Rename team failed: team not found, team id = " + input.getTeamId());
            return output;
        }

        team.rename(TeamName.valueOf(input.getName()));

        teamRepository.save(team);
        domainEventBus.postAll(team);

        return output.setId(input.getTeamId()).setExitCode(ExitCode.SUCCESS);
    }
}
