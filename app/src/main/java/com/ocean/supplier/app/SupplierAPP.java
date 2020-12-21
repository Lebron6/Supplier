package com.ocean.supplier.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.taobao.sophix.SophixManager;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by James on 2020/4/7.
 */
public class SupplierAPP extends Application {

    private static Context application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        MultiDex.install(this);
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();//查询是否有新的补丁
    }

    public static Context getApplication() {
        return application;
    }

    public static String PATH_LOGCAT;

}
