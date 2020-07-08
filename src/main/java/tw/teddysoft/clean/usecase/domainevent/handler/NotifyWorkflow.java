package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.card.event.CardCreated;
import tw.teddysoft.clean.domain.model.card.event.CardDeleted;
import tw.teddysoft.clean.domain.model.kanbanboard.board.event.WorkflowCommitted;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.domain.usecase.repository.Repository;


public class NotifyWorkflow {

    private Repository<Workflow> workflowRepository;
    private DomainEventBus eventBus;

    public NotifyWorkflow(Repository<Workflow> workflowRepository,
                          DomainEventBus eventBus){

        this.workflowRepository = workflowRepository;
        this.eventBus = eventBus;
    }

    @Subscribe
    public void whenCardCreated(CardCreated event) {
        System.out.println("NotifyWorkflow, event = " + event.detail());

        Workflow workflow = workflowRepository.findById(event.getEntity().getWorkflowId());
        workflow.commitCard(event.getEntity().getId(), event.getLaneId());
        workflowRepository.save(workflow);
        eventBus.postAll(workflow);
    }

    @Subscribe
    public void whenCardDeleted(CardDeleted event) {
        System.out.println("NotifyWorkflow, event = " + event.detail());

        Workflow workflow = workflowRepository.findById(event.getWorkflowId());
        workflow.uncommitCard(event.getCardId(), event.getLaneId());
        workflowRepository.save(workflow);
        eventBus.postAll(workflow);
    }
}
