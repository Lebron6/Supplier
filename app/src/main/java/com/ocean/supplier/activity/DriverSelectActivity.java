package com.ocean.supplier.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ocean.supplier.R;
import com.ocean.supplier.adapter.DriverEditAdapter;
import com.ocean.supplier.adapter.DriverSelectAdapter;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/24.
 * 司机选择
 */
public class DriverSelectActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_driver)
    RecyclerView rvDriver;
    private DriverSelectAdapter adapter;
    public static final int PARMS = 93;
    private DriverList data;

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("选择司机");
        manger.setBack();
    }

    public static void actionStartForResult(Activity context) {
        Intent intent = new Intent(context, DriverSelectActivity.class);
        context.startActivityForResult(intent, PARMS);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_driver_select;
    }

    @Override
    protected void initViews() {
        getDriverList();

    }

    public static final String CALLBACK = "callBack";

    @Override
    protected void initDatas() {

    }

    private void getDriverList() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().getDriverList()).driverList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<DriverList>>() {
            @Override
            public void onResponse(Call<ApiResponse<DriverList>> call, Response<ApiResponse<DriverList>> response) {
                if (response.body().getCode() == 1) {
                    data = response.body().getData();

                    adapter = new DriverSelectAdapter(DriverSelectActivity.this);
                    adapter.setDatas(data);
                    RecyclerViewHelper.initRecyclerViewV(DriverSelectActivity.this, rvDriver, false, adapter);
                    adapter.setOnItemClickLitener(new DriverSelectAdapter.OnItemClickLitener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Intent intent = new Intent();
                            intent.putExtra(CALLBACK, new Gson().toJson(data.getList().get(position)));
                            setResult(3, intent);
                            finish();
                        }
                    });
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
}
