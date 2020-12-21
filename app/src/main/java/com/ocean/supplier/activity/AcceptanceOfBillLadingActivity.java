package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.NavPagerAdapter;
import com.ocean.supplier.fragment.acceptlading.CompleteFragment;
import com.ocean.supplier.fragment.acceptlading.ToBeAcceptedFragment;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.view.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by James on 2020/8/28.
 * 提单受理
 */
public class AcceptanceOfBillLadingActivity extends BaseActivity {
    @BindView(R.id.nav_tab)
    NavitationLayout navTab;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AcceptanceOfBillLadingActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("提单受理");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_acceptance_of_bill_lading;
    }

    @Override
    protected void initViews() {
        String[] strings = {"待受理", "完成"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ToBeAcceptedFragment());
        fragments.add(new CompleteFragment());
        NavPagerAdapter viewPagerAdapter2 = new NavPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter2.setData(fragments);
        vpFragment.setAdapter(viewPagerAdapter2);
        vpFragment.setCurrentItem(0);
        navTab.setViewPager(this, strings, vpFragment, R.color.colorMainBlack, R.color.colorMain, 14, 16, 0, 82, true);
        navTab.setBgLine(this, 0, R.color.colorMain);
        navTab.setNavLine(this, 2, R.color.colorMain, 0);
    }

    @Override
    protected void initDatas() {

    }

}
