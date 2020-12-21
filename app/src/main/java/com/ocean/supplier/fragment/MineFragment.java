package com.ocean.supplier.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ocean.supplier.BuildConfig;
import com.ocean.supplier.R;
import com.ocean.supplier.activity.ClipImageActivity;
import com.ocean.supplier.activity.CompanyInfoActivity;
import com.ocean.supplier.activity.PasswordLoginActivity;
import com.ocean.supplier.activity.SettingActivity;
import com.ocean.supplier.activity.StaffManagementActivity;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.SettingInfo;
import com.ocean.supplier.entity.SettingResult;
import com.ocean.supplier.entity.UpLoadResult;
import com.ocean.supplier.tools.AppManager;
import com.ocean.supplier.tools.GlideCircleTransform;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.ToastUtil;
import com.ocean.supplier.tools.Utils;
import com.ocean.supplier.view.UpDateIconPop;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by James on 2020/6/29.
 * 个人中心
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.iv_3pl)
    ImageView iv3pl;
    @BindView(R.id.layout_3pl_manager)
    RelativeLayout layout3plManager;
    @BindView(R.id.iv_staff)
    ImageView ivStaff;
    @BindView(R.id.layout_staff_manager)
    RelativeLayout layoutStaffManager;
    @BindView(R.id.iv_enterprise)
    ImageView ivEnterprise;
    @BindView(R.id.layout_company_info)
    RelativeLayout layoutCompanyInfo;
    @BindView(R.id.iv_set)
    ImageView ivSet;
    @BindView(R.id.layout_setting)
    RelativeLayout layoutSetting;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.iv_contact)
    ImageView ivContact;
    @BindView(R.id.layout_call_service)
    RelativeLayout layoutCallService;
    @BindView(R.id.iv_e9)
    ImageView ivE9;
    @BindView(R.id.layout_about_us)
    RelativeLayout layoutAboutUs;
    @BindView(R.id.iv_invete)
    ImageView ivInvete;
    @BindView(R.id.layout_invite_driver)
    RelativeLayout layoutInviteDriver;
    @BindView(R.id.view_line)
    View viewLine;
    private String servicePhone;

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
    private UpDateIconPop upDateIconPop;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {

    }

    private void getUserInfo() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().getInfo()).settinInfo(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<SettingResult>>() {
            @Override
            public void onResponse(Call<ApiResponse<SettingResult>> call, Response<ApiResponse<SettingResult>> response) {
                if (response.body().getCode() == 1) {
                    Glide.with(getActivity()).load(response.body().getData().getHeadimg()).bitmapTransform(new GlideCircleTransform(getActivity())).into(ivUserIcon);
                    tvCompany.setText(response.body().getData().getCompany_name() + "(" + response.body().getData().getCompany_no() + ")");
                    tvUserName.setText(response.body().getData().getDepartment() + "-" + response.body().getData().getUsername());
                    servicePhone = response.body().getData().getTel();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<SettingResult>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取个人数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getUserInfo();
    }


    @OnClick({R.id.iv_user_icon,R.id.layout_about_us, R.id.layout_3pl_manager, R.id.layout_staff_manager, R.id.layout_company_info, R.id.layout_setting, R.id.layout_call_service,  R.id.layout_invite_driver})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_icon:
                upDateIconPop = new UpDateIconPop(getActivity(), itemsOnClick);
                upDateIconPop.showAtLocation(viewLine, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.layout_3pl_manager:
                break;
            case R.id.layout_staff_manager:
                StaffManagementActivity.actionStart(getActivity());
                break;
            case R.id.layout_company_info:
                CompanyInfoActivity.actionStart(getActivity());
                break;
            case R.id.layout_setting:
                SettingActivity.actionStart(getActivity());
                break;
            case R.id.layout_call_service:
                if (TextUtils.isEmpty(servicePhone)) {
                    ToastUtil.showToast("暂不支持客服热线");
                    return;
                }
                callPhone(servicePhone);
                break;
            case R.id.layout_about_us:
                break;
            case R.id.layout_invite_driver:

                break;
        }
    }

    /**
     * 拨打电话（直接拨打电话）
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        getActivity().startActivity(intent);
    }
    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_take_photo:                   //拍照取图
                    //权限判断
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到调用系统相机
                        gotoCamera();
                    }
                    break;
                case R.id.btn_chose:
                    // 3、调用从图库选取图片方法
                    //权限判断
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请READ_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
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
            Uri contentUri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
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
                    final String cropImagePath = Utils.getRealFilePathFromUri(getActivity(), uri);

                    File file = new File(cropImagePath);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                    HttpUtil.createRequest(TAG, BaseUrl.getInstence().uploadfile()).changeImage(PreferenceUtils.getInstance().getUserToken(),"2", part).enqueue(new Callback<ApiResponse<UpLoadResult>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<UpLoadResult>> call, Response<ApiResponse<UpLoadResult>> response) {
                            if (response.body() != null) {
                                if (response.body().getCode() == 1) {
                                    Glide.with(getActivity()).load(response.body().getData().getUrl()+response.body().getData().getPath()).bitmapTransform(new GlideCircleTransform(getActivity())).into(ivUserIcon);
                                    ToastUtil.showToast("头像修改成功");
                                    bind(response.body().getData().getPath());
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

    private void bind(String path) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().settingEdit()).bindUserIcon(PreferenceUtils.getInstance().getUserToken(),path).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
//                    ToastUtil.showToast("保存成功");
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:保存信息失败");
            }
        });
    }


    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(getActivity(), ClipImageActivity.class);
        intent.putExtra("type", 1);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }
}
