package com.ocean.supplier.fragment.operasheet.nopear;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.NavPagerAdapter;
import com.ocean.supplier.fragment.BaseFragment;
import com.ocean.supplier.fragment.operasheet.nopear.OperaAllFragment;
import com.ocean.supplier.view.NavitationScrollLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by James on 2020/6/30.
 * 操作单管理-未完成
 */
public class NOperaFragment extends BaseFragment {
    @BindView(R.id.tab_bill)
    NavitationScrollLayout tabBill;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    public NOperaFragment() {
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_n_opera;
    }

    @Override
    protected void initViews() {
        List<Fragment> fragments=new ArrayList<>();
        String[] titles = new String[6];
        titles[0]="全部";
        titles[1]="受理";
        titles[2]="驳回";
        titles[3]="收货";
        titles[4]="调度";
        titles[5]="途中";
        for (int i = 0; i <titles.length ; i++) {
            fragments.add(new OperaAllFragment(i));
        }
        NavPagerAdapter viewPagerAdapter = new NavPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.setData(fragments);
        vpContent.setAdapter(viewPagerAdapter);
        tabBill.setViewPager(getActivity(), titles, vpContent, R.color.colorGray, R.color.colorMain, 14, 14, 14, true, R.color.colorMain, 0f, 15f, 15f, 56);
        tabBill.setNavLine(getActivity(), 2, R.color.colorMain);
    }

    @Override
    protected void initDatas() {

    }

}
