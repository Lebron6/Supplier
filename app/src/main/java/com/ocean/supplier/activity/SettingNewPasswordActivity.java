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
import com.ocean.supplier.tools.AppManager;
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
 * 设置新密码
 */
public class SettingNewPasswordActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SettingNewPasswordActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_setting_new_password;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etPassword.getText().toString())){
            ToastUtil.showToast("请输入原密码");
            return;
        }
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().changePassword()).changePassword(PreferenceUtils.getInstance().getUserToken(),etPassword.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        ToastUtil.showToast("密码修改成功");
                        PreferenceUtils.getInstance().loginOut();
                        AppManager.getAppManager().AppExit(SettingNewPasswordActivity.this);
                        PasswordLoginActivity.actionStart(SettingNewPasswordActivity.this);
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:密码修改失败");
            }
        });
    }
}
