package ntut.csie.sslab.account.users.adapter.restcontroller;

import ntut.csie.sslab.account.users.usecase.register.in.RegisterInput;
import ntut.csie.sslab.account.users.usecase.register.in.RegisterUseCase;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RegisterController {
    private final RegisterUseCase registerUseCase;

    @Autowired
    public RegisterController(RegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase;
    }

    @PostMapping(path = "${ACCOUNT_PREFIX}/users/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommonViewModel> registerUser(@RequestBody String userInfo){

        String username = "";
        String password = "";
        String email= "";
        String nickname = "";
        String role = "";
        boolean isThirdParty = false;

        try {
            JSONObject userJSON = new JSONObject(userInfo);

            username = userJSON.getString("username");
            password = userJSON.getString("password");
            email = userJSON.getString("email");
            nickname = userJSON.getString("nickname");
            role = userJSON.getString("role");
            isThirdParty = userJSON.getBoolean("isThirdParty");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RegisterInput input = new RegisterInput();
        input.setUsername(username);
        input.setPassword(password);
        input.setEmail(email);
        input.setNickname(nickname);
        input.setRole(role);
        input.setThirdParty(isThirdParty);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(registerUseCase.execute(input));

        if(viewModel.getExitCode() == ExitCode.SUCCESS) {
            return new ResponseEntity<>(viewModel, HttpStatus.OK);
        }

        return new ResponseEntity<>(viewModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
