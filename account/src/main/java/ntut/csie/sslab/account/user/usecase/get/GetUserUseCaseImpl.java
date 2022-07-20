package ntut.csie.sslab.account.user.usecase.get;

import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

public class GetUserUseCaseImpl implements GetUserUseCase {
    private final UserQueryRepository userRepository;

    public GetUserUseCaseImpl(UserQueryRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public GetUserOutput execute(GetUserInput input) {
        UserDto user = userRepository.findById(input.getUserId()).orElse(null);
        GetUserOutput output = new GetUserOutput();

        if (null == user){
            output.setId(input.getUserId())
                    .setExitCode(ExitCode.FAILURE)
                    .setMessage("Get user failed: user not found, user id = " + input.getUserId());
        }
        else {
            output.setUser(user).setExitCode(ExitCode.SUCCESS);
        }

        return output;
    }

}
