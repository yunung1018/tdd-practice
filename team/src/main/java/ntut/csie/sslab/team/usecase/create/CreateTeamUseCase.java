package ntut.csie.sslab.team.usecase.create;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;

public interface CreateTeamUseCase extends Command<CreateTeamInput, CqrsCommandOutput> {
}
