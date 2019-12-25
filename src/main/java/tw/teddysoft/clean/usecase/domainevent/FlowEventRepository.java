package tw.teddysoft.clean.usecase.domainevent;

import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.model.FlowEvent;
import tw.teddysoft.clean.domain.usecase.repository.Repository;
import tw.teddysoft.clean.domain.usecase.repository.RepositoryPeer;

public class FlowEventRepository extends Repository<FlowEvent> {
    public FlowEventRepository(RepositoryPeer peer) {
        super(peer);
    }
}

