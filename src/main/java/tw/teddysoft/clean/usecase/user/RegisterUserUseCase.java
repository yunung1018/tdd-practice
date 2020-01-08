package tw.teddysoft.clean.usecase.user;

import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.user.User;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.domain.usecase.repository.Repository;

public class RegisterUserUseCase implements UseCase<RegisterUserInput, RegisterUserOutput> {

    private Repository<User> repository;
    private DomainEventBus eventBus;

    public RegisterUserUseCase(Repository repository, DomainEventBus eventBus){
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(RegisterUserInput input, RegisterUserOutput output) {
        User user = new User(input.getLoginId(), input.getPassword(), input.getName(), input.getEmail());
        repository.save(user);
        eventBus.postAll(user);

        output.setUserId(user.getId());
    }

    @Override
    public RegisterUserInput createInput(){
        return new RegisterUserInputImpl();
    }

    private static class RegisterUserInputImpl implements RegisterUserInput {

        private String loginId;
        private String password;
        private String name;
        private String email;

        @Override
        public RegisterUserInput setLoginId(String loginId) {
            this.loginId = loginId;
            return this;
        }

        @Override
        public RegisterUserInput setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public RegisterUserInput setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public RegisterUserInput setEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public String getLoginId() {
            return loginId;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getEmail() {
            return email;
        }
    }

}
