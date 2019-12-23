package tw.teddysoft.clean.usecase.kanbanboard.workspace;

import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;

import java.util.List;

public interface WorkspaceRepository {
    List<Workspace> findAll();
    Workspace findById(String id);
    Workspace findFirstByName(String name);
    Workspace save(Workspace arg);
    boolean remove(Workspace arg);
}
