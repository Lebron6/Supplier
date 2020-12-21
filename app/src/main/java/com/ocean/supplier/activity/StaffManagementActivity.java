package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.StaffManagementAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.StaffList;
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
 * Created by James on 2020/7/22.
 * 员工管理
 */
public class StaffManagementActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_staff)
    RecyclerView rvStaff;
    @BindView(R.id.cb_select_all)
    CheckBox cbSelectAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_finish)
    Button btnFinish;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;
    private ApiResponse<StaffList> body;
    private StaffManagementAdapter adapter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, StaffManagementActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("员工管理");
        manger.setBack();
        getStaffList();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_staff_management;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {

        cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (body != null && body.getData()!=null&& body.getData().getList().size() > 0 && body.getData().getList() != null) {
                        for (int i = 0; i < body.getData().getList().size(); i++) {
                            body.getData().getList().get(i).setSelectStatus(1);
                        }
                        adapter.setDatas(body.getData());
                    }
                } else {
                    if (body != null &&  body.getData().getList().size() > 0 &&  body.getData().getList() != null) {
                        for (int i = 0; i <  body.getData().getList().size(); i++) {
                            body.getData().getList().get(i).setSelectStatus(0);
                        }
                        adapter.setDatas(body.getData());
                    }
                }
            }
        });
    }
    private void getStaffList() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().staffList()).staffList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<StaffList>>() {
            @Override
            public void onResponse(Call<ApiResponse<StaffList>> call, Response<ApiResponse<StaffList>> response) {
                body = response.body();
                if (body.getCode() == 1) {
                    adapter = new StaffManagementAdapter(StaffManagementActivity.this);
                    adapter.setDatas(body.getData());
                    RecyclerViewHelper.initRecyclerViewV(StaffManagementActivity.this, rvStaff, false, adapter);
                } else {
                    ToastUtil.showToast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<StaffList>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取员工列表失败");
            }
        });
    }
    @OnClick({R.id.btn_delete, R.id.btn_finish,R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
                List<String> deleteSelects = new ArrayList<>();
                if (body != null && body.getData().getList().size() > 0 && body.getData().getList() != null) {
                    for (int i = 0; i < body.getData().getList().size(); i++) {
                        if (body.getData().getList().get(i).getSelectStatus() == 1) {
                            deleteSelects.add(body.getData().getList().get(i).getSw_id());
                        }
                    }
                    delete(deleteSelects);
                } else {
                    ToastUtil.showToast("暂无代操作员工");

                }
                break;
            case R.id.btn_finish:finish();
                break;
            case R.id.tv_add:AddStaffActivity.actionStart(this);
                break;
        }
    }
    private void delete(List<String> selects) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().staffDelete()).deleteStaff(PreferenceUtils.getInstance().getUserToken(),
                ListToStringTest.listToString3(selects, ',')).enqueue(new Callback<ApiResponse>() {
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

}
