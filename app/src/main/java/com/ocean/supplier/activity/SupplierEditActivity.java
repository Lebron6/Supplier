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

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.supplier.R;
import com.ocean.supplier.adapter.CarEditAdapter;
import com.ocean.supplier.adapter.SupplierEditAdapter;
import com.ocean.supplier.adapter.SupplierManagementAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.VehicleGetResult;
import com.ocean.supplier.tools.ListToStringTest;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.RecyclerViewHelper;
import com.ocean.supplier.tools.SimpleFooter;
import com.ocean.supplier.tools.SimpleHeader;
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
 * 供应商调度-批量修改
 */
public class SupplierEditActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_car)
    RecyclerView rvCar;
    @BindView(R.id.sv_pur)
    SpringView svPur;
    @BindView(R.id.cb_select_all)
    CheckBox cbSelectAll;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_chose)
    Button btnChose;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;
    private SupplierEditAdapter adapter;

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("车辆管理");
        manger.setBack();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SupplierEditActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_supplier_edit;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter=new SupplierEditAdapter(this);
        getData();
        cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (listBeans != null && listBeans.size() > 0) {
                        for (int i = 0; i < listBeans.size(); i++) {
                            listBeans.get(i).setSelectStatus(1);
                        }
                        adapter.setDatas(listBeans);
                    }
                } else {
                    if (listBeans != null && listBeans.size() > 0) {
                        for (int i = 0; i < listBeans.size(); i++) {
                            listBeans.get(i).setSelectStatus(0);
                        }
                        adapter.setDatas(listBeans);
                    }
                }
            }
        });
    }

    private void initSpringViewStyle() {
        svPur.setType(SpringView.Type.FOLLOW);
        svPur.setListener(onFreshListener);
        svPur.setHeader(new SimpleHeader(this));
        svPur.setFooter(new SimpleFooter(this));
    }

    private int page = 1;
    SpringView.OnFreshListener onFreshListener = new SpringView.OnFreshListener() {
        @Override
        public void onRefresh() {
            page = 1;
            getData();
            cbSelectAll.setChecked(false);
        }

        @Override
        public void onLoadmore() {
            page = ++page;
            getData();
        }
    };
    List<VehicleGetResult.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(BaseUrl.getInstence().vehicleGetList()).vehicleGetList(PreferenceUtils.getInstance().getUserToken(), page + "", "").enqueue(new Callback<ApiResponse<VehicleGetResult>>() {
            @Override
            public void onResponse(Call<ApiResponse<VehicleGetResult>> call, Response<ApiResponse<VehicleGetResult>> response) {
                if (svPur != null) {
                    svPur.onFinishFreshAndLoad();
                }

                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(SupplierEditActivity.this, rvCar, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData().getList());
                        }
                        adapter.setDatas(listBeans);

                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<VehicleGetResult>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取车辆列表失败");
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.btn_cancel, R.id.btn_chose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                finish();
                break;

            case R.id.btn_chose:
                List<String> jySelects = new ArrayList<>();
                if (listBeans != null && listBeans.size() > 0) {
                    for (int i = 0; i < listBeans.size(); i++) {
                        if (listBeans.get(i).getSelectStatus() == 1) {
                            jySelects.add(listBeans.get(i).getVehicle_id());
                        }
                    }
                    jy(jySelects);
                } else {
                    ToastUtil.showToast("暂无代操作车辆");
                }
                break;
        }
    }

    private void jy(List<String> selects1) {
        SettingSupplierActivity.actionStart(this, ListToStringTest.listToString3(selects1, ','));
        finish();
    }
}
