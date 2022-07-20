package ntut.csie.sslab.account.user.usecase.get.out;

import ntut.csie.sslab.account.user.usecase.get.UserDto;

import java.util.Optional;

public interface UserQueryRepository {
    Optional<UserDto> findById(String id);
}
