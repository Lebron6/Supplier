package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
//import com.ocean.supplier.entity.InfoInit;
import com.ocean.supplier.entity.SettingInfo;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.tools.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/4.
 * 个人资料
 */
public class PersonalDataActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.txt_wx_bind)
    TextView txtWxBind;
    @BindView(R.id.rb_m)
    RadioButton rbM;
    @BindView(R.id.rb_w)
    RadioButton rbW;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.layout_check)
    LinearLayout layoutCheck;
    @BindView(R.id.layout_driver_level)
    RelativeLayout layoutDriverLevel;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.txt_phone_bind)
    TextView txtPhoneBind;
    @BindView(R.id.et_part)
    EditText etPart;
    @BindView(R.id.txt_emergency)
    TextView txtEmergency;
    @BindView(R.id.et_position)
    EditText etPosition;
    @BindView(R.id.layout_about_e9)
    LinearLayout layoutAboutE9;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PersonalDataActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("个人信息");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_personal_data;
    }

    @Override
    protected void initViews() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().settingInfo()).settingInfo(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<SettingInfo>>() {
            @Override
            public void onResponse(Call<ApiResponse<SettingInfo>> call, Response<ApiResponse<SettingInfo>> response) {
                if (response.body().getCode() == 1) {
                    etName.setText(response.body().getData().getUsername());
                    etPart.setText(response.body().getData().getDepartment());
                    etPosition.setText(response.body().getData().getPosition());
                    rbM.setChecked(response.body().getData().getSex().equals("1"));
                    rbW.setChecked(response.body().getData().getSex().equals("2"));

                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<SettingInfo>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取个人数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etName.getText().toString()))
        {
            ToastUtil.showToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(etPart.getText().toString()))
        {
            ToastUtil.showToast("请输入部门");
            return;
        }
        if (TextUtils.isEmpty(etPosition.getText().toString()))
        {
            ToastUtil.showToast("请输入岗位");
            return;
        }
        commit();
    }

    private void commit() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().settingEdit()).infoSave(PreferenceUtils.getInstance().getUserToken(), etName.getText().toString(), rbM.isChecked()==true?"1":"2",etPart.getText().toString(), etPosition.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("保存成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:保存信息失败");
            }
        });
    }

}
