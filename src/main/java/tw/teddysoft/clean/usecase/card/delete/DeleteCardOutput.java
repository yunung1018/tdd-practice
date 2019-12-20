package tw.teddysoft.clean.usecase.card.delete;

import tw.teddysoft.clean.domain.usecase.Output;

public interface DeleteCardOutput extends Output {

    String getId();
    String getErrorMessage();
    boolean hasError();

    void setId(String id);
    void setErrorMessage(String message);
    void setHasError(boolean arg);

}
