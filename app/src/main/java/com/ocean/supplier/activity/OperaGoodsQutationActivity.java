package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.OperaQuotationAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.OperaGoodsListData;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.RecyclerViewHelper;
import com.ocean.supplier.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/9/1.
 * 报价单
 */
public class OperaGoodsQutationActivity extends BaseActivity {
    public static final String O_ID = "o_id";
    @BindView(R.id.layout_title_type)
    LinearLayout layoutTitleType;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.tv_five)
    TextView tvFive;
    @BindView(R.id.tv_six)
    TextView tvSix;
    @BindView(R.id.tv_seven)
    TextView tvSeven;
    @BindView(R.id.tv_eight)
    TextView tvEight;
    @BindView(R.id.tv_nine)
    TextView tvNine;
    @BindView(R.id.tv_weigth)
    TextView tvWeigth;
    @BindView(R.id.tv_volume)
    TextView tvVolume;
    @BindView(R.id.tv_jnum)
    TextView tvJnum;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;

    public static void actionStart(Context context, String os_id) {
        Intent intent = new Intent(context, OperaGoodsQutationActivity.class);
        intent.putExtra(O_ID, os_id);
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
        return R.layout.activity_opera_list;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().operationGoodsList()).operationListingGoodsList(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(O_ID)).enqueue(new Callback<ApiResponse<OperaGoodsListData>>() {
            @Override
            public void onResponse(Call<ApiResponse<OperaGoodsListData>> call, Response<ApiResponse<OperaGoodsListData>> response) {
                if (response.body().getCode() == 1) {
                    OperaQuotationAdapter quotationAdapter = new OperaQuotationAdapter(OperaGoodsQutationActivity.this);
                    quotationAdapter.setDatas(response.body().getData());
                    RecyclerViewHelper.initRecyclerViewV(OperaGoodsQutationActivity.this, rvList, false, quotationAdapter);
                    tvWeigth.setText("重量："+response.body().getData().getTotal_info().getTotal_weight()+"kg");
                    tvVolume.setText("体积："+response.body().getData().getTotal_info().getTotal_volume()+"m³");
                    tvJnum.setText("件数："+response.body().getData().getTotal_info().getGoods_jnum());
                    tvNum.setText("货物数量："+response.body().getData().getTotal_info().getGoods_num());
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<OperaGoodsListData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取数据失败");
            }
        });
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }
}
