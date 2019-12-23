package tw.teddysoft.clean.usecase.kanbanboard.board;

import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;

import java.util.List;

public interface BoardRepository {
    List<Board> findAll();
    Board findById(String id);
    Board findFirstByName(String name);
    Board save(Board arg);
    boolean remove(Board arg);
}
