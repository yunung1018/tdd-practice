package tw.teddysoft.clean.usecase.kanbanboard.home.create;

import tw.teddysoft.clean.domain.usecase.Input;

public interface CreateHomeInput extends Input {

    CreateHomeInput setUserId(String userId);
    CreateHomeInput setHomeName(String workspaceName);

    String getUserId();
    String getHomeName();
}
