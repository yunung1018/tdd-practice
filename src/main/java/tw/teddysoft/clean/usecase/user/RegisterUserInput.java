package tw.teddysoft.clean.usecase.user;

import tw.teddysoft.clean.domain.usecase.Input;

public interface RegisterUserInput extends Input {

    RegisterUserInput setLoginId(String loginId);
    RegisterUserInput setPassword(String password);
    RegisterUserInput setName(String name);
    RegisterUserInput setEmail(String email);

    String getLoginId();
    String getPassword();
    String getName();
    String getEmail();
}
