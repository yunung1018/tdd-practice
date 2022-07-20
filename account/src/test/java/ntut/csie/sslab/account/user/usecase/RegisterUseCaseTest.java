package ntut.csie.sslab.account.user.command.usecase.user;

import ntut.csie.sslab.account.user.usecase.register.in.RegisterInput;
import ntut.csie.sslab.account.user.usecase.register.in.RegisterUseCase;
import ntut.csie.sslab.account.AbstractSpringBootJpaTest;

import ntut.csie.sslab.account.user.entity.User;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@Transactional
public class RegisterUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void should_success_when_register_user() {
        RegisterUseCase registerUseCase = newRegisterUseCase();
        RegisterInput input = new RegisterInput();
        input.setUsername(username);
        input.setPassword(password);
        input.setEmail(email);
        input.setNickname(nickname);
        input.setRole(role);
        input.setThirdParty(false);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(registerUseCase.execute(input));

        assertNotNull(viewModel.getId());
        assertEquals(ExitCode.SUCCESS, viewModel.getExitCode());
        User user = userRepository.findById(viewModel.getId()).get();
        assertEquals(username, user.getUsername());
        assertTrue(encrypt.checkPassword(password, user.getPassword()));
        assertEquals(email, user.getEmail());
        assertEquals(nickname, user.getNickname());
        assertEquals(role, user.getRole().toString());
    }

    @Test
    public void should_success_when_register_user_using_third_party() {
        RegisterUseCase registerUseCase = newRegisterUseCase();
        RegisterInput input = new RegisterInput();
        input.setUsername(username);
        input.setPassword(password);
        input.setEmail(email);
        input.setNickname(nickname);
        input.setRole(role);
        input.setThirdParty(true);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(registerUseCase.execute(input));

        assertNotNull(viewModel.getId());
        assertEquals(ExitCode.SUCCESS, viewModel.getExitCode());

        User user = userRepository.findById(viewModel.getId()).get();

        assertEquals(username, user.getUsername());
        assertTrue(encrypt.checkPassword(password, user.getPassword()));
        assertEquals(email, user.getEmail());
        assertEquals(nickname, user.getNickname());
        assertEquals(role, user.getRole().toString());
    }

    @Test
    public void should_success_when_register_user_using_third_party_but_user_already_register() {
        should_success_when_register_user_using_third_party();
        RegisterUseCase registerUseCase = newRegisterUseCase();
        RegisterInput input = new RegisterInput();
        input.setUsername(username);
        input.setPassword(password);
        input.setEmail(email);
        input.setNickname(nickname);
        input.setRole(role);
        input.setThirdParty(true);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(registerUseCase.execute(input));

        assertNotNull(viewModel.getId());
        assertEquals(ExitCode.SUCCESS, viewModel.getExitCode());

        User user = userRepository.findById(viewModel.getId()).get();

        assertEquals(username, user.getUsername());
        assertTrue(encrypt.checkPassword(password, user.getPassword()));
        assertEquals(email, user.getEmail());
        assertEquals(nickname, user.getNickname());
        assertEquals(role, user.getRole().toString());
    }

    @Test
    public void should_success_when_register_user_using_third_party_but_user_username_or_email_exist() {
        should_success_when_register_user_using_third_party();
        String fakeEmail = "fake@gmail.com";
        RegisterUseCase registerUseCase = newRegisterUseCase();
        RegisterInput input = new RegisterInput();
        input.setUsername(username);
        input.setPassword(password);
        input.setEmail(fakeEmail);
        input.setNickname(nickname);
        input.setRole(role);
        input.setThirdParty(true);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(registerUseCase.execute(input));

        assertNull(viewModel.getId());
        assertEquals(ExitCode.FAILURE, viewModel.getExitCode());
        assertEquals("Third party registration failed, internal error: username or email already exists", viewModel.getMessage());
    }

    @Test
    public void should_fail_when_register_user_with_same_name() {
        String newEmail = "123@mail.com";
        registerUser(username, password, email, nickname, role, isThirdParty);
        RegisterUseCase registerUseCase = newRegisterUseCase();
        RegisterInput input = new RegisterInput();
        input.setUsername(username);
        input.setPassword(password);
        input.setEmail(newEmail);
        input.setNickname(nickname);
        input.setRole(role);
        input.setThirdParty(false);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(registerUseCase.execute(input));

        assertEquals(ExitCode.FAILURE, viewModel.getExitCode());
        assertEquals("Username:" + username + " already exists.", viewModel.getMessage());
    }

    @Test
    public void should_fail_when_register_user_with_same_email() {
        String newUsername = "123";
        registerUser(username, password, email, nickname, role, isThirdParty);
        RegisterUseCase registerUseCase = newRegisterUseCase();
        RegisterInput input = new RegisterInput();
        input.setUsername(newUsername);
        input.setPassword(password);
        input.setEmail(email);
        input.setNickname(nickname);
        input.setRole(role);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(registerUseCase.execute(input));

        assertEquals(ExitCode.FAILURE, viewModel.getExitCode());
        assertEquals("Email:" + email + " already exists.", viewModel.getMessage());
    }
}
