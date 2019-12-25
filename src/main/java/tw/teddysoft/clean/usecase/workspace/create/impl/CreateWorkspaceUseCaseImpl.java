package tw.teddysoft.clean.usecase.workspace.create.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.workspace.WorkspaceRepository;
import tw.teddysoft.clean.usecase.workspace.create.CreateWorkspaceInput;
import tw.teddysoft.clean.usecase.workspace.create.CreateWorkspaceOutput;
import tw.teddysoft.clean.usecase.workspace.create.CreateWorkspaceUseCase;

public class CreateWorkspaceUseCaseImpl implements CreateWorkspaceUseCase {

    private WorkspaceRepository repository;

    public CreateWorkspaceUseCaseImpl(WorkspaceRepository repository){
        this.repository = repository;
    }

    @Override
    public void execute(CreateWorkspaceInput input, CreateWorkspaceOutput output) {

        Workspace workspace = new Workspace(input.getWorkspaceName(), input.getUserId());
        repository.save(workspace);

        output.setWorkspaceId(workspace.getId());
    }

    public static CreateWorkspaceInput createInput(){
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
