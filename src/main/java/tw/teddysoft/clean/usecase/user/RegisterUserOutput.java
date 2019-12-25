package tw.teddysoft.clean.usecase.user;

import tw.teddysoft.clean.domain.usecase.Output;

public interface RegisterUserOutput extends Output {

    void setUserId(String id);
    String getUserId();
}
