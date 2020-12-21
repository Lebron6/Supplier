//package com.ocean.supplier.view;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.ocean.supplier.R;
////import com.ocean.supplier.activity.BindNewPhoneActivity;
////import com.ocean.supplier.activity.SettingNewPasswordActivity;
//import com.ocean.supplier.api.BaseUrl;
//import com.ocean.supplier.api.HttpUtil;
//import com.ocean.supplier.entity.ApiResponse;
//import com.ocean.supplier.tools.PreferenceUtils;
//import com.ocean.supplier.tools.ToastUtil;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * 验证旧密码
// */
//
//public class VerifyPasswordDialog extends Dialog {
//
//
//    TextView tvSure;
//    TextView tvCancel;
//    EditText etOldPassword;
//    private Context context;
//    private int type;
//    private String phoneNum;
//
//    public VerifyPasswordDialog(Context context, int dialog, int type, String phoneNum) {
//
//        super(context);
//        this.context = context;
//        this.type = type;
//        this.phoneNum = phoneNum;
//    }
//
//    public VerifyPasswordDialog(Context context, int theme, int type) {
//        super(context, theme);
//        this.context = context;
//        this.type = type;
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dialog_verify_password);
//        initView();
//    }
//
//    private void initView() {
//        tvCancel = findViewById(R.id.tv_cancel);
//        tvSure = findViewById(R.id.tv_sure);
//        etOldPassword = findViewById(R.id.et_old_password);
//        tvCancel.setOnClickListener(onClickListener);
//        tvSure.setOnClickListener(onClickListener);
//    }
//
//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.tv_cancel:
//                    dismiss();
//                    break;
//                case R.id.tv_sure:
//                    if (TextUtils.isEmpty(etOldPassword.getText().toString())) {
//                        ToastUtil.showToast("请输入原密码");
//                        return;
//                    }
//                    HttpUtil.createRequest("VerifyPasswordDialog", BaseUrl.getInstence().confirmPassword()).confirmPassword(PreferenceUtils.getInstance().getUserToken(), etOldPassword.getText().toString()).enqueue(new Callback<ApiResponse>() {
//                        @Override
//                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                            if (response.body() != null) {
//                                if (response.body().getCode() == 1) {
//                                    ToastUtil.showToast("验证成功");
//                                    if (type == 0) {
//                                        SettingNewPasswordActivity.actionStart(context);
//                                    } else {
//                                        BindNewPhoneActivity.actionStart(context,phoneNum);
//                                    }
//                                    dismiss();
//                                } else {
//                                    ToastUtil.showToast(response.body().getMsg());
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<ApiResponse> call, Throwable t) {
//                            ToastUtil.showToast("网络异常:密码验证失败");
//                        }
//                    });
//                    break;
//            }
//        }
//    };
//
//}
