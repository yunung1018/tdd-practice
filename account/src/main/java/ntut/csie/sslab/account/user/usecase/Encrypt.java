package ntut.csie.sslab.account.user.usecase;

public interface Encrypt {
    public String encrypt(String password);

    public Boolean checkPassword(String password1, String password2);
}
