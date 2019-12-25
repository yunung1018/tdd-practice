package tw.teddysoft.clean.usecase.kanbanboard.workspace;

import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.domain.usecase.repository.Repository;
import tw.teddysoft.clean.domain.usecase.repository.RepositoryPeer;

import java.util.List;

public class WorkspaceRepository extends Repository<Workspace> {
    public WorkspaceRepository(RepositoryPeer peer) {
        super(peer);
    }
}
