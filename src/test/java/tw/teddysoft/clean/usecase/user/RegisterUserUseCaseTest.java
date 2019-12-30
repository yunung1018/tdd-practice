package tw.teddysoft.clean.usecase.user;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workspace.SingleWorkspacePresenter;
import tw.teddysoft.clean.adapter.presenter.user.SingleUserPresenter;
import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.usecase.Context;
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
                Context.USER_LOGIN_ID,
                Context.USER_PASSWORD,
                Context.USER_NAME,
                Context.USER_EMAIL,
                context.getUserRepository(),
                context.getDomainEventBus());

        assertNotNull(output.getUserId());
        assertNotNull(context.getUserRepository().findById(output.getUserId()));
        assertEquals(1, context.getUserRepository().findAll().size());
        assertEquals(Context.USER_LOGIN_ID, context.getUserRepository().findById(output.getUserId()).getLoginId());
        assertEquals(Context.USER_NAME, context.getUserRepository().findById(output.getUserId()).getName());


        context.getStoredEventRepository().findAll().stream().forEach(x -> System.out.println(x.detail()));

        assertEquals(Context.USER_LOGIN_ID, context.getUserRepository().findById(output.getUserId()).getLoginId());

        int lastIndex = context.getStoredEventRepository().findAll().size() - 1;
        assertThat(context.getStoredEventRepository().findAll().get(lastIndex - 1).detail()).startsWith("UserRegistrationSucceeded");
        assertThat(context.getStoredEventRepository().findAll().get(lastIndex).detail()).startsWith("HomeCreated");
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
