package ntut.csie.sslab.account.user.adapter.restcontroller;

import ntut.csie.sslab.account.user.adapter.get.GetUserPresenter;
import ntut.csie.sslab.account.user.usecase.get.GetUserInput;
import ntut.csie.sslab.account.user.usecase.get.GetUserUseCase;
import ntut.csie.sslab.account.user.adapter.get.UserBasicViewModel;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserController {
    private final GetUserUseCase getUserUseCase;

    @Autowired
    public GetUserController(GetUserUseCase getUserUseCase){
        this.getUserUseCase = getUserUseCase;
    }

    @GetMapping(path = "${ACCOUNT_PREFIX}/users/{userId}", produces = "application/json")
    public ResponseEntity<UserBasicViewModel> getUser(@PathVariable("userId") String userId){

        GetUserInput input = new GetUserInput();
        input.setUserId(userId);

        GetUserPresenter presenter = new GetUserPresenter();
        UserBasicViewModel viewModel = presenter.buildViewModel(getUserUseCase.execute(input));

        if(viewModel.getExitCode() == ExitCode.SUCCESS){
            return new ResponseEntity<>(viewModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(viewModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
