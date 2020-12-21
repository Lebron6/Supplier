package com.ocean.supplier.fragment.operasheet.yopera;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.supplier.R;
import com.ocean.supplier.activity.OperaDetailsActivity;
import com.ocean.supplier.adapter.NOperaAdapter;
import com.ocean.supplier.adapter.YOperaAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.NOperaListData;
import com.ocean.supplier.entity.YOperaListData;
import com.ocean.supplier.fragment.BaseFragment;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.RecyclerViewHelper;
import com.ocean.supplier.tools.SimpleFooter;
import com.ocean.supplier.tools.SimpleHeader;
import com.ocean.supplier.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/6/29.
 * 操作单列表-已完成
 */
public class YOperaListFragment extends BaseFragment {

    @BindView(R.id.rv_bill)
    RecyclerView rvBill;
    @BindView(R.id.sv_bill)
    SpringView svBill;
    private int type;
    private int status;
    private YOperaAdapter adapter;

    @SuppressLint("ValidFragment")
    public YOperaListFragment(int type, int status) {
        this.type = type;
        this.status = status;
    }

    public YOperaListFragment() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_opera_list;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new YOperaAdapter(getActivity(),type);

    }

    private void initSpringViewStyle() {
        svBill.setType(SpringView.Type.FOLLOW);
        svBill.setListener(onFreshListener);
        svBill.setHeader(new SimpleHeader(getActivity()));
        svBill.setFooter(new SimpleFooter(getActivity()));
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

    List<YOperaListData.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().transportOperationCompleteList()).transportOperationCompleteList(PreferenceUtils.getInstance().getUserToken(),  page+ "",  type+"").enqueue(new Callback<ApiResponse<YOperaListData>>() {
            @Override
            public void onResponse(Call<ApiResponse<YOperaListData>> call, Response<ApiResponse<YOperaListData>> response) {
                if (svBill != null) {
                    svBill.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvBill, false, adapter);
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
            public void onFailure(Call<ApiResponse<YOperaListData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getData();
    }

}
