package ntut.csie.sslab.account.user.usecase.register.in;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;


public interface RegisterUseCase extends Command<RegisterInput, CqrsCommandOutput> {
}
