package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
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
import com.ocean.supplier.dialog.BindNewPhoneSuccessDialog;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.tools.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/5.
 * 绑定新手机号获取验证码
 */
public class BindNewPhoneGetPhoneCodeActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_now_phone_num)
    TextView tvNowPhoneNum;
    @BindView(R.id.et_phone_code)
    EditText etPhoneCode;
    @BindView(R.id.tv_voice)
    TextView tvVoice;
    @BindView(R.id.tv_get_again)
    TextView tvGetAgain;
    @BindView(R.id.tv_timter)
    TextView tvTimter;
    @BindView(R.id.btn_next)
    Button btnNext;
    public static final String PHONE = "PHONE";
    private String phoneNum;

    public static void actionStart(Context context, String phone) {
        Intent intent = new Intent(context, BindNewPhoneGetPhoneCodeActivity.class);
        intent.putExtra(PHONE, phone);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_bind_new_phone_get_phone_code;
    }

    @Override
    protected void initViews() {
        phoneNum = getIntent().getStringExtra(PHONE);
        tvNowPhoneNum.setText("+86 " + phoneNum);
        TimeCount time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
        time.start();// 开始计时
        tvGetAgain.setEnabled(false);
        tvGetAgain.setClickable(false);
        tvGetAgain.setTextColor(getResources().getColor(R.color.colorGray));
    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.tv_voice, R.id.tv_get_again, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_voice:
                break;
            case R.id.tv_get_again:
                getPhoneCode();
                break;
            case R.id.btn_next:
                if (TextUtils.isEmpty(etPhoneCode.getText().toString())) {
                    ToastUtil.showToast("请输入验证码");
                    return;
                }
                HttpUtil.createRequest(TAG, BaseUrl.getInstence().savePhone()).changePhone(PreferenceUtils.getInstance().getUserToken(), phoneNum, etPhoneCode.getText().toString()).enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.body() != null) {
                            if (response.body().getCode() == 1) {
                                BindNewPhoneSuccessDialog dialog = new BindNewPhoneSuccessDialog(BindNewPhoneGetPhoneCodeActivity.this, R.style.MyDialog);
                                dialog.show();
                            } else {
                                ToastUtil.showToast(response.body().getMsg());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        ToastUtil.showToast("网络异常:验证失败");
                    }
                });


                break;
        }
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
                tvGetAgain.setEnabled(false);
                tvGetAgain.setClickable(false);
                tvTimter.setText(millisUntilFinished / 1000 + "秒后重新发送验证码 或 通过其他途径验证");
            }catch (Exception e){

            }

        }

        @Override
        public void onFinish() {// 计时完毕时触发
            try {
                tvGetAgain.setEnabled(true);
                tvGetAgain.setClickable(true);
                tvGetAgain.setTextColor(getResources().getColor(R.color.colorMain));
                tvGetAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPhoneCode();
                    }
                });
            }catch (Exception E){}

        }

    }
    private void getPhoneCode(){
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().sendSms()).sendSmsNew(PreferenceUtils.getInstance().getUserToken(), phoneNum).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        TimeCount time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
                        time.start();// 开始计时
                        tvGetAgain.setEnabled(false);
                        tvGetAgain.setTextColor(getResources().getColor(R.color.colorGray));
                        tvGetAgain.setClickable(false);
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:验证码发送失败");
            }
        });
    }
}
