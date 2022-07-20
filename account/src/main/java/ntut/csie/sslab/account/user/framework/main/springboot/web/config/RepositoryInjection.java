package ntut.csie.sslab.account.user.framework.main.config;

import ntut.csie.sslab.account.user.adapter.encyption.BCryptImpl;
import ntut.csie.sslab.account.user.adapter.repository.UserRepositoryImpl;
import ntut.csie.sslab.account.user.adapter.repository.UserRepositoryPeer;
import ntut.csie.sslab.account.user.usecase.Encrypt;
import ntut.csie.sslab.account.user.usecase.UserRepository;
import ntut.csie.sslab.account.user.adapter.repository.UserQueryRepositoryImpl;
import ntut.csie.sslab.account.user.usecase.get.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;


@PropertySource(value = "classpath:/application.properties")
@Configuration("AccountRepositoryInjection")
public class RepositoryInjection {
    private UserRepositoryPeer userRepositoryPeer;

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public RepositoryInjection(UserRepositoryPeer userRepositoryPeer, JdbcTemplate jdbcTemplate){
        this.userRepositoryPeer = userRepositoryPeer;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public UserRepository userRepository(){ return new UserRepositoryImpl(userRepositoryPeer); }

    @Bean
    public UserQueryRepository userQueryRepository(){ return new UserQueryRepositoryImpl(jdbcTemplate); }

    @Bean
    public Encrypt encrypt(){ return new BCryptImpl();}


}
