package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;

import com.ocean.supplier.R;
import com.ocean.supplier.tools.TitleManger;

/**
 * Created by James on 2020/8/4.
 * 关于E9
 */
public class WebViewActivity extends BaseActivity{
    public static final String URL = "url";
    public static final String TITLE = "title";


    public static void actionStart(Context context, String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(TITLE));
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
