package ntut.csie.sslab.account.users.adapter.repository;

import ntut.csie.sslab.account.users.entity.User;
import ntut.csie.sslab.account.users.usecase.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryPeer peer;

    public UserRepositoryImpl(UserRepositoryPeer peer) {
        this.peer = peer;
    }

    @Override
    public Optional<User> findById(String id) {
        return peer.findById(id).map(UserMapper::transformToDomain);
    }

    @Override
    public void save(User user) {
        UserData data = UserMapper.transformToData(user);
        peer.save(data);
    }

    @Override
    public void deleteById(String id) {
        peer.deleteById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        UserData userData = peer.getUserByUsername(username);
        if(null == userData) {
            return Optional.ofNullable(null);
        }
        return Optional.ofNullable(UserMapper.transformToDomain(userData));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        UserData userData = peer.getUserByEmail(
                email);
        if(null == userData) {
            return Optional.ofNullable(null);
        }
        return Optional.ofNullable(UserMapper.transformToDomain(userData));
    }
}
