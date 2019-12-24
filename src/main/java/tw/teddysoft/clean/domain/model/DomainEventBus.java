package tw.teddysoft.clean.domain.model;

import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class DomainEventBus extends EventBus {

        public DomainEventBus(){
            super();
        }

    public void postAll(Entity entity){
        List<DomainEvent> events = new ArrayList(entity.getDomainEvents());
        entity.clearDomainEvents();

        for(DomainEvent each : events){
            post(each);
        }
        events.clear();
    }
}
