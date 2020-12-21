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
import com.ocean.supplier.fragment.operasheet.nopear.NOperaFragment;
import com.ocean.supplier.fragment.operasheet.yopera.YOperaFragment;
import com.ocean.supplier.fragment.operasheet.yopera.YOperaListFragment;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by James on 2020/7/28.
 * 操作单管理
 */
public class OperationSheetManagementActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rb_f)
    RadioButton rbF;
    @BindView(R.id.rb_t)
    RadioButton rbT;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.vp_bill_type)
    CustomViewPager vpBillType;
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, OperationSheetManagementActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initTitle() {
        TitleManger manger= TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("操作单管理");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_operation_sheet_management;
    }

    @Override
    protected void initViews() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NOperaFragment());
        fragments.add(new YOperaFragment());

        NavPagerAdapter viewPagerAdapter = new NavPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.setData(fragments);
        vpBillType.setAdapter(viewPagerAdapter);
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_f:
                        vpBillType.setCurrentItem(0, false);
                        break;
                    case R.id.rb_t:
                        vpBillType.setCurrentItem(1, false);
                        break;
                }
            }
        });
        vpBillType.setOnPageChangeListener(onPagerChangerListener);
        rbF.setChecked(true);
    }

    ViewPager.OnPageChangeListener onPagerChangerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    rbF.setChecked(true);
                    break;
                case 1:
                    rbT.setChecked(true);
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
