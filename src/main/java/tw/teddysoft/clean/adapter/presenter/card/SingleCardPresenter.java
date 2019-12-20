package tw.teddysoft.clean.adapter.presenter.card;

import tw.teddysoft.clean.usecase.card.create.CreateCardOutput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardOutput;
import tw.teddysoft.clean.usecase.card.move.MoveCardOutput;

public class SingleCardPresenter implements CreateCardOutput, DeleteCardOutput, MoveCardOutput {
    private String name;
    private boolean hasError = false;
    private String errorMessage = "";
    private String id;

    @Override
    public String getId() {
        return id;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
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
