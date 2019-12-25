package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.board.event.WorkflowCommitted;
import tw.teddysoft.clean.domain.model.kanbanboard.home.Home;
import tw.teddysoft.clean.domain.model.kanbanboard.home.event.HomeCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.domain.model.user.event.UserRegistrationSucceeded;
import tw.teddysoft.clean.usecase.kanbanboard.home.HomeRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;

import java.util.HashSet;


public class UserEventHandler {

    private HomeRepository homeRepository;
    private DomainEventBus eventBus;

    public UserEventHandler(HomeRepository homeRepository, DomainEventBus eventBus){
        this.homeRepository = homeRepository;
        this.eventBus = eventBus;
    }

    @Subscribe
    public void handleEvent(UserRegistrationSucceeded domainEvent) {
        System.out.println("UserEventHandler, event = " + domainEvent.detail());

        Home home = new Home(domainEvent.getSourceName(), domainEvent.getSourceId());
        homeRepository.save(home);
        eventBus.postAll(home);
    }
}
