package tw.teddysoft.clean.adapter.gateway.kanbanboard;


import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryWorkspaceRepository implements WorkspaceRepository {

    private List<Workspace> workspaces;

    public InMemoryWorkspaceRepository(){
        workspaces = new ArrayList<Workspace>();
    }

    public List<Workspace> findAll() {
        return workspaces;
    }

    public Workspace findById(String id) {
        for(Workspace each : workspaces){
            if (each.getId().equalsIgnoreCase(id))
                return each;
        }
        throw new RuntimeException("Cannot find workspace with id : " + id);
    }

    public Workspace findFirstByName(String name) {
        for(Workspace each : workspaces){
            if (each.getName().equals(name))
                return each;
        }
        throw new RuntimeException("Cannot find workspace with name : " + name);
    }

    public Workspace save(Workspace arg) {
        if (workspaces.contains(arg))
            return arg;
        else if (workspaces.add(arg))
            return arg;
        else
            return null;
    }

    public boolean remove(Workspace arg) {
        return workspaces.remove(arg);
    }

}
