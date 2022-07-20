package ntut.csie.sslab.ddd.adapter.presenter;

import ntut.csie.sslab.ddd.usecase.Output;

public interface Giver<D extends Output, M extends ViewModel> {

    M buildViewModel(D data);
}
