package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.tools.TitleManger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/8/21.
 * 接单成功
 */
public class ReceivingOrderSuccessActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_commit_suc)
    ImageView ivCommitSuc;
    @BindView(R.id.layout_c)
    RelativeLayout layoutC;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ReceivingOrderSuccessActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("提单受理");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_receiving_orders_success;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
