package ntut.csie.sslab.account.user.usecase.get;

import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;

public class GetUserOutput extends CqrsCommandOutput<GetUserOutput> {
    private UserDto userDto;


    public UserDto getUser(){
        return userDto;
    }

    public GetUserOutput setUser(UserDto userDto){
        this.userDto = userDto;
        return this;
    }
}
