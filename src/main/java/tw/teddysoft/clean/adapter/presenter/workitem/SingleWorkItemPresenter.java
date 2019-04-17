package tw.teddysoft.clean.adapter.presenter.workitem;

import tw.teddysoft.clean.usecase.workitem.add.CreateWorkItemOutput;

public class SingleWorkItemPresenter implements CreateWorkItemOutput {
    private String name;
    private boolean hasError = false;
    private String errorMessage = "";
    private String id;

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasError() {
        return hasError;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
