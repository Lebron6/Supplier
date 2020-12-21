package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.DriverAddSearch;
import com.ocean.supplier.entity.DriverInfo;
import com.ocean.supplier.entity.DriverList;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ocean.supplier.activity.DriverSelectActivity.CALLBACK;
import static com.ocean.supplier.activity.DriverSelectActivity.PARMS;

/**
 * Created by James on 2020/8/24.
 * 添加司机
 */
public class AddDriverActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.layout_select)
    RelativeLayout layoutSelect;
    @BindView(R.id.tv_driver_type)
    TextView tvDriverType;
    @BindView(R.id.tv_driver_num)
    TextView tvDriverNum;
    @BindView(R.id.tv_id_num)
    TextView tvIdNum;
    @BindView(R.id.tv_call_per)
    TextView tvCallPer;
    @BindView(R.id.layout_has_driver)
    LinearLayout layoutHasDriver;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.rb_z)
    RadioButton rbZ;
    @BindView(R.id.rb_j)
    RadioButton rbJ;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.layout_center)
    RelativeLayout layoutCenter;
    @BindView(R.id.layout_remarks)
    LinearLayout layoutRemarks;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.tv_keep_and_go)
    TextView tvKeepAndGo;
    @BindView(R.id.btn_keep)
    Button btnKeep;


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AddDriverActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("添加司机");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_add_driver;
    }

    @Override
    protected void initViews() {
        layoutHasDriver.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.layout_select)
    public void onViewClicked() {

    }

    private String driverId;


    @OnClick({R.id.tv_keep_and_go, R.id.btn_keep, R.id.tv_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_query:
                if (TextUtils.isEmpty(etPhoneNum.getText().toString())){
                    ToastUtil.showToast("请输入手机号码");
                    return;
                }
                    search();
                break;
            case R.id.tv_keep_and_go:
                keep(0);
                break;
            case R.id.btn_keep:
//                if (TextUtils.isEmpty(result)) {
//                    ToastUtil.showToast("请选择司机");
//                    return;
//                }
//                if (TextUtils.isEmpty(etRemarks.getText().toString())) {
//                    ToastUtil.showToast("请填写备注");
//                    return;
//                }
                keep(1);
                break;
        }
    }

    private void search() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().driverAddSearch()).driverAddSearch(PreferenceUtils.getInstance().getUserToken(), etPhoneNum.getText().toString()).enqueue(new Callback<ApiResponse<DriverAddSearch>>() {
            @Override
            public void onResponse(Call<ApiResponse<DriverAddSearch>> call, Response<ApiResponse<DriverAddSearch>> response) {
                if (response.body().getCode() == 1) {
                    if (TextUtils.isEmpty(response.body().getData().getInfo().getName())){
                        ToastUtil.showToast("暂无司机信息");
                        layoutHasDriver.setVisibility(View.GONE);
                        tvDriverNum.setText( "");
                        tvDriverType.setText( "");
                        tvIdNum.setText( "");
                        tvCallPer.setText("");
                        driverId = "";
                        return;
                    }
                    layoutHasDriver.setVisibility(View.VISIBLE);
                    tvDriverNum.setText(response.body().getData().getInfo().getLicense_num() + "");
                    tvDriverType.setText(response.body().getData().getInfo().getType() + "");
                    tvIdNum.setText(response.body().getData().getInfo().getId_card() + "");
                    tvCallPer.setText(response.body().getData().getInfo().getTel_name() + "  " + response.body().getData().getInfo().getTel_num());
                    driverId = response.body().getData().getInfo().getDriver_id();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<DriverAddSearch>> call, Throwable t) {
                ToastUtil.showToast("网络异常:搜索司机失败");
            }
        });
    }

    private void keep(final int type) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().driverSave()).driverSave(PreferenceUtils.getInstance().getUserToken(), driverId, etRemarks.getText().toString(), rbZ.isChecked() ? "1" : "2").enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("保存成功");
                    if (type == 0) {
                        AddDriverActivity.actionStart(AddDriverActivity.this);
                    }
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:保存司机失败");
            }
        });
    }

}
