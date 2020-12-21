package com.ocean.supplier.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.ToBeAcceptDetailsAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.AcceptDetailsData;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.RecyclerViewHelper;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/31.
 * 提单待受理详情
 */
public class ToBeAcceptDetailsActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.layout_center)
    LinearLayout layoutCenter;
    @BindView(R.id.layout_one)
    RelativeLayout layoutOne;
    @BindView(R.id.rv_details)
    RecyclerView rvDetails;
    @BindView(R.id.layout_see_map)
    LinearLayout layoutSeeMap;
    @BindView(R.id.layout_accept)
    LinearLayout layoutAccept;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;
    @BindView(R.id.iv_call)
    ImageView ivCall;
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;
    @BindView(R.id.tv_driver)
    TextView tvDriver;
    @BindView(R.id.tv_updata_car)
    TextView tvUpdataCar;
    @BindView(R.id.layout_three)
    RelativeLayout layoutThree;
    private String phoneNum;
    private ToBeAcceptDetailsAdapter adapter;
    public static String SDL_ID = "SDL_ID";
    public static String SDLV_ID = "SDLV_ID";
    public static String TYPE = "TYPE";


    public static void actionStart(Context context, String sdl_id, String sdlv_id, String type) {
        Intent intent = new Intent(context, ToBeAcceptDetailsActivity.class);
        intent.putExtra(SDL_ID, sdl_id);
        intent.putExtra(SDLV_ID, sdlv_id);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("详情-待受理");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_to_be_complete_detatils;
    }

    @Override
    protected void initViews() {
        adapter = new ToBeAcceptDetailsAdapter(this);
        if (getIntent().getStringExtra(TYPE).equals("0")) {//待处理
            layoutThree.setVisibility(View.GONE);
            layoutButton.setVisibility(View.VISIBLE);
        } else {//完成

            layoutThree.setVisibility(View.VISIBLE);
            layoutButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initDatas() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().deliveryBillInfo()).deliveryBillInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(SDL_ID), getIntent().getStringExtra(SDLV_ID)).enqueue(new Callback<ApiResponse<AcceptDetailsData>>() {
            @Override
            public void onResponse(Call<ApiResponse<AcceptDetailsData>> call, Response<ApiResponse<AcceptDetailsData>> response) {
                if (response.body().getCode() == 1) {
                    tvCompanyName.setText(response.body().getData().getInfo().getTlogistics_name());
                    tvTime.setText(response.body().getData().getInfo().getCreateTime());
                    if (getIntent().getStringExtra(TYPE).equals("1")) {//完成
                        tvCarNum.setText("调度车辆："+response.body().getData().getInfo().getVehicle_num());
                        tvDriver.setText("司机："+response.body().getData().getInfo().getVehicle_num());
                    }
                    tvTime.setText(response.body().getData().getInfo().getCreateTime());
                    phoneNum = response.body().getData().getInfo().getTlogistics_mobile();
                    adapter.setDatas(response.body().getData());
                    RecyclerViewHelper.initRecyclerViewV(ToBeAcceptDetailsActivity.this, rvDetails, adapter);
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }

            }

            @Override
            public void onFailure(Call<ApiResponse<AcceptDetailsData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取数据失败");
            }
        });
    }


    @OnClick({R.id.layout_see_map, R.id.layout_accept, R.id.iv_call, R.id.tv_updata_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_call:
                if (TextUtils.isEmpty(phoneNum)) {
                    ToastUtil.showToast("暂不支持客服热线");
                    return;
                }
                callPhone(phoneNum);
                break;
            case R.id.layout_see_map:
                break;
            case R.id.tv_updata_car:
                SelectBillCarOneActivity.actionStart(this, getIntent().getStringExtra(SDL_ID), getIntent().getStringExtra(SDLV_ID));
                break;
            case R.id.layout_accept:
                SelectBillCarOneActivity.actionStart(this, getIntent().getStringExtra(SDL_ID), getIntent().getStringExtra(SDLV_ID));
                break;
        }
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

}
