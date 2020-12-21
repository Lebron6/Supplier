package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
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
 * 绑定新手机号
 */
public class BindNewPhoneActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_now_phone_num)
    TextView tvNowPhoneNum;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.btn_next)
    Button btnNext;
    public static final String PHONE = "PHONE";
    private String phoneNum;
    public static void actionStart(Context context, String phone) {
        Intent intent = new Intent(context, BindNewPhoneActivity.class);
        intent.putExtra(PHONE, phone);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger= TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_bind_new_phone;
    }

    @Override
    protected void initViews() {
        phoneNum = getIntent().getStringExtra(PHONE);
        tvNowPhoneNum.setText("更换手机号后，下次可使用新手机号登录。\n" +
                "当前手机号：" + phoneNum);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etPhoneNum.getText().toString())) {
            ToastUtil.showToast("请输入手机号码");
            return;
        }
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().sendSms()).sendSmsNew(PreferenceUtils.getInstance().getUserToken(), etPhoneNum.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        BindNewPhoneGetPhoneCodeActivity.actionStart(BindNewPhoneActivity.this,etPhoneNum.getText().toString());
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                ToastUtil.showToast("网络异常:验证码发送失败");
            }
        });

    }

}
