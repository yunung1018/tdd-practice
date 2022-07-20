package ntut.csie.sslab.account.user.usecase;

import ntut.csie.sslab.account.user.entity.User;
import ntut.csie.sslab.account.user.usecase.common.AbstractSpringBootJpaTest;
import ntut.csie.sslab.account.user.usecase.unregister.UnregisterInput;
import ntut.csie.sslab.account.user.usecase.unregister.UnregisterUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
public class UnregisterUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void should_success_when_unregister_user() {
        String userId = registerUser(username, password, email, nickname, role, isThirdParty);

        UnregisterUseCase unregisterUseCase = newUnregisterUseCase();
        UnregisterInput input = new UnregisterInput();
        input.setUserId(userId);
        input.setPassword(password);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(unregisterUseCase.execute(input));

        assertNotNull(viewModel.getId());
        assertEquals(ExitCode.SUCCESS, viewModel.getExitCode());

        User user = userRepository.findById(viewModel.getId()).get();
        assertFalse(user.isActivated());
    }


}
