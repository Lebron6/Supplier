package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.SelectBillCarTwoAdapter;
import com.ocean.supplier.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/8/31.
 * 选择提货车辆2
 */
public class SelectBillCarTwoActivity extends BaseActivity {


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
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.layout_last)
    LinearLayout layoutLast;
    @BindView(R.id.layout_commit)
    LinearLayout layoutCommit;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SelectBillCarTwoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        SelectBillCarTwoAdapter adapter = new SelectBillCarTwoAdapter(this);
        RecyclerViewHelper.initRecyclerViewV(this, rvList, adapter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_select_bill_car_two;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.layout_last, R.id.layout_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_last:
                break;
            case R.id.layout_commit:
                break;
        }
    }
}
