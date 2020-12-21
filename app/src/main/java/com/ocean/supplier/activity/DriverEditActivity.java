package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ocean.supplier.R;
import com.ocean.supplier.adapter.DriverEditAdapter;
import com.ocean.supplier.adapter.DriverManagementAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.DriverList;
import com.ocean.supplier.tools.ListToStringTest;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.RecyclerViewHelper;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/24.
 * 司机状态编辑
 */
public class DriverEditActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_driver)
    RecyclerView rvDriver;
    @BindView(R.id.cb_select_all)
    CheckBox cbSelectAll;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_jy)
    Button btnJy;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;
    private DriverList data;
    private DriverEditAdapter adapter;

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("司机管理");
        manger.setBack();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DriverEditActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_driver_edit;
    }

    @Override
    protected void initViews() {
        adapter = new DriverEditAdapter(DriverEditActivity.this);
        getDriverList();

        cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (data != null && data.getList().size() > 0 && data.getList() != null) {
                        for (int i = 0; i < data.getList().size(); i++) {
                            data.getList().get(i).setSelectStatus(1);
                        }
                        adapter.setDatas(data);
                    }
                } else {
                    if (data != null && data.getList().size() > 0 && data.getList() != null) {
                        for (int i = 0; i < data.getList().size(); i++) {
                            data.getList().get(i).setSelectStatus(0);
                        }
                        adapter.setDatas(data);
                    }
                }
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.btn_cancel, R.id.btn_delete, R.id.btn_jy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_delete:
                List<String> deleteSelects = new ArrayList<>();
                if (data != null && data.getList().size() > 0 && data.getList() != null) {
                    for (int i = 0; i < data.getList().size(); i++) {
                        if (data.getList().get(i).getSelectStatus() == 1) {
                            deleteSelects.add(data.getList().get(i).getS_driver_id());
                        }
                    }
                    delete(deleteSelects);
                } else {
                    ToastUtil.showToast("暂无代操作司机");

                }
                break;
            case R.id.btn_jy:
                List<String> jySelects = new ArrayList<>();

                if (data != null && data.getList().size() > 0 && data.getList() != null) {
                    for (int i = 0; i < data.getList().size(); i++) {
                        if (data.getList().get(i).getSelectStatus() == 1) {
                            jySelects.add(data.getList().get(i).getS_driver_id());
                        }
                    }
                    jy(jySelects);
                } else {
                    ToastUtil.showToast("暂无代操作员工");

                }
                break;
        }
    }

    private void jy(List<String> selects1) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().changeDriverStatus()).changeStatus(PreferenceUtils.getInstance().getUserToken(),
                ListToStringTest.listToString3(selects1, ','),"2").enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("禁用成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:操作失败");
            }
        });
    }

    private void delete(List<String> selects) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().changeDriverStatus()).changeStatus(PreferenceUtils.getInstance().getUserToken(),
                ListToStringTest.listToString3(selects, ','),"3").enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("删除成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:操作失败");
            }
        });
    }

    private void getDriverList() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().getDriverList()).driverList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<DriverList>>() {
            @Override
            public void onResponse(Call<ApiResponse<DriverList>> call, Response<ApiResponse<DriverList>> response) {
                if (response.body().getCode() == 1) {
                    data = response.body().getData();
                    adapter.setDatas(data);
                    RecyclerViewHelper.initRecyclerViewV(DriverEditActivity.this, rvDriver, false, adapter);
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
