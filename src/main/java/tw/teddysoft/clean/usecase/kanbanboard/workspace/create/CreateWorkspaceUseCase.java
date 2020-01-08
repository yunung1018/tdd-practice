package tw.teddysoft.clean.usecase.kanbanboard.workspace.create;

import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.domain.usecase.repository.Repository;

public class CreateWorkspaceUseCase implements UseCase<CreateWorkspaceInput, CreateWorkspaceOutput> {

    private Repository<Workspace> repository;

    public CreateWorkspaceUseCase(Repository repository){
        this.repository = repository;
    }

    @Override
    public void execute(CreateWorkspaceInput input, CreateWorkspaceOutput output) {

        Workspace workspace = new Workspace(input.getWorkspaceName(), input.getUserId());
        repository.save(workspace);

        output.setWorkspaceId(workspace.getId());
    }

    @Override
    public CreateWorkspaceInput createInput(){
        return new CreateWorkspaceInputImpl();
    }

    private static class CreateWorkspaceInputImpl implements CreateWorkspaceInput {

        private String userId;
        private String workspaceName;

        @Override
        public CreateWorkspaceInput setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public CreateWorkspaceInput setWorkspaceName(String workspaceName) {
            this.workspaceName = workspaceName;
            return this;
        }

        @Override
        public String getUserId() {
            return userId;
        }

        @Override
        public String getWorkspaceName() {
            return workspaceName;
        }
    }
}
