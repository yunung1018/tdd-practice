package ntut.csie.sslab.account.user.adapter.encyption;

import ntut.csie.sslab.account.user.usecase.Encrypt;

public class BCryptImpl implements Encrypt {

    public BCryptImpl() {

    }

    @Override
    public String encrypt(String password) {
        return password;
    }

    @Override
    public Boolean checkPassword(String password1, String password2) {

        return password1.equals(password2);
    }
}
