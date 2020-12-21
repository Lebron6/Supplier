package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.DriverManagementAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.DriverList;
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
 * Created by James on 2020/7/22.
 * 司机管理
 */
public class DriverManagementActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.rv_driver)
    RecyclerView rvDriver;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DriverManagementActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("司机管理");
        manger.setBack();
        getDriverList();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_driver_management;
    }

    @Override
    protected void initViews() {

    }

    private void getDriverList() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().getDriverList()).driverList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<DriverList>>() {
            @Override
            public void onResponse(Call<ApiResponse<DriverList>> call, Response<ApiResponse<DriverList>> response) {
                if (response.body().getCode() == 1) {
                    DriverManagementAdapter adapter = new DriverManagementAdapter(DriverManagementActivity.this);
                    adapter.setDatas(response.body().getData());
                    RecyclerViewHelper.initRecyclerViewV(DriverManagementActivity.this, rvDriver, false, adapter);
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<DriverList>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取司机列表失败");
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.tv_edit, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                DriverEditActivity.actionStart(this);
                break;
            case R.id.tv_add:
                AddDriverActivity.actionStart(this);
                break;
        }
    }
}
