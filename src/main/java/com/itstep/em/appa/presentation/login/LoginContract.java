package com.itstep.em.appa.presentation.login;

import android.widget.EditText;

import com.itstep.em.appa.base.BasePresenter;
import com.itstep.em.appa.base.BaseView;


@SuppressWarnings("WeakerAccess")
public interface LoginContract {

    interface View extends BaseView<Presenter>{
            void showSuccessfulMessage(String message);
            void showFailedMessage(String message);
            void navigateTo(String email);
    }

    interface Presenter extends BasePresenter{
            boolean validateLoginFields(EditText[] fields);
    }

}
