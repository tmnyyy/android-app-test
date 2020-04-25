package com.itstep.em.appa.presentation.member;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.itstep.em.appa.utils.NavUtil;
import com.itstep.em.appa.R;
import com.itstep.em.appa.base.BaseActivity;
import com.itstep.em.appa.data.model.Member;
import com.itstep.em.appa.presentation.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

public class MemberActivity extends BaseActivity implements MemberContract.View{

    @BindView(R.id.et_fname) EditText edit_fname;
    @BindView(R.id.et_lname) EditText edit_lname;
    @BindView(R.id.et_email) EditText edit_email;
    @BindView(R.id.et_phone) EditText edit_phone;
    @BindView(R.id.et_pass) EditText edit_pass;
    @BindView(R.id.tv_fname) TextView tv_fname;
    MemberContract.Presenter mPresenter;
    MemberPresenter memberPresenter;
    String de_fname, de_lname, de_phone, de_email, de_pass, email;
    int de_id;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_member;
    }

    @Override
    public void setPresenter(MemberContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @OnClick({R.id.btn_edit, R.id.btn_delete, R.id.btn_logout})
    public void buttonClicked(View v){
        switch (v.getId()){
            case R.id.btn_edit:
                memberPresenter.validateUpdateFields(new EditText[]{edit_fname, edit_lname,
                        edit_phone, edit_email, edit_pass});
                break;
            case R.id.btn_delete:
                memberPresenter.deleteAccount(de_email);
                break;
            case R.id.btn_logout:
                memberPresenter.logOut(email);
                break;
        }
    }

    @Override
    public void loadUserDetail(Member user) {
        de_id = user.getUser_id();
        de_fname = user.getUser_fname();
        de_lname = user.getUser_lname();
        de_phone = user.getUser_phone();
        de_email = user.getUser_email();
        de_pass = user.getUser_password();

        if (de_fname != null){
            setData();
        }
    }

    private void setData(){
        edit_fname.setText(de_fname);
        edit_lname.setText(de_lname);
        edit_phone.setText(de_phone);
        edit_email.setText(de_email);
        edit_pass.setText(de_pass);
        tv_fname.setText(de_fname);
    }

    @Override
    public void showSuccess(String message) {
        showMessageToast(message);
    }

    @Override
    public void showFailed(String message) {
        showMessageToast(message);
    }

    @Override
    public void navigateToNextPage() {
        NavUtil.startActivity(this,
                LoginActivity.class,
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void refreshPage(String email) {
        memberPresenter = new MemberPresenter(this, this);
        memberPresenter.getUserEmail(email);
    }

    @Override
    public void showMessageToast(String message) {
        super.showMessageToast(message);
    }

    @Override
    public void logOut() {
        NavUtil.startActivity(this,
                LoginActivity.class,
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        email = getIntent().getStringExtra("wrap_detail");
        memberPresenter = new MemberPresenter(this, this);
        memberPresenter.getUserEmail(email);
    }
}
