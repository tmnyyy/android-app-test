package com.itstep.em.appa.presentation.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.itstep.em.appa.R;
import com.itstep.em.appa.data.db.DbHandler;


@SuppressWarnings("WeakerAccess")
public class LoginPresenter implements LoginContract.Presenter{

    @NonNull LoginContract.View mView;
    Context mContext;
    String email, pass;
    DbHandler dbHandler;

    public LoginPresenter(@NonNull LoginContract.View view, Context context){
        mView = view;
        mView.setPresenter(this);
        mContext = context;
        dbHandler = new DbHandler(context);
    }

//    проверка полей на пустоту
    @Override
    public boolean validateLoginFields(EditText[] fields) {
        for (EditText field: fields){
            if (field.getText().toString().isEmpty()){
                field.setError("Заполните поле!");
                return false;
            }
        }
        validated(fields);
        return true;
    }

    private void validated(EditText[] fields){
        email = fields[0].getText().toString();
        pass = fields[1].getText().toString();
        checkCredentials(email, pass);
    }

//    проверка существования пользователя
    private void checkCredentials(String email, String pass){
       if (dbHandler.checkUserCredentials(email, pass)){
           mView.showSuccessfulMessage(mContext.getString(R.string.email_pass_valid_success));
           mView.navigateTo(email);
       }else{
           mView.showFailedMessage(mContext.getString(R.string.email_pass_valid_err));
       }
    }

    @Override
    public void start() {}

}
