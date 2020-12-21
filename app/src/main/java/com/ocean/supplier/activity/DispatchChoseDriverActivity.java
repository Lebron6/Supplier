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
import com.liaoinstan.springview.widget.SpringView;
import com.ocean.supplier.R;
import com.ocean.supplier.adapter.ContarctStatusAdapter;
import com.ocean.supplier.adapter.DispatchDriverAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.ContractList;
import com.ocean.supplier.entity.DispactchList;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.RecyclerViewHelper;
import com.ocean.supplier.tools.SimpleFooter;
import com.ocean.supplier.tools.SimpleHeader;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/11/19.
 * 调度选择司机
 */
public class DispatchChoseDriverActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sv_list)
    SpringView svList;
    public static final int DRIVER = 100;
    public static void actionStartForResult(Activity context) {
        Intent intent = new Intent(context, DispatchChoseDriverActivity.class);
        context.startActivityForResult(intent, DRIVER);
    }

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("选择司机");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_dispatch_select;
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

    List<DispactchList.ListBean> listBeans = new ArrayList<>();
    public static final String CALLBACK = "callBack";
    private void getData() {
        HttpUtil.createRequest( TAG,BaseUrl.getInstence().operationDriverList()).operationDriverList(PreferenceUtils.getInstance().getUserToken(),  page + "").enqueue(new Callback<ApiResponse<DispactchList>>() {
            @Override
            public void onResponse(Call<ApiResponse<DispactchList>> call, Response<ApiResponse<DispactchList>> response) {
                if (svList != null) {
                    svList.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(DispatchChoseDriverActivity.this, rvList, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData().getList());
                        }
                        adapter.setDatas(listBeans);

                        adapter.setOnItemClickLitener(new DispatchDriverAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent();
                                intent.putExtra(CALLBACK, new Gson().toJson(listBeans.get(position)));
                                setResult(3, intent);
                                finish();
                            }
                        });
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<DispactchList>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取司机失败");
            }
        });
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
         adapter = new DispatchDriverAdapter(this);

    }
    DispatchDriverAdapter adapter;
    @Override
    protected void initDatas() {
        getData();
    }

}
