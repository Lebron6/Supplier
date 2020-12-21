package com.ocean.supplier.fragment.operasheet.nopear;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.NavPagerAdapter;
import com.ocean.supplier.fragment.BaseFragment;
import com.ocean.supplier.view.NavitationScrollLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by James on 2020/6/30.
 * 操作单管理-未完成-全部
 */
public class OperaAllFragment extends BaseFragment {
    @BindView(R.id.tab_bill)
    NavitationScrollLayout tabBill;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    private int status;

    public OperaAllFragment() {
    }
    @SuppressLint("ValidFragment")
    public OperaAllFragment(int status) {
        this.status=status;
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
        titles[1]="提货";
        titles[2]="干线";
        titles[3]="派送";
        titles[4]="交货";
        titles[5]="其它";
        for (int i = 0; i <titles.length ; i++) {
            fragments.add(new NOperaListFragment(i,status));
        }
        NavPagerAdapter viewPagerAdapter = new NavPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.setData(fragments);
        vpContent.setAdapter(viewPagerAdapter);
        tabBill.setViewPager(getActivity(), titles, vpContent, R.color.colorGray, R.color.colorMain, 14, 14, 20, true, R.color.colorMain, 0f, 15f, 15f, 65);
        tabBill.setNavLine(getActivity(), 2, R.color.colorMain);
    }

    @Override
    protected void initDatas() {

    }

}
