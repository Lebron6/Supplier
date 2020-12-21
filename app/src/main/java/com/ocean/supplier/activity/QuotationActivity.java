package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.QuotationAdapter;
import com.ocean.supplier.adapter.QuotationTitleAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.QuotationData;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.RecyclerViewHelper;
import com.ocean.supplier.tools.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/9/1.
 * 报价单
 */
public class QuotationActivity extends BaseActivity {
    public static final String Q_ID = "Q_ID";
    @BindView(R.id.rv_title)
    RecyclerView rvTitle;
    @BindView(R.id.layout_title_type)
    LinearLayout layoutTitleType;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;

    public static void actionStart(Context context, String q_id) {
        Intent intent = new Intent(context, QuotationActivity.class);
        intent.putExtra(Q_ID, q_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_quotation_constract;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().quotationInfo()).contractReject(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(Q_ID)).enqueue(new Callback<ApiResponse<QuotationData>>() {
            @Override
            public void onResponse(Call<ApiResponse<QuotationData>> call, Response<ApiResponse<QuotationData>> response) {
                if (response.body().getCode() == 1) {
                    QuotationTitleAdapter adapter=new QuotationTitleAdapter(QuotationActivity.this);
                    adapter.setDatas(response.body().getData());
                    RecyclerViewHelper.initRecyclerViewH(QuotationActivity.this,rvTitle,false,adapter);

                    QuotationAdapter quotationAdapter=new QuotationAdapter(QuotationActivity.this);
                    quotationAdapter.setDatas(response.body().getData());
                    RecyclerViewHelper.initRecyclerViewV(QuotationActivity.this,rvList,false,quotationAdapter);
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<QuotationData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取数据失败");
            }
        });
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

}
