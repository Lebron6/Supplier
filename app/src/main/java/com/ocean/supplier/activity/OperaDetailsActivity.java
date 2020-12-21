package com.ocean.supplier.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.DriverRejectAdapter;
import com.ocean.supplier.adapter.OperaDetailsJAdapter;
import com.ocean.supplier.adapter.OperaDetailsSAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.dialog.RejectOperaRemarksDialog;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.OperaDetails;
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
 * Created by James on 2020/7/3.
 * 操作单详情
 */
public class OperaDetailsActivity extends BaseActivity {

    public static final String OS_ID = "os_id";
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_reject_time)
    TextView tvRejectTime;
    @BindView(R.id.tc_reject_content)
    TextView tcRejectContent;
    @BindView(R.id.layout_t)
    LinearLayout layoutT;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.tv_zzzl)
    TextView tvZzzl;
    @BindView(R.id.tv_zzjs)
    TextView tvZzjs;
    @BindView(R.id.zztj)
    TextView zztj;
    @BindView(R.id.tv_zzsl)
    TextView tvZzsl;
    @BindView(R.id.layout_see_list)
    LinearLayout layoutSeeList;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.layout_see_map)
    LinearLayout layoutSeeMap;
    @BindView(R.id.layout_fp)
    LinearLayout layoutFp;
    @BindView(R.id.layout_reject)
    LinearLayout layoutReject;
    @BindView(R.id.layout_accept)
    LinearLayout layoutAccept;
    @BindView(R.id.layout_sl)
    LinearLayout layoutSl;
    @BindView(R.id.layout_bottom_one)
    RelativeLayout layoutBottomOne;
    @BindView(R.id.tv_addr_start)
    TextView tvAddrStart;
    @BindView(R.id.tv_addr_end)
    TextView tvAddrEnd;
    @BindView(R.id.rv_jiehuo)
    RecyclerView rvJiehuo;
    @BindView(R.id.rv_jiaohuo)
    RecyclerView rvJiaohuo;
    @BindView(R.id.rv_bo)
    RecyclerView rvBo;
    @BindView(R.id.layout_t_one)
    LinearLayout layoutTOne;


    public static void actionStart(Context context, String os_id) {
        Intent intent = new Intent(context, OperaDetailsActivity.class);
        intent.putExtra(OS_ID, os_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("操作单详情");
        manger.setBack();
        getDetailsData();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_opera_details;
    }

    @Override
    protected void initViews() {

    }

    private void getDetailsData() {
        getDara();
    }

    String o_id;

    private void getDara() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().transportOperationInfo()).operationListingInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(OS_ID)).enqueue(new Callback<ApiResponse<OperaDetails>>() {
            @Override
            public void onResponse(Call<ApiResponse<OperaDetails>> call, Response<ApiResponse<OperaDetails>> response) {
                if (response.body().getCode() == 1) {
                    o_id = response.body().getData().getO_id();
                    tvAddrStart.setText(response.body().getData().getStart_city()+"");
                    tvAddrEnd.setText(response.body().getData().getEnd_city());
                    tvZzjs.setText(response.body().getData().getGoods_jnum());
                    tvZzsl.setText(response.body().getData().getGoods_num());
                    tvZzzl.setText(response.body().getData().getTotal_weight() + "kg");
                    zztj.setText(response.body().getData().getTotal_volume() + "m³");

                    OperaDetailsJAdapter pickupAdapter = new OperaDetailsJAdapter(OperaDetailsActivity.this);
                    pickupAdapter.setDatas(response.body().getData().getPickup_address());
                    RecyclerViewHelper.initRecyclerViewV(OperaDetailsActivity.this, rvJiehuo, false, pickupAdapter);

                    OperaDetailsSAdapter deliveryAdapter = new OperaDetailsSAdapter(OperaDetailsActivity.this);
                    deliveryAdapter.setDatas(response.body().getData().getDelivery_address());
                    RecyclerViewHelper.initRecyclerViewV(OperaDetailsActivity.this, rvJiaohuo, false, deliveryAdapter);
                    layoutT.setVisibility(View.GONE); layoutTOne.setVisibility(View.GONE);
                    if (response.body().getData().getS_status().equals("2")) {//状态为2 显示驳回
                        layoutT.setVisibility(View.VISIBLE);
                        tvRejectTime.setText(response.body().getData().getReject_time());
                        tcRejectContent.setText(response.body().getData().getReject());
                    }

                    if (response.body().getData().getS_status().equals("4")&&response.body().getData().getIs_d_reject()==1){//调度司机驳回
                        layoutTOne.setVisibility(View.VISIBLE);
                        DriverRejectAdapter driverRejectAdapter = new DriverRejectAdapter(OperaDetailsActivity.this);
                        driverRejectAdapter.setDatas(response.body().getData().getD_reject_list());
                        RecyclerViewHelper.initRecyclerViewV(OperaDetailsActivity.this, rvBo, false, driverRejectAdapter);
                    }
                    switch (response.body().getData().getS_type()) {//供应商类型 1提货 2干线 3派送 4交货 5其他
                        case "1":

                            break;
                        case "2":

                            break;
                        case "3":

                            break;
                        case "4":

                            break;
                        case "5":

                            break;
                    }

                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<OperaDetails>> call, Throwable t) {
                ToastUtil.showToast("网络异常:操作单详情数据获取失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getDetailsData();
    }


    @OnClick({R.id.layout_see_list, R.id.layout_see_map, R.id.layout_fp, R.id.layout_reject, R.id.layout_accept, R.id.layout_sl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_see_list:
                OperaGoodsQutationActivity.actionStart(this, o_id);
                break;
            case R.id.layout_see_map:
                OperationTrackActivity.actionStart(this, getIntent().getStringExtra(OS_ID));
                break;
            case R.id.layout_fp:
                break;
            case R.id.layout_reject:
                RejectOperaRemarksDialog dialog = new RejectOperaRemarksDialog(this, R.style.Theme_AppCompat_Dialog, getIntent().getStringExtra(OS_ID));
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        getDara();
                    }
                });
                break;
            case R.id.layout_accept:
                sure();
                break;
            case R.id.layout_sl:


                break;
        }
    }

    private void sure() {
        HttpUtil.createRequest(BaseUrl.getInstence().operationAccept()).shipperConfirm(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(OS_ID)).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("通过成功");
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
