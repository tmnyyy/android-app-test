package com.itstep.em.appa.presentation.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.itstep.em.appa.utils.NavUtil;
import com.itstep.em.appa.R;
import com.itstep.em.appa.base.BaseActivity;
import com.itstep.em.appa.presentation.member.MemberActivity;
import com.itstep.em.appa.presentation.register.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;


public class LoginActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.email_input) EditText et_email;
    @BindView(R.id.pass_input) EditText et_pass;

    LoginContract.Presenter mPresenter;
    LoginPresenter loginPresenter;

    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);

    }

    // FLAG_ACTIVITY_CLEAR_TASK флаг вызовет любую существующую задачу, которая будет связана
    // с активностью, которая должна быть очищена до начала действия.
    // FLAG_ACTIVITY_CLEAR_TOP очистка стека задач, запуск активности без создания нового экземпляра.
    @OnClick({R.id.btn_register, R.id.btn_login})
    public void buttonClicked(View v){
        switch (v.getId()){
            case R.id.btn_login:
                loginPresenter.validateLoginFields(new EditText[]{et_email, et_pass});
                break;
            case R.id.btn_register:
                NavUtil.startActivity(this,
                        RegisterActivity.class,
                        Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                break;
        }
    }

    @Override
    public void showSuccessfulMessage(String message) {
        showMessageToast(message);
    }

    @Override
    public void showFailedMessage(String message) {
        showMessageToast(message);
    }

    @Override
    public void showMessageToast(String message) {
        super.showMessageToast(message);
    }

    @Override
    public void navigateTo(String email) {
        NavUtil.startActivity(this,
                MemberActivity.class,
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP,
                email);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter = new LoginPresenter(this, this);
    }
}
