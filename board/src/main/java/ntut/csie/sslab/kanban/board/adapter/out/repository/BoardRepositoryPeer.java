package ntut.csie.sslab.kanban.board.adapter.out.repository.springboot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardRepositoryPeer extends CrudRepository<BoardData, String> {
}