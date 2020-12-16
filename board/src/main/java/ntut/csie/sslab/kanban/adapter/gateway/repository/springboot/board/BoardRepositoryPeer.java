package ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.board;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BoardRepositoryPeer extends CrudRepository<BoardData, String> {
}