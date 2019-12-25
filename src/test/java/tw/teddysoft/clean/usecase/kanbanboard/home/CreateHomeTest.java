package tw.teddysoft.clean.usecase.kanbanboard.home;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.home.SingleHomePresenter;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.home.Home;
import tw.teddysoft.clean.domain.usecase.repository.IRepository;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.kanbanboard.home.create.CreateHomeInput;
import tw.teddysoft.clean.usecase.kanbanboard.home.create.CreateHomeOutput;
import tw.teddysoft.clean.usecase.kanbanboard.home.create.CreateHomeUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateHomeTest {

    public static final String USER_ID = "USER_007";
    public static final String Home_NAME = "Teddy's Home";
    private TestContext context;

    @Before
    public void setUp(){
        context = TestContext.newInstance();
        context.registerAllEventHandler();
    }

    @Test
    public void add_a_home() {

        CreateHomeOutput output = doCreateHomeUseCase(context.getHomeRepository(), context.getDomainEventBus(), USER_ID, Home_NAME);

        assertNotNull(output.getHomeId());
        assertNotNull(context.getHomeRepository().findById(output.getHomeId()));
        assertEquals(USER_ID, context.getHomeRepository().findById(output.getHomeId()).getUserId());
        assertEquals(1, context.getHomeRepository().findAll().size());
        assertEquals(Home_NAME, context.getHomeRepository().findById(output.getHomeId()).getName());

        assertThat(context.getStoredEventRepository().findAll().size()).isEqualTo(1);
        assertThat(context.getStoredEventRepository().findAll().get(0).detail()).startsWith("HomeCreated");
    }


    public static CreateHomeOutput doCreateHomeUseCase(
            HomeRepository repository,
            DomainEventBus eventBus,
            String userId,
            String homeName){

        UseCase<CreateHomeInput, CreateHomeOutput> createHomeUC  = new CreateHomeUseCase(repository, eventBus);
        CreateHomeInput input = createHomeUC.createInput();
        CreateHomeOutput output = new SingleHomePresenter();

        input.setUserId(userId);
        input.setHomeName(homeName);

        createHomeUC.execute(input, output);

        return output;
    }


}
