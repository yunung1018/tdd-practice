package tw.teddysoft.clean.domain.model.user;

import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.user.RegisterUserOutput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void registering_a_user_should_generate_a_UserRegistrationSucceeded_event() {

        User user = new User(
                TestContext.USER_LOGIN_ID,
                TestContext.USER_PASSWORD,
                TestContext.USER_NAME,
                TestContext.USER_EMAIL);

        assertEquals(TestContext.USER_NAME, user.getName());
        assertEquals(TestContext.USER_LOGIN_ID, user.getLoginId());
        assertEquals(TestContext.USER_PASSWORD, user.getPassword());
        assertEquals(TestContext.USER_EMAIL, user.getEmail());
        assertThat(user.getDomainEvents().size()).isEqualTo(1);
        assertThat(user.getDomainEvents().get(0).detail()).startsWith("UserRegistrationSucceeded");

    }
}
