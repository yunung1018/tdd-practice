package ntut.csie.sslab.account.users.adapter.restcontroller;

import ntut.csie.sslab.account.users.usecase.unregister.in.UnregisterInput;
import ntut.csie.sslab.account.users.usecase.unregister.in.UnregisterUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnregisterController {
    private UnregisterUseCase unregisterUseCase;

    @Autowired
    private void setUnregisterUseCase(UnregisterUseCase unregisterUseCase) {
        this.unregisterUseCase = unregisterUseCase;
    }

    @PutMapping(path = "${ACCOUNT_PREFIX}/users/{userId}/unregister", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommonViewModel> unregisterUser(@PathVariable("userId") String userId, @RequestBody String userInfo){


        String password = "";

        try {
            JSONObject userJSON = new JSONObject(userInfo);
            password = userJSON.getString("password");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        UnregisterInput input = new UnregisterInput();
        input.setUserId(userId);
        input.setPassword(password);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(unregisterUseCase.execute(input));

        if(viewModel.getExitCode() == ExitCode.SUCCESS) {
            return new ResponseEntity<>(viewModel, HttpStatus.OK);
        }

        return new ResponseEntity<>(viewModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
