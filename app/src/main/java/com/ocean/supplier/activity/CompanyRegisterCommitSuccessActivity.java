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
import com.ocean.supplier.tools.AppManager;
import com.ocean.supplier.tools.TitleManger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/8/21.
 * 公司注册提交成功
 */
public class CompanyRegisterCommitSuccessActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_commit_suc)
    ImageView ivCommitSuc;
    @BindView(R.id.tv_company_num)
    TextView tvCompanyNum;
    @BindView(R.id.layout_c)
    RelativeLayout layoutC;
    @BindView(R.id.btn_to_app)
    Button btnToApp;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;

    public static final String COMPANY_NUM = "COMPANY_NUM";

    public static void actionStart(Context context, String companyNum) {
        Intent intent = new Intent(context, CompanyRegisterCommitSuccessActivity.class);
        intent.putExtra(COMPANY_NUM, companyNum);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("提交成功");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_company_register_commit_success;
    }

    @Override
    protected void initViews() {
        tvCompanyNum.setText("企业编号：" + getIntent().getStringExtra(COMPANY_NUM));
    }

    @Override
    protected void initDatas() {

    }


    @OnClick(R.id.btn_to_app)
    public void onViewClicked() {
        AppManager.getAppManager().AppExit(this);
        PasswordLoginActivity.actionStart(this);
        finish();
    }
}
