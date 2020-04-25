package com.itstep.em.appa.presentation.member;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.itstep.em.appa.R;
import com.itstep.em.appa.data.model.Member;
import com.itstep.em.appa.data.db.DbHandler;


@SuppressWarnings("WeakerAccess")
public class MemberPresenter implements MemberContract.Presenter{

    @NonNull MemberContract.View mView;
    Context mContext;
    DbHandler dbHandler;
    String fname, lname, phone, email, pass;

    public MemberPresenter(@NonNull MemberContract.View view, Context context){
        mView = view;
        mContext = context;
        mView.setPresenter(this);
        dbHandler = new DbHandler(context);
    }

    @Override
    public void getUserEmail(String email) {
        dbHandler.getUserDetails(email);
        getUserDetail(dbHandler.getUserDetails(email));
    }

    @Override
    public void getUserDetail(Member user) {
        mView.loadUserDetail(user);
    }

    @Override
    public void deleteAccount(String email) {
        dbHandler.deleteAccount(email);
        if (!dbHandler.deleteAccount(email)){
            mView.showSuccess(mContext.getString(R.string.delete_success));
            mView.navigateToNextPage();
        }else{
            mView.showFailed(mContext.getString(R.string.delete_error));
        }
    }

    @Override
    public boolean validateUpdateFields(EditText[] fields) {
        for (EditText fieldsCounter: fields) {
            if (fieldsCounter.getText().toString().isEmpty()){
                fieldsCounter.setError("Заполните поле!");
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

        if (updateUserData(fname, lname, phone, email, pass) == 1){
            mView.showSuccess(mContext.getString(R.string.update_success));
            mView.refreshPage(email);
        }else{
            mView.showFailed(mContext.getString(R.string.update_error));
        }

    }

    @Override
    public void logOut(String email) {
        if (email != null){
            mView.logOut();
        }
    }

    private int updateUserData(String fname, String lname, String phone, String email, String pass){
        Member user = new Member(fname, lname, phone, email, pass);
        return dbHandler.updateUserData(user, email);
    }

    @Override
    public void start() {}
}
