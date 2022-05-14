package interfaces;

public interface LoginController {
    String getLoginForm();
    String getPwForm();
    void setUsernameValid(String text);
    void setPwValid(String text);
}
