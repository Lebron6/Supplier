package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.LoginResult;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.ToastUtil;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/3.
 * 密码登录
 */
public class PasswordLoginActivity extends BaseActivity {


    @BindView(R.id.et_company)
    EditText etCompany;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_show_password)
    CheckBox cbShowPassword;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_company_register)
    TextView tvCompanyRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PasswordLoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_password_login;
    }

    @Override
    protected void initViews() {
        if (PreferenceUtils.getInstance().getLoginStatus() == true) {
            MainActivity.actionStart(this);
            finish();
        }
        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，隐藏密码
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //否则显示密码
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        cbShowPassword.setChecked(true);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({ R.id.btn_login, R.id.tv_forget_password,R.id.tv_company_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_company_register:
CompanyRegisterActivity.actionStart(this);
                break;
            case R.id.btn_login:
                if (TextUtils.isEmpty(etAccount.getText().toString())) {
                    ToastUtil.showToast("请输入手机号码");
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    ToastUtil.showToast("请输入密码");
                    return;
                }
                if (TextUtils.isEmpty(etCompany.getText().toString())) {
                    ToastUtil.showToast("请输入公司编号");
                    return;
                }
                HttpUtil.createRequest(TAG, BaseUrl.getInstence().passwordLogin()).userLogin(etCompany.getText().toString(),etAccount.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<ApiResponse<LoginResult>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<LoginResult>> call, Response<ApiResponse<LoginResult>> response) {
                        if (response.body().getCode() == 1) {
                            ToastUtil.showToast("登录成功");
                            PreferenceUtils.getInstance().setUserToken(response.body().getData().getToken());
                            PreferenceUtils.getInstance().setLoginStatus(true);
                            PreferenceUtils.getInstance().setUserID(response.body().getData().getUser_id());
                            MainActivity.actionStart(PasswordLoginActivity.this);
                            finish();
                        } else {
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<LoginResult>> call, Throwable t) {
                        Log.e("888",t.toString());
                        ToastUtil.showToast("网络异常:登录失败");
                    }
                });
                break;
            case R.id.tv_forget_password:
                ForgetPasswordActivity.actionStart(this);
                break;
        }
    }

}
