package ntut.csie.sslab.account.user.usecase;

import ntut.csie.sslab.account.user.adapter.get.GetUserPresenter;
import ntut.csie.sslab.account.user.adapter.get.UserBasicViewModel;
import ntut.csie.sslab.account.user.usecase.common.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.user.usecase.get.UserDto;
import ntut.csie.sslab.account.user.usecase.get.GetUserUseCase;
import ntut.csie.sslab.account.user.usecase.get.GetUserInput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@Transactional don't add this annotation!
public class GetUserUseCaseTest extends AbstractSpringBootJpaTest {

    String userId;

    @BeforeEach
    public void setUp() {
        super.setUp();
        username += UUID.randomUUID().toString();
        email += UUID.randomUUID().toString();
        userId = registerUser(username, password, email, nickname, role, isThirdParty);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteById(userId);
    }

    @Test
    public void should_success_when_get_user() {
        GetUserUseCase useCase = newGetUserUseCase();
        GetUserInput input = new GetUserInput();
        input.setUserId(userId);
        GetUserPresenter presenter = new GetUserPresenter();

        UserBasicViewModel viewModel = presenter.buildViewModel(useCase.execute(input));

        assertEquals(ExitCode.SUCCESS, viewModel.getExitCode());
        UserDto user = viewModel.getUser();
        assertEquals(userId, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(nickname, user.getNickname());
        assertEquals(role, user.getRole());
    }

    @Test
    public void should_return_message_when_get_user_fail() {
        String userId = "failId";
        GetUserUseCase useCase = newGetUserUseCase();
        GetUserInput input = new GetUserInput();
        input.setUserId(userId);
        GetUserPresenter presenter = new GetUserPresenter();

        UserBasicViewModel viewModel = presenter.buildViewModel(useCase.execute(input));

        assertEquals(ExitCode.FAILURE, viewModel.getExitCode());
        assertEquals("Get user failed: user not found, user id = " + userId, viewModel.getMessage());
    }
}
