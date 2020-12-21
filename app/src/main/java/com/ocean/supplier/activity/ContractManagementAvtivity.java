package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.NavPagerAdapter;
import com.ocean.supplier.fragment.ContractStatusFragment;
import com.ocean.supplier.tools.TitleManger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by James on 2020/7/9.
 * 合同管理
 */
public class ContractManagementAvtivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rb_all)
    RadioButton rbAll;
    @BindView(R.id.rb_to_be_confirmed)
    RadioButton rbToBeConfirmed;
    @BindView(R.id.rb_have_in_hand)
    RadioButton rbHaveInHand;
    @BindView(R.id.rb_reject)
    RadioButton rbReject;
    @BindView(R.id.rg_contract)
    RadioGroup rgContract;
    @BindView(R.id.vp_contarct)
    ViewPager viewPager;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ContractManagementAvtivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("合同管理");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_contract_management;
    }

    @Override
    protected void initViews() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ContractStatusFragment(0));
        fragments.add(new ContractStatusFragment(2));
        fragments.add(new ContractStatusFragment(3));
        fragments.add(new ContractStatusFragment(4));

        NavPagerAdapter viewPagerAdapter = new NavPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.setData(fragments);
        viewPager.setAdapter(viewPagerAdapter);
        rgContract.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_all:
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_to_be_confirmed:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_have_in_hand:
                        viewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_reject:
                        viewPager.setCurrentItem(3, false);
                        break;
                }
            }
        });
        viewPager.setOnPageChangeListener(onPagerChangerListener);
        rbAll.setChecked(true);
    }
    ViewPager.OnPageChangeListener onPagerChangerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    rbAll.setChecked(true);
                    break;
                case 1:
                    rbToBeConfirmed.setChecked(true);
                    break;
                case 2:
                    rbHaveInHand.setChecked(true);
                    break;
                case 3:
                    rbReject.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    @Override
    protected void initDatas() {

    }
}
