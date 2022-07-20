package ntut.csie.sslab.account.user.framework.main.config;

import ntut.csie.sslab.account.user.usecase.Encrypt;
import ntut.csie.sslab.account.user.usecase.UserRepository;
import ntut.csie.sslab.account.user.usecase.register.RegisterUseCase;
import ntut.csie.sslab.account.user.usecase.register.RegisterUseCaseImpl;
import ntut.csie.sslab.account.user.usecase.unregister.UnregisterUseCase;
import ntut.csie.sslab.account.user.usecase.unregister.UnregisterUseCaseImpl;
import ntut.csie.sslab.account.user.usecase.get.UserQueryRepository;
import ntut.csie.sslab.account.user.usecase.get.GetUserUseCase;
import ntut.csie.sslab.account.user.usecase.get.GetUserUseCaseImpl;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("AccountUseCaseInjection")
public class UseCaseInjection {

    private DomainEventBus eventBus;
    private UserRepository userRepository;
    private UserQueryRepository userQueryRepository;
    private Encrypt encrypt;

    @Autowired
    public void setEventBus(DomainEventBus eventBus) { this.eventBus = eventBus; }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserQueryRepository(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    @Autowired
    public void setEncrypt(Encrypt encrypt) {
        this.encrypt = encrypt;
    }

    @Bean
    public RegisterUseCase registerUseCase(){
        return new RegisterUseCaseImpl(userRepository, eventBus, encrypt);
    }

    @Bean
    public UnregisterUseCase unregisterUseCase(){
        return new UnregisterUseCaseImpl(userRepository, eventBus, encrypt);
    }

    @Bean
    public GetUserUseCase getUserUseCase() {
        return new GetUserUseCaseImpl(userQueryRepository);
    }

}
