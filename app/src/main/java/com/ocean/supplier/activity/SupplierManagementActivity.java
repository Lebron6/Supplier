package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.supplier.R;
import com.ocean.supplier.adapter.CarManagementAdapter;
import com.ocean.supplier.adapter.SupplierManagementAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.VehicleGetResult;
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
 * Created by James on 2020/9/25.
 * 供应商调度
 */
public class SupplierManagementActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sv_pur)
    SpringView svPur;
    @BindView(R.id.tv_updata)
    TextView tvUpdata;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    private SupplierManagementAdapter adapter;

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("供应商调度");
        manger.setBack();
        getData();
    }
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SupplierManagementActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_supplier_management;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter=new SupplierManagementAdapter(this);
    }
    private void initSpringViewStyle() {
        svPur.setType(SpringView.Type.FOLLOW);
        svPur.setListener(onFreshListener);
        svPur.setHeader(new SimpleHeader(this));
        svPur.setFooter(new SimpleFooter(this));
    }
    @Override
    protected void initDatas() {

    }
    private int page = 1;
    SpringView.OnFreshListener onFreshListener = new SpringView.OnFreshListener() {
        @Override
        public void onRefresh() {
            page = 1;
            getData();
        }

        @Override
        public void onLoadmore() {
            page = ++page;
            getData();
        }
    };
    List<VehicleGetResult.ListBean> listBeans = new ArrayList<>();
    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().vehicleGetList()).vehicleGetList(PreferenceUtils.getInstance().getUserToken(),page+"","").enqueue(new Callback<ApiResponse<VehicleGetResult>>() {
            @Override
            public void onResponse(Call<ApiResponse<VehicleGetResult>> call, Response<ApiResponse<VehicleGetResult>> response) {
                if (svPur!=null){
                    svPur.onFinishFreshAndLoad();
                }

                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(SupplierManagementActivity.this, rvList, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData().getList());
                        }
                      adapter.setDatas(listBeans);

                        adapter.setOnItemClickLitener(new SupplierManagementAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
//                                AddCarActivity.actionStart(CarManagementActivity.this,"EDIT",listBeans.get(position).getVehicle_id());
                            }
                        });
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<VehicleGetResult>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表数据失败");
            }
        });
    }

    @OnClick(R.id.tv_updata)
    public void onViewClicked() {
        SupplierEditActivity.actionStart(this);
    }
}
