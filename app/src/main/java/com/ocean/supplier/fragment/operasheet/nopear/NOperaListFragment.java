package com.ocean.supplier.fragment.operasheet.nopear;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.supplier.R;
import com.ocean.supplier.activity.OperaDetailsActivity;
import com.ocean.supplier.adapter.NOperaAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.callback.NotiImp;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.NOperaListData;
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
 * 操作单列表
 */
public class NOperaListFragment extends BaseFragment {

    @BindView(R.id.rv_bill)
    RecyclerView rvBill;
    @BindView(R.id.sv_bill)
    SpringView svBill;
    private int type;
    private int status;
    private NOperaAdapter adapter;

    @SuppressLint("ValidFragment")
    public NOperaListFragment(int type, int status) {
        this.type = type;
        this.status = status;
    }

    public NOperaListFragment() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_opera_list;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new NOperaAdapter(getActivity(),type);
        adapter.setNotiImp(new NotiImp() {
            @Override
            public void noti() {
                getData();
            }
        });
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

    List<NOperaListData.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().transportOperationList()).transportOperationList(PreferenceUtils.getInstance().getUserToken(), page + "",  type+"", status + "").enqueue(new Callback<ApiResponse<NOperaListData>>() {
            @Override
            public void onResponse(Call<ApiResponse<NOperaListData>> call, Response<ApiResponse<NOperaListData>> response) {
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
                        adapter.setOnItemClickLitener(new NOperaAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
//                                OperaDetailsActivity.actionStart(getActivity(), listBeans.get(position).getOs_id());
                            }
                        });

                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<NOperaListData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getData();
    }

}
