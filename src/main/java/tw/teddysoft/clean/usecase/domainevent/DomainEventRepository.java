package tw.teddysoft.clean.usecase.domainevent;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;
import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.usecase.repository.Repository;
import tw.teddysoft.clean.domain.usecase.repository.RepositoryPeer;

import java.util.List;

public class DomainEventRepository extends Repository<DomainEvent> {
    public DomainEventRepository(RepositoryPeer peer) {
        super(peer);
    }
}

