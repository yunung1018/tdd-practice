package ntut.csie.sslab.account.user.usecase.unregister;

import ntut.csie.sslab.account.user.entity.User;
import ntut.csie.sslab.account.user.usecase.Encrypt;
import ntut.csie.sslab.account.user.usecase.UserRepository;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

public class UnregisterUseCaseImpl implements UnregisterUseCase {
	private UserRepository userRepository;
	private DomainEventBus domainEventBus;
	private Encrypt encrypt;

	public UnregisterUseCaseImpl(UserRepository userRepository, DomainEventBus domainEventBus, Encrypt encrypt) {
		this.userRepository = userRepository;
		this.domainEventBus = domainEventBus;
		this.encrypt = encrypt;
	}

	@Override
	public CqrsOutput execute(UnregisterInput input) {
		User user = userRepository.findById(input.getUserId()).orElse(null);

		CqrsOutput output = CqrsOutput.create();

		if(encrypt.checkPassword(input.getPassword(), user.getPassword())) {
			user.deactivate("");
			output.setId(user.getId());
			output.setExitCode(ExitCode.SUCCESS);
			userRepository.save(user);
			domainEventBus.postAll(user);
		} else {
			output.setExitCode(ExitCode.FAILURE)
					.setMessage("Wrong password.");
		}

		return output;
	}
}
