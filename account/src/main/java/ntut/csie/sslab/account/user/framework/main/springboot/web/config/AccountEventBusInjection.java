package ntut.csie.sslab.account.user.framework.main.config;


import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBusAdapter;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("AccountEventBusInjection")
public class AccountEventBusInjection {

    @Bean(name="accountEventBus")
    public DomainEventBus eventBus() {
        return new GoogleEventBusAdapter();
    }

}