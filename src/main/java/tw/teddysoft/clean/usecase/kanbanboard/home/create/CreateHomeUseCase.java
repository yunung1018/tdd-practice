package tw.teddysoft.clean.usecase.kanbanboard.home.create;

import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.home.Home;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.domain.usecase.GenericRepository;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.usecase.kanbanboard.home.create.CreateHomeInput;
import tw.teddysoft.clean.usecase.kanbanboard.home.create.CreateHomeOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceInput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;

public class CreateHomeUseCase implements UseCase<CreateHomeInput, CreateHomeOutput> {

    private GenericRepository<Home> repository;
    private DomainEventBus eventBus;

    public CreateHomeUseCase(GenericRepository<Home> repository, DomainEventBus eventBus){
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(CreateHomeInput input, CreateHomeOutput output) {
        Home home = new Home(input.getHomeName(), input.getUserId());
        repository.save(home);
        eventBus.postAll(home);

        output.setHomeId(home.getId());
    }

    @Override
    public CreateHomeInput createInput(){
        return new CreateHomeInputImpl();
    }

    private static class CreateHomeInputImpl implements CreateHomeInput {

        private String userId;
        private String homeName;

        @Override
        public CreateHomeInput setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public CreateHomeInput setHomeName(String homeName) {
            this.homeName = homeName;
            return this;
        }

        @Override
        public String getUserId() {
            return userId;
        }

        @Override
        public String getHomeName() {
            return homeName;
        }
    }

}
