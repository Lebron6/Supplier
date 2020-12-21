package com.ocean.supplier.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 驳回运单原因
 */
public class RejectOperaRemarksDialog extends Dialog {


    TextView tvSure;
    TextView tvCancel;
    EditText etRemarks;
    private Context context;
    private String os_id;

    public RejectOperaRemarksDialog(Context context) {
        super(context);
    }

    public RejectOperaRemarksDialog(Context context, int theme, String os_id) {
        super(context, theme);
        this.context = context;
        this.os_id = os_id;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_reject_opera);
        initView();
    }

    private void initView() {
        tvCancel=findViewById(R.id.tv_cancel);
        tvSure=findViewById(R.id.tv_sure);
        etRemarks=findViewById(R.id.et_remarks);
        tvCancel.setOnClickListener(onClickListener);
        tvSure.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_cancel:
                    dismiss();
                    break;
                case R.id.tv_sure:
                    if (TextUtils.isEmpty(etRemarks.getText().toString())){
                        ToastUtil.showToast("请输入驳回原因");
                        return;
                    }
                    reject(os_id);
                    break;
            }
        }
    };

    private void reject(String os_id) {
        HttpUtil.createRequest("RejectOperaRemarksDialog", BaseUrl.getInstence().operationReject()).operaReject(PreferenceUtils.getInstance().getUserToken(),os_id,etRemarks.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body()!=null){
                    if (response.body().getCode()==1){
                        ToastUtil.showToast("驳回成功");
                        dismiss();
                    }else{
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
ToastUtil.showToast("网络异常:驳回失败");
            }
        });
    }


}
