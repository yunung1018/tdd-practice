package ntut.csie.sslab.kanban.main;

import ntut.csie.sslab.kanban.board.adapter.in.eventbus.NotifyBoardGoogleEventBusAdapter;
import ntut.csie.sslab.kanban.workflow.adapter.in.eventbus.NotifyWorkflowGoogleEventBusAdapter;
import ntut.csie.sslab.kanban.board.adapter.out.repository.BoardRepositoryPeer;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"ntut.csie.sslab.kanban"})
@EntityScan(basePackages={"ntut.csie.sslab.kanban"})

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
)
public class EzKanbanWebMain extends SpringBootServletInitializer implements CommandLineRunner {

    private DomainEventBus domainEventBus;
    private NotifyBoardGoogleEventBusAdapter notifyBoardAdapter;
    private NotifyWorkflowGoogleEventBusAdapter notifyWorkflowGoogleEventBusAdapter;
    private BoardRepositoryPeer boardRepositoryPeer;


    @Autowired
    public void setDomainEventBus(DomainEventBus domainEventBus) {
        this.domainEventBus = domainEventBus;
    }

    @Autowired
    public void setNotifyBoardAdapter(NotifyBoardGoogleEventBusAdapter notifyBoardGoogleEventBusAdapter) { this.notifyBoardAdapter = notifyBoardGoogleEventBusAdapter; }

    @Autowired
    public void setNotifyWorkflowAdapter(NotifyWorkflowGoogleEventBusAdapter notifyWorkflowGoogleEventBusAdapter) { this.notifyWorkflowGoogleEventBusAdapter = notifyWorkflowGoogleEventBusAdapter; }


    @Autowired
    public void setBoardRepositoryPeer(BoardRepositoryPeer boardRepositoryPeer) {
        this.boardRepositoryPeer = boardRepositoryPeer;
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EzKanbanWebMain.class);
    }

    public static void main(String[] args){
        SpringApplication.run(EzKanbanWebMain.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("EzKanbanWebMain run");

        domainEventBus.register(notifyBoardAdapter);
        domainEventBus.register(notifyWorkflowGoogleEventBusAdapter);
    }
}
