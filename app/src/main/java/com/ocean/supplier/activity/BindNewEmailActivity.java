package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/5.
 * 绑定新邮箱
 */
public class BindNewEmailActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_email_num)
    TextView tvEmailNum;
    @BindView(R.id.et_email_code)
    EditText etEmailCode;
    @BindView(R.id.btn_next)
    Button btnNext;
public static final  String EMAIL="EMAIL";
    public static void actionStart(Context context,String email) {
        Intent intent = new Intent(context, BindNewEmailActivity.class);
        intent.putExtra(EMAIL,email);
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
        return R.layout.activity_bind_new_email;
    }

    @Override
    protected void initViews() {
        tvEmailNum.setText(getIntent().getStringExtra(EMAIL));
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etEmailCode.getText().toString())) {
            ToastUtil.showToast("请输入验证码");
            return;
        }
        commit();
    }

    private void commit() {
        btnNext.setEnabled(false);
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().bindEmail()).saveEmail(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(EMAIL),etEmailCode.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        ToastUtil.showToast("邮箱修改成功");
                        finish();
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                        btnNext.setEnabled(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:验证码发送失败");
                btnNext.setEnabled(true);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
