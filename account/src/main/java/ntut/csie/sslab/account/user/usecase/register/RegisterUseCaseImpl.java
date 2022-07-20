package ntut.csie.sslab.account.user.usecase.register.impl;

import ntut.csie.sslab.account.user.entity.User;
import ntut.csie.sslab.account.user.usecase.Encrypt;
import ntut.csie.sslab.account.user.usecase.UserRepository;

import ntut.csie.sslab.account.user.entity.UserBuilder;
import ntut.csie.sslab.account.user.usecase.register.in.RegisterInput;
import ntut.csie.sslab.account.user.usecase.register.in.RegisterUseCase;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

public class RegisterUseCaseImpl implements RegisterUseCase {
	private UserRepository userRepository;
	private DomainEventBus domainEventBus;
	private Encrypt encrypt;

	public RegisterUseCaseImpl(UserRepository userRepository, DomainEventBus domainEventBus, Encrypt encrypt) {
		this.userRepository = userRepository;
		this.domainEventBus = domainEventBus;
		this.encrypt = encrypt;
	}

	@Override
	public CqrsCommandOutput execute(RegisterInput input) {
		boolean usernameExist = isUsernameExist(input.getUsername());
		boolean emailExist = isEmailExist(input.getEmail());

		CqrsCommandOutput output = CqrsCommandOutput.create();

		if(isThirdPartyAndUserAlreadyExist(input)) {
			String userId = userRepository.getUserByUsername(input.getUsername()).get().getId();
			output.setId(userId);
			output.setExitCode(ExitCode.SUCCESS);
			return output;
		}

		if(isThirdPartyAndUsernameOrEmailExist(input)) {
			output.setExitCode(ExitCode.FAILURE)
					.setMessage("Third party registration failed, internal error: username or email already exists");
			return output;
		}

		if(NotThirdPartyAndUsernameOrEmailExist(input)) {
			String errorMessage = "";
			if(usernameExist) {
				errorMessage = "Username:"+ input.getUsername() + " already exists.";
			}else if(emailExist){
				errorMessage = "Email:"+ input.getEmail() + " already exists.";
			}
			output.setExitCode(ExitCode.FAILURE)
					.setMessage(errorMessage);
			return output;
		}

		User user = UserBuilder.newInstance()
				.username(input.getUsername())
				.email(input.getEmail())
				.password(encrypt.encrypt(input.getPassword()))
				.nickname(input.getNickname())
				.role(input.getRole())
				.build();

		userRepository.save(user);
		domainEventBus.postAll(user);

		return output.setId(user.getId()).setExitCode(ExitCode.SUCCESS);
	}

	private boolean isThirdPartyAndUserAlreadyExist(RegisterInput input) {
		return input.isThirdParty() &&
				isUsernameExist(input.getUsername()) &&
				isEmailExist(input.getEmail());
	}

	private boolean isThirdPartyAndUsernameOrEmailExist(RegisterInput input) {
		return input.isThirdParty() &&
				(isUsernameExist(input.getUsername())
						!= isEmailExist(input.getEmail()));
	}

	private boolean NotThirdPartyAndUsernameOrEmailExist(RegisterInput input) {
		return !input.isThirdParty() &&
				(isUsernameExist(input.getUsername()) ||
						isEmailExist(input.getEmail()));
	}

	private boolean isUsernameExist(String username) {
		return userRepository.getUserByUsername(username).orElse(null) != null;
	}
	private boolean isEmailExist(String email) {
		return userRepository.getUserByEmail(email).orElse(null) != null;
	}

}
