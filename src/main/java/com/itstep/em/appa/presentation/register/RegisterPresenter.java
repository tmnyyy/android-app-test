package com.itstep.em.appa.presentation.register;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.itstep.em.appa.R;
import com.itstep.em.appa.data.model.Member;
import com.itstep.em.appa.data.db.DbHandler;


@SuppressWarnings("WeakerAccess")
public class RegisterPresenter implements RegisterContract.Presenter{

    @NonNull RegisterContract.View mView;
    String fname, lname, phone, email, pass;
    Context context;
    DbHandler dbHandler;

    public RegisterPresenter(@NonNull RegisterContract.View view, Context mContext){
        mView = view;
        context = mContext;
        mView.setPresenter(this);
        dbHandler = new DbHandler(context);
    }

    @Override
    public boolean validateRegisterFields(EditText[] fields) {

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

        fname = fields[0].getText().toString();
        lname = fields[1].getText().toString();
        phone = fields[2].getText().toString();
        email = fields[3].getText().toString();
        pass = fields[4].getText().toString();

        if (savingData(fname, lname, phone, email, pass)){
            mView.showSuccessfulRegister(context.getString(R.string.register_success));
        }else{
            mView.showErrorRegister(context.getString(R.string.register_error));
        }

    }

    private boolean savingData(String fname, String lname,
                               String phone, String email, String pass){
        Member user = new Member(fname, lname, phone, email, pass);
        return dbHandler.saveUserData(user);
    }

    @Override
    public void start() {}
}
