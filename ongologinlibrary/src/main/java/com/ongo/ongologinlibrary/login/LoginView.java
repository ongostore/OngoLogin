package com.ongo.ongologinlibrary.login;

import com.ongo.ongologinlibrary.baseclasses.BaseView;

public interface LoginView {
    interface View extends BaseView{
        void loginResponce(LoginResponse loginResponse);
        void errorLoginResult();

    }
    interface Presenter {
        void callLoginApi(String url, int i, String mobilenumberStr, String passwordrStr);

        void callSocialLoginsApi(String url, int i, String mobilenumberStr, String nameStr);

    }
}
