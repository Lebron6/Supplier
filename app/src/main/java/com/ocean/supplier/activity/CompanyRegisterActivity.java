package com.ocean.supplier.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.api.SupplierApi;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.RegisterCommitData;
import com.ocean.supplier.entity.RegisterResult;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.tools.ToastUtil;
import com.ocean.supplier.tools.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static okhttp3.MultipartBody.FORM;

/**
 * Created by James on 2020/8/20.
 * 企业注册
 */
public class CompanyRegisterActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_company_name)
    EditText etCompanyName;
    @BindView(R.id.et_license)
    EditText etLicense;
    @BindView(R.id.tv_upload_license)
    TextView tvUploadLicense;
    @BindView(R.id.layout_upload)
    LinearLayout layoutUpload;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_phone_code)
    EditText etPhoneCode;
    @BindView(R.id.tv_get_phone_code)
    TextView tvGetPhoneCode;
    @BindView(R.id.cb_show_password)
    CheckBox cbShowPassword;
    @BindView(R.id.tv_user_agreement)
    TextView tvUserAgreement;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.tv_to_login)
    TextView tvToLogin;
    @BindView(R.id.et_password)
    EditText etPassword;
    private String cropImagePath;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CompanyRegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("公司注册");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_company_register;
    }

    @Override
    protected void initViews() {
etLicense.setEnabled(false);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.tv_get_phone_code, R.id.layout_upload, R.id.btn_commit, R.id.tv_to_login, R.id.tv_user_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_phone_code:
                if (TextUtils.isEmpty(etAccount.getText().toString())) {
                    ToastUtil.showToast("请输入手机号码");
                    return;
                }
                if (!Utils.isMobileNO(etAccount.getText().toString())) {
                    ToastUtil.showToast("手机号码格式错误");
                    return;
                }
                getPhoneCode(etAccount.getText().toString());
                break;
            case R.id.layout_upload:
                // 3、调用从图库选取图片方法
                //权限判断
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    gotoPhoto();
                }
                break;
            case R.id.btn_commit:
                if (TextUtils.isEmpty(etCompanyName.getText().toString())) {
                    ToastUtil.showToast("请输入公司名称");
                    return;
                }

                if (TextUtils.isEmpty(etAccount.getText().toString())) {
                    ToastUtil.showToast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    ToastUtil.showToast("请输入密码");
                    return;
                }
                if (TextUtils.isEmpty(etPhoneCode.getText().toString())) {
                    ToastUtil.showToast("请输入验证码");
                    return;
                }
                if (cbShowPassword.isChecked() == false) {
                    ToastUtil.showToast("请阅读并勾选用户注册协议");
                    return;
                }
                    register();
                break;
            case R.id.tv_to_login:
                PasswordLoginActivity.actionStart(this);
                break;
            case R.id.tv_user_agreement:
                WebViewActivity.actionStart(this, "服务协议", "https://www.baidu.com");
                break;
        }
    }

    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;

    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", 0);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    cropImagePath = Utils.getRealFilePathFromUri(this, uri);
                    etLicense.setText(cropImagePath.substring(cropImagePath.lastIndexOf("/") + 1));
                }
                break;
        }
    }


    private void register() {

        SupplierApi supplierApi = HttpUtil.createRequest(TAG, BaseUrl.getInstence().loginRegister());
        Call<ApiResponse<RegisterResult>> register;
        if (TextUtils.isEmpty(cropImagePath)) {
            register = supplierApi.register(etCompanyName.getText().toString(),etAccount.getText().toString(),etPhoneCode.getText().toString()
            ,etPassword.getText().toString());
        } else {
            Map<String, RequestBody> params = new HashMap<>();
            //以下参数是伪代码，参数需要换成自己服务器支持的
            params.put("company_name", convertToRequestBody(etCompanyName.getText().toString()));
            params.put("phone", convertToRequestBody(etAccount.getText().toString()));
            params.put("code", convertToRequestBody(etPhoneCode.getText().toString()));
            params.put("password", convertToRequestBody(etPassword.getText().toString()));
            File file = new File(cropImagePath);
            MultipartBody.Part part = filesToMultipartBodyPart(file);
            register = supplierApi.register(params, part);
        }
        register.enqueue(new Callback<ApiResponse<RegisterResult>>() {
            @Override
            public void onResponse(Call<ApiResponse<RegisterResult>> call, Response<ApiResponse<RegisterResult>> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        CompanyRegisterCommitSuccessActivity.actionStart(CompanyRegisterActivity.this, response.body().getData().getCompany_no());
                        finish();
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<RegisterResult>> call, Throwable t) {
                ToastUtil.showToast("网络异常:上传失败");
            }
        });
    }

    private RequestBody convertToRequestBody(String param) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), param);
        return requestBody;
    }

    private MultipartBody.Part filesToMultipartBodyPart(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("license", file.getName(), requestBody);
        return part;
    }

    private void getPhoneCode(String phoneNum) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().sendSMS()).sendSMS(phoneNum).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    TimeCount time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
                    time.start();// 开始计时
                    ToastUtil.showToast("验证码已发送，注意查收");
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取验证码失败");
            }
        });
    }

    // /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 计时过程显示
            try {
                tvGetPhoneCode.setEnabled(false);
                tvGetPhoneCode.setClickable(false);
                tvGetPhoneCode.setText(millisUntilFinished / 1000 + "秒");
            } catch (Exception e) {

            }

        }

        @Override
        public void onFinish() {// 计时完毕时触发
            try {
                tvGetPhoneCode.setEnabled(true);
                tvGetPhoneCode.setText("重新验证");
                tvGetPhoneCode.setClickable(true);
                tvGetPhoneCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPhoneCode(etAccount.getText().toString());
                    }
                });
            } catch (Exception E) {
            }

        }

    }
}
