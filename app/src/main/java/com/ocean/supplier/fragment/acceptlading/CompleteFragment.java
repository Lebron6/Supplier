package com.ocean.supplier.fragment.acceptlading;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.supplier.R;
import com.ocean.supplier.activity.ToBeAcceptDetailsActivity;
import com.ocean.supplier.adapter.CompleteAdapter;
import com.ocean.supplier.adapter.ToBeAcceptAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.DeliveryList;
import com.ocean.supplier.fragment.BaseFragment;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.RecyclerViewHelper;
import com.ocean.supplier.tools.SimpleFooter;
import com.ocean.supplier.tools.SimpleHeader;
import com.ocean.supplier.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/28.
 * 完成
 */
public class CompleteFragment extends BaseFragment {
    @BindView(R.id.rv_loading_type)
    RecyclerView rvLoadingType;
    @BindView(R.id.sv_list)
    SpringView svList;
    Unbinder unbinder;
    private CompleteAdapter adapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_complete;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new CompleteAdapter(getActivity());
    }

    @Override
    protected void initDatas() {
        getData();
    }

    private void initSpringViewStyle() {
        svList.setType(SpringView.Type.FOLLOW);
        svList.setListener(onFreshListener);
        svList.setHeader(new SimpleHeader(getActivity()));
        svList.setFooter(new SimpleFooter(getActivity()));
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
    List<DeliveryList.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().deliveryBillList()).deliveryBillList(PreferenceUtils.getInstance().getUserToken(), 1 + "", page + "").enqueue(new Callback<ApiResponse<DeliveryList>>() {
            @Override
            public void onResponse(Call<ApiResponse<DeliveryList>> call, Response<ApiResponse<DeliveryList>> response) {
                if (svList != null) {
                    svList.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvLoadingType, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData().getList());
                        }
                        adapter.setDatas(listBeans);

                        adapter.setOnItemClickLitener(new CompleteAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ToBeAcceptDetailsActivity.actionStart(getActivity(), listBeans.get(position).getSdl_id(), listBeans.get(position).getSdlv_id(), "1");
                            }
                        });
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<DeliveryList>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取数据失败");
            }
        });
    }

}
