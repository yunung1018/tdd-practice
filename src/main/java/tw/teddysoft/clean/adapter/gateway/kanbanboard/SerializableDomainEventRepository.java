package tw.teddysoft.clean.adapter.gateway.kanbanboard;


import tw.teddysoft.clean.domain.model.PersistentDomainEvent;
import tw.teddysoft.clean.usecase.domainevent.DomainEventRepository;

import java.util.ArrayList;
import java.util.List;

public class SerializableDomainEventRepository implements DomainEventRepository<PersistentDomainEvent> {
    public final String DOMAIN_EVENT_STORED_FILE_NAME = "domain-event-repository.ser";
    public final String FLOW_EVENT_STORED_FILE_NAME = "flow-event-repository.ser";

    private final String fileName;
    private List<PersistentDomainEvent> events;

    public SerializableDomainEventRepository(String fileName){
        this.fileName = fileName;
        events = new ArrayList<PersistentDomainEvent>();

        if (SerializationUtil.storedFileExists(fileName)){
            events = (List<PersistentDomainEvent>) SerializationUtil.loadFromFile(fileName);
        }
    }

    public String getFileName(){
        return fileName;
    }

    @Override
    public List<PersistentDomainEvent> findAll() {
        return events;
    }

    @Override
    public PersistentDomainEvent save(PersistentDomainEvent arg) {
        if (events.contains(arg)){
            SerializationUtil.saveToFile(fileName, events);
            return arg;
        }
        else if (events.add(arg)){
            SerializationUtil.saveToFile(fileName, events);
            return arg;
        }
        else
            return null;
    }

}
