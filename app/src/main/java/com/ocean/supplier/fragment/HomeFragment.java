package com.ocean.supplier.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.EarlyWarningAdapter;
import com.ocean.supplier.tools.RecyclerViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2020/6/29.
 * 首页
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.layout_waybill_inquiry)
    LinearLayout layoutWaybillInquiry;
    @BindView(R.id.layout_goods_management)
    LinearLayout layoutGoodsManagement;
    @BindView(R.id.layout_inventory_query)
    LinearLayout layoutInventoryQuery;
    @BindView(R.id.layout_system_board)
    LinearLayout layoutSystemBoard;
    @BindView(R.id.rv_early)
    RecyclerView rvEarly;
    @BindView(R.id.layout_pol)
    LinearLayout layoutPol;
    @BindView(R.id.layout_tips)
    LinearLayout layoutTips;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        EarlyWarningAdapter adapter=new EarlyWarningAdapter(getActivity());
        RecyclerViewHelper.initRecyclerViewH(getActivity(),rvEarly,adapter);
    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.iv_scan, R.id.layout_waybill_inquiry, R.id.layout_goods_management, R.id.layout_inventory_query, R.id.layout_system_board, R.id.layout_tips})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                break;
            case R.id.layout_waybill_inquiry:
                break;
            case R.id.layout_goods_management:

                break;
            case R.id.layout_inventory_query:
                break;
            case R.id.layout_system_board:
                break;
            case R.id.layout_tips:
                break;
        }
    }
}
