package tw.teddysoft.clean.domain.model.user;

import tw.teddysoft.clean.domain.model.AggregateRoot;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.user.event.UserRegistrationSucceeded;

public class User extends AggregateRoot {

    private String loginId;
    private String password;
    private String email;

    public User(String loginId, String password, String name, String email) {
        super(name);
        this.loginId = loginId;
        this.password = password;
        this.email = email;

        addDomainEvent(new UserRegistrationSucceeded(this.getId(), this.getName(), this.getLoginId()));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }
}
