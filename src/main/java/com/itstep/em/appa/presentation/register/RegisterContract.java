package com.itstep.em.appa.presentation.register;

import android.widget.EditText;

import com.itstep.em.appa.base.BasePresenter;
import com.itstep.em.appa.base.BaseView;


@SuppressWarnings("WeakerAccess")
public interface RegisterContract {

    interface View extends BaseView<Presenter> {
        void showSuccessfulRegister(String message);
        void showErrorRegister(String message);
        void navigateTo();
    }

    interface Presenter extends BasePresenter {
        boolean validateRegisterFields(EditText[] fields);
    }

}
