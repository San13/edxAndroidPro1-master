package edu.galileo.mvp;

/**
 * Created by san on 10/18/17.
 */

public interface LoginView {
    void showProgress(boolean showProgress);
    void setUsernameError(int messageResId);
    void setPasswordError(int messageResId);
}
