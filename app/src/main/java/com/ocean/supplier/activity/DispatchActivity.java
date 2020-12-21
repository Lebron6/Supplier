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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.DispactchList;
import com.ocean.supplier.entity.DispatchAddress;
import com.ocean.supplier.entity.DispatchCarList;
import com.ocean.supplier.entity.DispatchDriverInfo;
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

import static com.ocean.supplier.activity.DispatchChoseCarActivity.CAR;
import static com.ocean.supplier.activity.DispatchChoseDriverActivity.DRIVER;
import static com.ocean.supplier.activity.DriverSelectActivity.CALLBACK;

/**
 * Created by James on 2020/11/19.
 * 调度
 */
public class DispatchActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout_cph)
    LinearLayout layoutCph;
    @BindView(R.id.tv_sixm)
    TextView tvSixm;
    @BindView(R.id.layout_xszh)
    LinearLayout layoutXszh;
    @BindView(R.id.et_sjh)
    EditText etSjh;
    @BindView(R.id.layout_ccspsj)
    LinearLayout layoutCcspsj;
    @BindView(R.id.tv_cph)
    TextView tvCph;
    @BindView(R.id.layout_zdzzzl)
    LinearLayout layoutZdzzzl;
    @BindView(R.id.et_zzjs)
    EditText etZzjs;
    @BindView(R.id.layout_cx)
    LinearLayout layoutCx;
    @BindView(R.id.et_hwsl)
    EditText etHwsl;
    @BindView(R.id.layout_jhdz)
    LinearLayout layoutJhdz;
    @BindView(R.id.tv_jhdz)
    TextView tvJhdz;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.btn_sure)
    Button btnSure;

    public static final String OS_ID = "os_id";

    public static void actionStart(Context context, String os_id) {
        Intent intent = new Intent(context, DispatchActivity.class);
        intent.putExtra(OS_ID, os_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("调度");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_dispactch;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        getAddrData();
    }

    private void getAddrData() {

        HttpUtil.createRequest(BaseUrl.getInstence().supplierDeliveryAddress()).supplierDeliveryAddress(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(OS_ID)).enqueue(new Callback<DispatchAddress>() {
            @Override
            public void onResponse(Call<DispatchAddress> call, Response<DispatchAddress> response) {
                if (response.body().getCode() == 1) {
                    tvJhdz.setText(response.body().getData());
                    address = response.body().getData();
                    address = response.body().getData();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<DispatchAddress> call, Throwable t) {
                ToastUtil.showToast("获取地址数据失败");
            }
        });
    }


    @OnClick({R.id.tv_sixm, R.id.tv_cph, R.id.tv_jhdz, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sixm:
                DispatchChoseDriverActivity.actionStartForResult(this);
                break;
            case R.id.tv_cph:
                DispatchChoseCarActivity.actionStartForResult(this);
                break;
            case R.id.tv_jhdz:
                break;
            case R.id.btn_sure:
                if (TextUtils.isEmpty(tvSixm.getText().toString())){
                    ToastUtil.showToast("请选择司机");
                    return;
                }
                if (TextUtils.isEmpty(etSjh.getText().toString())){
                    ToastUtil.showToast("请填写司机手机号");
                    return;
                }
                if (TextUtils.isEmpty(tvCph.getText().toString())){
                    ToastUtil.showToast("请选择车辆");
                    return;
                }
                if (TextUtils.isEmpty(etZzjs.getText().toString())){
                    ToastUtil.showToast("请填写装载件数");
                    return;
                }
                if (TextUtils.isEmpty(etHwsl.getText().toString())){
                    ToastUtil.showToast("请填写货物数量");
                    return;
                }

                if (TextUtils.isEmpty(tvJhdz.getText().toString())){
                    ToastUtil.showToast("请选择地址");
                    return;
                }
                sure();
                break;
        }
    }

    //调度
    private void sure() {
        HttpUtil.createRequest(BaseUrl.getInstence().operationScheduling()).operationScheduling(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(OS_ID)
                , driverId, etSjh.getText().toString(), carId, etZzjs.getText().toString(), etHwsl.getText().toString(), tvJhdz.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("调度成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:调度失败");
            }
        });

    }

    String driverId;
    String carId;
    String address;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case DRIVER:
                if (intent != null && intent.getExtras() != null) {
                    Bundle bundle = intent.getExtras();
                    String result = bundle.getString(CALLBACK);
                    Log.e("返回的司机数据", result);
                    if (!TextUtils.isEmpty(result)) {
                        DispactchList.ListBean driverBean = new Gson().fromJson(result, DispactchList.ListBean.class);
                        tvSixm.setText(driverBean.getName());
                        driverId = driverBean.getD_id();
getDriverInfo(driverId);
                    }

                }

                break;
            case CAR:
                if (intent != null && intent.getExtras() != null) {
                    Bundle bundle = intent.getExtras();
                    String result = bundle.getString(CALLBACK);
                    Log.e("返回的车辆数据", result);
                    if (!TextUtils.isEmpty(result)) {
                        DispatchCarList.ListBean carBean = new Gson().fromJson(result, DispatchCarList.ListBean.class);
                        tvCph.setText(carBean.getNum());
                        carId = carBean.getV_id();
                    }

                }

                break;
        }
    }

    private void getDriverInfo(String driverId) {
        HttpUtil.createRequest(BaseUrl.getInstence().driverInfo()).driverInfo(PreferenceUtils.getInstance().getUserToken(),driverId, getIntent().getStringExtra(OS_ID)
                ).enqueue(new Callback<DispatchDriverInfo>() {
            @Override
            public void onResponse(Call<DispatchDriverInfo> call, Response<DispatchDriverInfo> response) {
                if (response.body().getCode() == 1) {
                  tvCph.setText(response.body().getData().getV_num()+"");
                  etSjh.setText(response.body().getData().getPhone()+"");
                  etZzjs.setText(response.body().getData().getGoods_jnum()+"");
                  etHwsl.setText(response.body().getData().getGoods_num()+"");
                  etSjh.setText(response.body().getData().getPhone()+"");
                    carId=response.body().getData().getV_id();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<DispatchDriverInfo> call, Throwable t) {
                ToastUtil.showToast("网络异常:调度失败");
            }
        });
    }
}
