package tw.teddysoft.clean.usecase.kanbanboard.board;

import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.home.Home;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.domain.usecase.repository.Repository;
import tw.teddysoft.clean.domain.usecase.repository.RepositoryPeer;

import java.util.List;

public class BoardRepository extends Repository<Board>  {
    public BoardRepository(RepositoryPeer peer) {
        super(peer);
    }
}
