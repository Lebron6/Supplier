package com.ocean.supplier.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ocean.supplier.BuildConfig;
import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.dialog.CommonPopWindow;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.CompanyInfo;
import com.ocean.supplier.entity.UpLoadResult;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.tools.ToastUtil;
import com.ocean.supplier.tools.Utils;
import com.ocean.supplier.view.UpDateIconPop;

import java.io.File;
import java.util.ArrayList;
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
import retrofit2.http.Header;

/**
 * Created by James on 2020/9/4.
 * 企业信息
 */
public class CompanyInfoActivity extends BaseActivity{

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_company_num)
    EditText etCompanyNum;
    @BindView(R.id.txt_phone_bind)
    TextView txtPhoneBind;
    @BindView(R.id.et_company_name)
    EditText etCompanyName;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_sh)
    EditText etSh;
    @BindView(R.id.tv_bank_name)
    EditText tvBankName;
    @BindView(R.id.tv_bank_num)
    EditText tvBankNum;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.et_dwdz)
    EditText etDwdz;
    private UpDateIconPop upDateIconPop;

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    private File tempFile;
    private String imageUrl;

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("企业信息");
        manger.setBack();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CompanyInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_company_info;
    }

    @Override
    protected void initViews() {
        getCompanyInfo();
    }


    private void getCompanyInfo() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().companyInfo()).getCompanyInfo(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<CompanyInfo>>() {
            @Override
            public void onResponse(Call<ApiResponse<CompanyInfo>> call, Response<ApiResponse<CompanyInfo>> response) {
                if (response.body().getCode() == 1) {
                    etCompanyNum.setText(response.body().getData().getCompany_no());
                    etCompanyName.setText(response.body().getData().getCompany_name());
                    etPhoneNum.setText(response.body().getData().getTel());
                    etSh.setText(response.body().getData().getTax_num());
                    etDwdz.setText(response.body().getData().getAddress());
                    tvBankNum.setText(response.body().getData().getBank_card());
                    tvBankName.setText(response.body().getData().getAccount_bank());
                    Glide.with(CompanyInfoActivity.this).load( response.body().getData().getLicenseimg()).into(ivPic);
//                    imageUrl = response.body().getData().getLicenseimg();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<CompanyInfo>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取设置数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.iv_pic, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pic:
                upDateIconPop = new UpDateIconPop(this, itemsOnClick);
                upDateIconPop.showAtLocation(viewLine, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;

            case R.id.btn_save:
                if (TextUtils.isEmpty(etCompanyName.getText().toString())) {
                    ToastUtil.showToast("请输入企业编号");
                    return;
                }
                if (TextUtils.isEmpty(etPhoneNum.getText().toString())) {
                    ToastUtil.showToast("请输入正确号码");
                    return;
                }
                if (TextUtils.isEmpty(etSh.getText().toString())) {
                    ToastUtil.showToast("请输入税号");
                    return;
                }
                if (TextUtils.isEmpty(etDwdz.getText().toString())) {
                    ToastUtil.showToast("请输入单位地址");
                    return;
                }
                if (TextUtils.isEmpty(tvBankName.getText().toString())) {
                    ToastUtil.showToast("请输入开户行");
                    return;
                }
                if (TextUtils.isEmpty(tvBankNum.getText().toString())) {
                    ToastUtil.showToast("请输入银行账号");
                    return;
                }
                commit();
                break;

        }
    }


    private void commit() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().companyEdit()).saveCompany(PreferenceUtils.getInstance().getUserToken(),etCompanyName.getText().toString(),imageUrl,etPhoneNum.getText().toString(),etDwdz.getText().toString(),tvBankName.getText().toString(),tvBankNum.getText().toString(),etSh.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("企业信息更新成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:企业信息上传失败");
            }
        });
    }


    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_take_photo:                   //拍照取图
                    //权限判断
                    if (ContextCompat.checkSelfPermission(CompanyInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(CompanyInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到调用系统相机
                        gotoCamera();
                    }
                    break;
                case R.id.btn_chose:
                    // 3、调用从图库选取图片方法
                    //权限判断
                    if (ContextCompat.checkSelfPermission(CompanyInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请READ_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(CompanyInfoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到相册
                        gotoPhoto();
                    }
                    break;
            }
            upDateIconPop.dismiss();
        }
    };

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
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Log.e("参数", BuildConfig.APPLICATION_ID + ".fileProvider");
            Uri contentUri = FileProvider.getUriForFile(CompanyInfoActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
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
                    final String cropImagePath = Utils.getRealFilePathFromUri(CompanyInfoActivity.this, uri);

                    File file = new File(cropImagePath);
                    MultipartBody.Part part = filesToMultipartBodyPart(file);
                    HttpUtil.createRequest(TAG, BaseUrl.getInstence().uploadfile()).upLoadFile(PreferenceUtils.getInstance().getUserToken(),"1", part).enqueue(new Callback<ApiResponse<UpLoadResult>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<UpLoadResult>> call, Response<ApiResponse<UpLoadResult>> response) {
                            if (response.body() != null) {
                                if (response.body().getCode() == 1) {
                                    Glide.with(CompanyInfoActivity.this).load(response.body().getData().getUrl() + response.body().getData().getPath()).into(ivPic);
                                    imageUrl = response.body().getData().getPath();
                                    ToastUtil.showToast("上传成功");
                                } else {
                                    ToastUtil.showToast(response.body().getMsg());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<UpLoadResult>> call, Throwable t) {
                            ToastUtil.showToast("网络异常:上传失败");
                        }
                    });
                }
                break;
        }
    }


    private MultipartBody.Part filesToMultipartBodyPart(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return part;
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
}
