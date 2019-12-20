package tw.teddysoft.clean.usecase.card.create;

import tw.teddysoft.clean.domain.usecase.Output;

public interface CreateCardOutput extends Output {

    String getId();
    String getErrorMessage();
    boolean hasError();

    void setId(String id);
    void setErrorMessage(String message);
    void setHasError(boolean arg);

}
