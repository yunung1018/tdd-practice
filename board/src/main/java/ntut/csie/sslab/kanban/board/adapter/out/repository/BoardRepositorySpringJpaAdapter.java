package ntut.csie.sslab.kanban.board.usecase.port.out;

import ntut.csie.sslab.kanban.board.adapter.out.repository.BoardData;
import ntut.csie.sslab.kanban.board.adapter.out.repository.BoardMapper;
import ntut.csie.sslab.kanban.board.adapter.out.repository.BoardRepositoryPeer;
import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;

import java.util.Optional;

public class BoardRepositorySpringJpaAdapter implements BoardRepository {

    private final BoardRepositoryPeer peer;

    public BoardRepositorySpringJpaAdapter(BoardRepositoryPeer peer){
        this.peer = peer;
    }

    @Override
    public Optional<Board> findById(String id) {
        return peer.findById(id).map(BoardMapper::toDomain);
    }

    @Override
    public void save(Board board) {
        BoardData data = BoardMapper.toData(board);
        peer.save(data);
    }

    @Override
    public void deleteById(String id) {
        peer.deleteById(id);
    }

}
