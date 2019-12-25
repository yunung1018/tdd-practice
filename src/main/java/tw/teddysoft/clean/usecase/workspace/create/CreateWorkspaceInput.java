package tw.teddysoft.clean.usecase.workspace.create;

import tw.teddysoft.clean.domain.usecase.Input;

public interface CreateWorkspaceInput extends Input {

    CreateWorkspaceInput setUserId(String userId);
    CreateWorkspaceInput setWorkspaceName(String workspaceName);

    String getUserId();
    String getWorkspaceName();
}
