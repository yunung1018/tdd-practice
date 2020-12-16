package ntut.csie.sslab.kanban.usecase.board;

import java.util.List;

import ntut.csie.sslab.kanban.entity.model.board.Board;
import ntut.csie.sslab.ddd.usecase.AbstractRepository;

public interface BoardRepository extends AbstractRepository<Board, String> {

//	Optional<Board> getBoardById(String id);
	List<Board> getBoardsByUserId(String userId);
	List<Board> getBoardsByProjectId(String projectId);

}
