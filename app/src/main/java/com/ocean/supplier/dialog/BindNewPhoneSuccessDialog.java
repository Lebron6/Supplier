package com.ocean.supplier.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.activity.PasswordLoginActivity;
import com.ocean.supplier.tools.AppManager;
import com.ocean.supplier.tools.PreferenceUtils;

/**
 * 绑定新手机成功
 */
public class BindNewPhoneSuccessDialog extends Dialog {


    TextView tvSure;
    private Context context;

    public BindNewPhoneSuccessDialog(Context context) {
        super(context);
    }

    public BindNewPhoneSuccessDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bind_new_phone);
        initView();
    }

    private void initView() {
        tvSure=findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_sure:
                    dismiss();
                    break;
            }
        }
    };

    @Override
    public void dismiss() {
        super.dismiss();
        PreferenceUtils.getInstance().loginOut();
        AppManager.getAppManager().AppExit(context);
        PasswordLoginActivity.actionStart(context);
    }
}
