package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.supplier.R;
import com.ocean.supplier.adapter.SelectBillCarOneAdapter;
import com.ocean.supplier.adapter.ToBeAcceptAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.CarList;
import com.ocean.supplier.entity.DeliveryList;
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
 * Created by James on 2020/8/31.
 * 选择提货车辆
 */
public class SelectBillCarOneActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_weigth)
    TextView tvWeigth;
    @BindView(R.id.tv_tj)
    TextView tvTj;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.btn_login)
    Button btnLogin;

    public static String SDL_ID = "SDL_ID";
    public static String SDLV_ID = "SDLV_ID";
    @BindView(R.id.sv_list)
    SpringView svList;
    private SelectBillCarOneAdapter adapter;


    public static void actionStart(Context context, String sdl_id, String sdlv_id) {
        Intent intent = new Intent(context, SelectBillCarOneActivity.class);
        intent.putExtra(SDL_ID, sdl_id);
        intent.putExtra(SDLV_ID, sdlv_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("选择提货车辆");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_select_bill_car_one;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new SelectBillCarOneAdapter(this);
    }

    @Override
    protected void initDatas() {
        getData();
    }

    private void initSpringViewStyle() {
        svList.setType(SpringView.Type.FOLLOW);
        svList.setListener(onFreshListener);
        svList.setHeader(new SimpleHeader(this));
        svList.setFooter(new SimpleFooter(this));
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
    List<CarList.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().getVehicleList()).getVehicleList(PreferenceUtils.getInstance().getUserToken(), page + "").enqueue(new Callback<ApiResponse<CarList>>() {
            @Override
            public void onResponse(Call<ApiResponse<CarList>> call, Response<ApiResponse<CarList>> response) {
                if (svList != null) {
                    svList.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(SelectBillCarOneActivity.this, rvList, false, adapter);
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
            public void onFailure(Call<ApiResponse<CarList>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取数据失败");
            }
        });
    }

    private String v_id;

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (listBeans != null && listBeans.size() > 0) {
            for (int i = 0; i < listBeans.size(); i++) {
                if (listBeans.get(i).getType() == 1) {
                    v_id = listBeans.get(i).getV_id();
                    break;
                }

            }
        }
        if (TextUtils.isEmpty(v_id)) {
            ToastUtil.showToast("请选择提货车辆");
            return;
        }

        commit(v_id);
    }

    private void commit(String v_id) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().acceptDelivery()).acceptDelivery(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(SDL_ID),v_id,getIntent().getStringExtra(SDLV_ID)
               ).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("接单成功");
                    ReceivingOrderSuccessActivity.actionStart(SelectBillCarOneActivity.this);
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
