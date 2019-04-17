package tw.teddysoft.clean.usecase.workitem.add;

import tw.teddysoft.clean.domain.usecase.Output;

public interface CreateWorkItemOutput extends Output {

    String getId();
    String getName();
    String getErrorMessage();
    boolean hasError();

    void setId(String id);
    void setName(String name);
    void setErrorMessage(String message);
    void setHasError(boolean arg);

}
