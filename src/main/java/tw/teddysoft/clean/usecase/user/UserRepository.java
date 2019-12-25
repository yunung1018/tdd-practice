package tw.teddysoft.clean.usecase.user;

import tw.teddysoft.clean.domain.model.kanbanboard.home.Home;
import tw.teddysoft.clean.domain.model.user.User;
import tw.teddysoft.clean.domain.usecase.repository.Repository;
import tw.teddysoft.clean.domain.usecase.repository.RepositoryPeer;

public class UserRepository extends Repository<User> {

    public UserRepository(RepositoryPeer peer) {
        super(peer);
    }
}
