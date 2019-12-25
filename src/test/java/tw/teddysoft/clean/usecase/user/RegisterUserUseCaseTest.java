package tw.teddysoft.clean.usecase.user;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workspace.SingleWorkspacePresenter;
import tw.teddysoft.clean.adapter.presenter.user.SingleUserPresenter;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceInput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RegisterUserUseCaseTest {

    private TestContext context;

    @Before
    public void setUp(){
        context = TestContext.newInstance();
        context.registerAllEventHandler();
    }

    @Test
    public void register_user() {

        RegisterUserOutput output = doRegisterUserUseCase(
                TestContext.USER_LOGIN_ID,
                TestContext.USER_PASSWORD,
                TestContext.USER_NAME,
                TestContext.USER_EMAIL,
                context.getUserRepository(),
                context.getDomainEventBus());

        assertNotNull(output.getUserId());
        assertNotNull(context.getUserRepository().findById(output.getUserId()));
        assertEquals(1, context.getUserRepository().findAll().size());
        assertEquals(TestContext.USER_LOGIN_ID, context.getUserRepository().findById(output.getUserId()).getLoginId());
        assertEquals(TestContext.USER_NAME, context.getUserRepository().findById(output.getUserId()).getName());

        int lastIndex = context.getStoredEventRepository().findAll().size() - 1;
        assertThat(context.getStoredEventRepository().findAll().get(lastIndex).detail()).startsWith("UserRegistrationSucceeded");
    }


    public static RegisterUserOutput doRegisterUserUseCase(
            String loginId,
            String password,
            String name,
            String email,
            UserRepository repository,
            DomainEventBus eventBus){

        UseCase<RegisterUserInput, RegisterUserOutput> registerUserUC =
                new RegisterUserUseCase(repository, eventBus);

        RegisterUserInput input = registerUserUC.createInput();
        RegisterUserOutput output = new SingleUserPresenter();

        input.setLoginId(loginId);
        input.setPassword(password);
        input.setName(name);
        input.setEmail(email);


        registerUserUC.execute(input, output);

        return output;
    }


}
