package tw.teddysoft.clean.domain.model.user;

import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.Context;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.user.RegisterUserOutput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void registering_a_user_should_generate_a_UserRegistrationSucceeded_event() {

        User user = new User(
                Context.USER_LOGIN_ID,
                Context.USER_PASSWORD,
                Context.USER_NAME,
                Context.USER_EMAIL);

        assertEquals(Context.USER_NAME, user.getName());
        assertEquals(Context.USER_LOGIN_ID, user.getLoginId());
        assertEquals(Context.USER_PASSWORD, user.getPassword());
        assertEquals(Context.USER_EMAIL, user.getEmail());
        assertThat(user.getDomainEvents().size()).isEqualTo(1);
        assertThat(user.getDomainEvents().get(0).detail()).startsWith("UserRegistrationSucceeded");

    }
}
