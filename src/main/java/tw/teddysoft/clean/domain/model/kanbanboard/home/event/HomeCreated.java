package tw.teddysoft.clean.domain.model.kanbanboard.home.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.home.Home;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;

public class HomeCreated extends AbstractDomainEvent {

    private String userId;

    public HomeCreated(String homeId, String userId, String name){
        super(homeId, name);
        this.userId = userId;
    }

//    public HomeCreated(Entity entity) {
//        super(entity);
//    }

    @Override
    public Home getEntity(){
        return (Home) super.getEntity();
    }


    public String getUserId() {
        return userId;
    }

    public String getHomeId(){
        return this.getSourceId();
    }

    public String getHomeName(){
        return this.getSourceName();
    }


    @Override
    public String detail() {
        String formatDate = String.format("occurredOn='%1$tY-%1$tm-%1$td %1$tH:%1$tM']", occurredOn());
        String format = String.format(
                "%s[Name='%s', user id='%s'] ",
                this.getClass().getSimpleName(),
                this.getSourceName(), this.getUserId());
        return format + formatDate;
    }

}
