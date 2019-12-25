package tw.teddysoft.clean.domain.model.user.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;

public class UserRegistrationSucceeded extends AbstractDomainEvent {

    private final String loginId;

    public UserRegistrationSucceeded(String id, String name, String loginId) {
        super(id, name);
        this.loginId = loginId;
    }

    @Override
    public String detail() {
        String formatDate = String.format("occurredOn='%1$tY-%1$tm-%1$td %1$tH:%1$tM']", occurredOn());
        String format = String.format(
                "%s[Name='%s', user id='%s'] ",
                this.getClass().getSimpleName(),
                this.getSourceName(), this.getSourceId());
        return format + formatDate;
    }


//    public UserRegistrationSucceeded(Entity entity) {
//        super(entity);
//    }
//
//    @Override
//    public Board getEntity(){
//        return (Board) super.getEntity();
//    }

    public String getLoginId() {
        return loginId;
    }


}
