package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.tools.ToastUtil;
import com.ocean.supplier.tools.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/3.
 * 忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_company_num)
    EditText etCompanyNum;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_phone_code)
    EditText etPhoneCode;
    @BindView(R.id.tv_get_phone_code)
    TextView tvGetPhoneCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("忘记密码");
        manger.setBack();
    }


    @OnClick({R.id.tv_get_phone_code, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_phone_code:
                if (TextUtils.isEmpty(etAccount.getText().toString())) {
                    ToastUtil.showToast("请输入手机号码");
                    return;
                }
                if (!Utils.isMobileNO(etAccount.getText().toString())) {
                    ToastUtil.showToast("手机号码格式错误");
                    return;
                }
                getPhoneCode(etAccount.getText().toString());
                break;
            case R.id.btn_commit:
                if (TextUtils.isEmpty(etCompanyNum.getText().toString())) {
                    ToastUtil.showToast("请输入公司编号");
                    return;
                }
                if (TextUtils.isEmpty(etAccount.getText().toString())) {
                    ToastUtil.showToast("请输入手机号码");
                    return;
                }
                if (TextUtils.isEmpty(etPhoneCode.getText().toString())) {
                    ToastUtil.showToast("请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    ToastUtil.showToast("请输入新密码");
                    return;
                }
                commit(etAccount.getText().toString(), etPhoneCode.getText().toString(), etPassword.getText().toString());
                break;
        }
    }

    private void commit(String phone, String phoneCode, String password) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().passwordForget()).passwordForget(etCompanyNum.getText().toString(),phone,phoneCode,password).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("密码找回成功");
                    appManager.finishAllActivity();
                    PasswordLoginActivity.actionStart(ForgetPasswordActivity.this);
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:登录失败");
            }
        });
    }

    private void getPhoneCode(String phoneNum) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().sendSMS()).sendSMS(phoneNum).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    TimeCount time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
                    time.start();// 开始计时
                    ToastUtil.showToast("验证码已发送，注意查收");
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取验证码失败");
            }
        });
    }

    // /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 计时过程显示
            try {
                tvGetPhoneCode.setEnabled(false);
                tvGetPhoneCode.setClickable(false);
                tvGetPhoneCode.setText(millisUntilFinished / 1000 + "秒");
            } catch (Exception e) {

            }

        }

        @Override
        public void onFinish() {// 计时完毕时触发
            try {
                tvGetPhoneCode.setEnabled(true);
                tvGetPhoneCode.setText("重新验证");
                tvGetPhoneCode.setClickable(true);
                tvGetPhoneCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPhoneCode(etAccount.getText().toString());
                    }
                });
            } catch (Exception E) {
            }

        }

    }
}
