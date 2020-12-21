package com.ocean.supplier.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ocean.supplier.R;
import com.ocean.supplier.adapter.MainPageAdapter;
import com.ocean.supplier.jpush.SetAlias;
import com.ocean.supplier.tools.AppManager;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.ToastUtil;
import com.ocean.supplier.view.CustomViewPager;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity  implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.vp_content)
    CustomViewPager vpContent;
    @BindView(R.id.home)
    RadioButton home;
    @BindView(R.id.bill_of_lading)
    RadioButton billOfLading;
    @BindView(R.id.mine)
    RadioButton mine;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    private MainPageAdapter adapter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        checkQX();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        adapter = new MainPageAdapter(getSupportFragmentManager());
        vpContent.setAdapter(adapter);
//        vpContent.setOffscreenPageLimit(2);
        vpContent.setOnPageChangeListener(onPagerChangerListener);
        rgGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        home.setChecked(true);
    }

    ViewPager.OnPageChangeListener onPagerChangerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    home.setChecked(true);
                    break;
                case 1:
                    billOfLading.setChecked(true);
                    break;
                case 2:
                    mine.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            setTabSelection(checkedId);
        }
    };

    private void setTabSelection(int checkedId) {
        switch (checkedId) {
            case R.id.home:
                vpContent.setCurrentItem(0, false);
                break;
            case R.id.bill_of_lading:
                vpContent.setCurrentItem(1, false);
                break;
            case R.id.mine:
                vpContent.setCurrentItem(2, false);
                break;
        }
    }
    @Override
    protected void initDatas() {
        new SetAlias(this, PreferenceUtils.getInstance().getUserId() + "").setAlias();   //设置极光推送别名
    }
    private void checkQX() {
        if (Build.VERSION.SDK_INT >= 23) {
            //打电话的权限
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            if (EasyPermissions.hasPermissions(this, mPermissionList)) {
                //已经同意过

            } else {
                //未同意过,或者说是拒绝了，再次申请权限
                EasyPermissions.requestPermissions(
                        this,  //上下文
                        "需要拨打电话的权限", //提示文言
                        1, //请求码
                        mPermissionList //权限列表
                );
            }
        }
    }

    //同意授权
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        //跳转到onPermissionsGranted或者onPermissionsDenied去回调授权结果
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        // ...
        Log.i(TAG, "onPermissionsGranted:" + requestCode + ":" + list.size());
    }

    //拒绝授权
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // Some permissions have been denied
        // ...

        Log.i(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
        ToastUtil.showToast("请在APP管理处为APP添加应有权限");
        AppManager.getAppManager().AppExit(this);
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
////            finish();
//            new AppSettingsDialog.Builder(this).build().show();
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            //拒绝授权后，从系统设置了授权后，返回APP进行相应的操作
            Log.i(TAG, "onPermissionsDenied:------>自定义设置授权后返回APP");
        }
    }
}
