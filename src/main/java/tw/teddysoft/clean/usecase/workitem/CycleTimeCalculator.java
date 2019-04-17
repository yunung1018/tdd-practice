package tw.teddysoft.clean.usecase.workitem;

import tw.teddysoft.clean.domain.model.FlowEvent;
import tw.teddysoft.clean.domain.model.PersistentDomainEvent;
import tw.teddysoft.clean.domain.model.workitem.event.WorkItemMovedOut;
import tw.teddysoft.clean.usecase.domainevent.DomainEventRepository;

import java.util.*;

public class CycleTimeCalculator {

    private  DomainEventRepository<FlowEvent> flowEventRepository;

    public CycleTimeCalculator(DomainEventRepository<FlowEvent> flowEventRepository) {
        this.flowEventRepository = flowEventRepository;
    }

    public List<FlowEntryPair> getCycleTime(String workItemId) {
        List<FlowEntryPair> flowEntryPairs = new ArrayList<>();
        Stack<FlowEvent> stack = new Stack<>();

        for (FlowEvent each : flowEventRepository.findAll()){
            if (!each.detail().contains(workItemId))
                continue;;

            System.out.println(each.detail());
            if (stack.empty()){
                stack.push(each);
            }
            else{
                FlowEvent movedIn = stack.pop();
                flowEntryPairs.add(new FlowEntryPair(movedIn, each));
            }
        }

//        if(!stack.empty()){
//            FlowEvent movedIn = stack.pop();
//            flowEntryPairs.add(new FlowEntryPair(movedIn,
//                    new WorkItemMovedOut(
//                            movedIn.getSourceId(),
//                            movedIn.getSourceName(),
//                            movedIn.getStageId(),
//                            movedIn.getMiniStageId(),
//                            movedIn.getSwimLaneId(),
//                            movedIn.getWorkItemId())));
//        }

        return Collections.unmodifiableList(flowEntryPairs);
    }
}
