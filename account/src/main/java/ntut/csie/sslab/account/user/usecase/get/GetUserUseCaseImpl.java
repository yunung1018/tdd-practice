package ntut.csie.sslab.account.user.usecase.get.impl;

import ntut.csie.sslab.account.user.usecase.get.in.GetUserInput;
import ntut.csie.sslab.account.user.usecase.get.in.GetUserOutput;
import ntut.csie.sslab.account.user.usecase.get.in.GetUserUseCase;
import ntut.csie.sslab.account.user.usecase.get.out.UserQueryRepository;
import ntut.csie.sslab.account.user.usecase.get.UserDto;
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
