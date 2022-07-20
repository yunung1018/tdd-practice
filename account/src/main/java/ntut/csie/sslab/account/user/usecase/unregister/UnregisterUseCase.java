package ntut.csie.sslab.account.user.usecase.unregister;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;

public interface UnregisterUseCase extends Command<UnregisterInput, CqrsCommandOutput> {
}
