package ntut.csie.sslab.account.users.usecase;

public interface Encrypt {
    public String encrypt(String password);

    public Boolean checkPassword(String password1, String password2);
}
