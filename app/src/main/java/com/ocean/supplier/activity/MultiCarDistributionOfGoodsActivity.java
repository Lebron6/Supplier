package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.FillBillAdapter;
import com.ocean.supplier.adapter.SelectBillCarTwoAdapter;
import com.ocean.supplier.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/9/1.
 * 多车分配任务
 */
public class MultiCarDistributionOfGoodsActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;
    @BindView(R.id.tv_driver_name)
    TextView tvDriverName;
    @BindView(R.id.tv_zdzl)
    TextView tvZdzl;
    @BindView(R.id.tv_yyzl)
    TextView tvYyzl;
    @BindView(R.id.tv_syzl)
    TextView tvSyzl;
    @BindView(R.id.tv_zdtj)
    TextView tvZdtj;
    @BindView(R.id.tv_yytj)
    TextView tvYytj;
    @BindView(R.id.tv_cz)
    TextView tvCz;
    @BindView(R.id.tv_sytj)
    TextView tvSytj;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    @Override
    protected void initTitle() {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MultiCarDistributionOfGoodsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_multi_car_distribution_of_goods;
    }

    @Override
    protected void initViews() {
        FillBillAdapter adapter = new FillBillAdapter(this);
        RecyclerViewHelper.initRecyclerViewV(this, rvList, adapter);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
        ReceivingOrderSuccessActivity.actionStart(this);
    }
}
