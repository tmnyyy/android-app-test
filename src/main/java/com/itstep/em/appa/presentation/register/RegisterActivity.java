package com.itstep.em.appa.presentation.register;

import android.content.Intent;
import android.widget.EditText;

import com.itstep.em.appa.utils.NavUtil;
import com.itstep.em.appa.R;
import com.itstep.em.appa.base.BaseActivity;
import com.itstep.em.appa.presentation.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;


public class RegisterActivity extends BaseActivity implements RegisterContract.View{

    @BindView(R.id.first_name_reg) EditText reg_first_name;
    @BindView(R.id.last_name_reg) EditText reg_last_name;
    @BindView(R.id.phone_reg) EditText reg_phone;
    @BindView(R.id.email_reg) EditText reg_email;
    @BindView(R.id.pass_reg) EditText reg_pass;
    RegisterContract.Presenter mPresenter;
    RegisterPresenter registerPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @OnClick(R.id.submit_btn)
    public void submitClicked(){
        mPresenter.validateRegisterFields(new EditText[]{reg_first_name, reg_last_name, reg_phone,
                reg_email, reg_pass});
    }


    @Override
    public void showSuccessfulRegister(String message) {
        showMessageToast(message);
        navigateTo();
    }

    @Override
    public void showErrorRegister(String message) {
        showMessageToast(message);
    }

    @Override
    public void showMessageToast(String message) {
        super.showMessageToast(message);
    }


    @Override
    public void navigateTo() {
        NavUtil.startActivity(this, LoginActivity.class,
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerPresenter = new RegisterPresenter(this, this);
        mPresenter.start();
    }
}
