package com.ocean.supplier.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.callback.OnTypeSelectImp;
import com.ocean.supplier.dialog.TypeSelectWindow;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.DriverInfo;
import com.ocean.supplier.entity.DriverList;
import com.ocean.supplier.entity.GetCarType;
import com.ocean.supplier.entity.VehicleGetInfo;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.tools.ToastUtil;
import com.ocean.supplier.tools.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ocean.supplier.activity.DriverSelectActivity.CALLBACK;
import static com.ocean.supplier.activity.DriverSelectActivity.PARMS;

/**
 * Created by James on 2020/8/25.
 * 添加车辆
 */
public class AddCarActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout_cph)
    LinearLayout layoutCph;
    @BindView(R.id.et_cph)
    EditText etCph;
    @BindView(R.id.layout_xszh)
    LinearLayout layoutXszh;
    @BindView(R.id.et_xszh)
    EditText etXszh;
    @BindView(R.id.layout_ccspsj)
    LinearLayout layoutCcspsj;
    @BindView(R.id.tv_chose_time)
    TextView tvChoseTime;
    @BindView(R.id.layout_zdzzzl)
    LinearLayout layoutZdzzzl;
    @BindView(R.id.et_zdzzzl)
    EditText etZdzzzl;
    @BindView(R.id.layout_zdzztj)
    LinearLayout layoutZdzztj;
    @BindView(R.id.et_zdzztj)
    EditText etZdzztj;
    @BindView(R.id.txt_must_chose_1)
    TextView txtMustChose1;
    @BindView(R.id.txt_clzl)
    TextView txtClzl;
    @BindView(R.id.tv_clzl)
    TextView tvClzl;
    @BindView(R.id.iv_down_cl)
    ImageView ivDownCl;
    @BindView(R.id.layout_clzl)
    RelativeLayout layoutClzl;
    @BindView(R.id.layout_cx)
    LinearLayout layoutCx;
    @BindView(R.id.et_cx)
    EditText etCx;
    @BindView(R.id.layout_zzs)
    LinearLayout layoutZzs;
    @BindView(R.id.et_zzs)
    EditText etZzs;
    @BindView(R.id.txt_must_chose)
    TextView txtMustChose;
    @BindView(R.id.txt_mrsj)
    TextView txtMrsj;
    @BindView(R.id.tv_mrsj)
    TextView tvMrsj;
    @BindView(R.id.iv_down)
    ImageView ivDown;
    @BindView(R.id.layout_mrsj)
    RelativeLayout layoutMrsj;
    @BindView(R.id.layout_gps)
    LinearLayout layoutGps;
    @BindView(R.id.et_gps)
    EditText etGps;
    @BindView(R.id.layout_ys)
    LinearLayout layoutYs;
    @BindView(R.id.et_ys)
    EditText etYs;
    @BindView(R.id.layout_remarks)
    LinearLayout layoutRemarks;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.rb_z)
    RadioButton rbZ;
    @BindView(R.id.rb_j)
    RadioButton rbJ;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.layout_center)
    RelativeLayout layoutCenter;
    @BindView(R.id.tv_keep_and_go)
    TextView tvKeepAndGo;
    @BindView(R.id.btn_keep)
    Button btnKeep;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.view_line)
    View viewLine;
    private ArrayAdapter brandAdapter;
    private List<String> brands;
    private DriverList.ListBean data;
    private GetCarType carType;


    private String carTypeId;//选择的车辆类型
    private String driverId;//选择的司机

    public static String TYPE="TYPE";//编辑车辆信息
    public static String VEHICLE_ID="VEHICLE_ID";//编辑车辆信息

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("添加车辆");
        manger.setBack();
    }
    public static void actionStart(Context context,String type,String vehicle_id) {
        Intent intent = new Intent(context, AddCarActivity.class);
        intent.putExtra(TYPE,type);
        intent.putExtra(VEHICLE_ID,vehicle_id);
        context.startActivity(intent);
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_add_car;
    }

    @Override
    protected void initViews() {
        getCarType();
        if (getIntent().getStringExtra(TYPE).equals("EDIT")){
            HttpUtil.createRequest(TAG,BaseUrl.getInstence().vehicleGetInfo()).vehicleGetInfo(PreferenceUtils.getInstance().getUserToken(),getIntent().getStringExtra(VEHICLE_ID)).enqueue(new Callback<ApiResponse<VehicleGetInfo>>() {
                @Override
                public void onResponse(Call<ApiResponse<VehicleGetInfo>> call, Response<ApiResponse<VehicleGetInfo>> response) {
                    if (response.body()!=null){
                        if (response.body().getCode()==1){
                            etCph.setText(response.body().getData().getVehicle().getNum());
                            etXszh.setText(response.body().getData().getVehicle().getRunNum());
                            tvChoseTime.setText(response.body().getData().getVehicle().getF_time());
                            etZdzzzl.setText(response.body().getData().getVehicle().getMax_weight());
                            etZdzztj.setText(response.body().getData().getVehicle().getMax_volume());
                            etCx.setText(response.body().getData().getVehicle().getCar_info());
                            etZzs.setText(response.body().getData().getVehicle().getMake_car());
                            etGps.setText(response.body().getData().getVehicle().getGps());
                            etYs.setText(response.body().getData().getVehicle().getColour_car());
                            etRemarks.setText(response.body().getData().getVehicle().getRemarks());
                            tvMrsj.setText(response.body().getData().getVehicle().getName());
                            tvClzl.setText(response.body().getData().getVehicle().getCar_type());
                            driverId=response.body().getData().getVehicle().getDriver_id();
                            if (carType!=null&&carType.getData()!=null&&carType.getData().size()>0){
                                for (int i = 0; i < carType.getData().size(); i++) {
                                    if ((carType.getData().get(i).getId()+"").equals(response.body().getData().getVehicle().getCar_type())){
                                        tvClzl.setText(response.body().getData().getVehicle().getName());
                                    }
                                }
                            }
                            rbZ.setChecked(response.body().getData().getVehicle().getSys_status().equals("1")?true:false);
                        }else{
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<VehicleGetInfo>> call, Throwable t) {
                    Log.e("yyyy",t.toString());
                    ToastUtil.showToast("网络异常:编辑获取车辆信息失败");
                }
            });
        }
    }

    @Override
    protected void initDatas() {

    }

    private void getCarType() {
        HttpUtil.createRequest(TAG,BaseUrl.getInstence().vehicleGetCarType()).vehicleGetCarType(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<GetCarType>() {
            @Override
            public void onResponse(Call<GetCarType> call, Response<GetCarType> response) {
                carType = response.body();
                if (carType != null) {
                    if (carType.getCode() == 1) {
                        brands = new ArrayList<>();
                        for (int i = 0; i < carType.getData().size(); i++) {
                            brands.add(carType.getData().get(i).getName());
                        }
                        brandAdapter = new ArrayAdapter(AddCarActivity.this, R.layout.item_type, R.id.tv_popqusetion, brands);

                    } else {
                        ToastUtil.showToast(carType.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCarType> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取车辆类型失败");
            }
        });
    }

    OnTypeSelectImp typeImpl = new OnTypeSelectImp() {
        @Override
        public void select(int postion) {
            tvClzl.setText(brands.get(postion));
            carTypeId = carType.getData().get(postion).getId()+"";
        }
    };
    private String result;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case PARMS:
                if (intent != null && intent.getExtras() != null) {
                    Bundle bundle = intent.getExtras();
                    result = bundle.getString(CALLBACK);
                    Log.e("返回的数据", result);
                    if (!TextUtils.isEmpty(result)) {
                        data = new Gson().fromJson(result, DriverList.ListBean.class);
                        HttpUtil.createRequest(TAG, BaseUrl.getInstence().driverGetInfo()).driverGetInfo(PreferenceUtils.getInstance().getUserToken(), data.getS_driver_id()).enqueue(new Callback<ApiResponse<DriverInfo>>() {
                            @Override
                            public void onResponse(Call<ApiResponse<DriverInfo>> call, Response<ApiResponse<DriverInfo>> response) {
                                if (response.body().getCode() == 1) {
                                    driverId = response.body().getData().getDriver_id();
                                    tvMrsj.setText(response.body().getData().getName());
                                } else {
                                    ToastUtil.showToast(response.body().getMsg());
                                }

                            }

                            @Override
                            public void onFailure(Call<ApiResponse<DriverInfo>> call, Throwable t) {
                                ToastUtil.showToast("网络异常:获取司机信息失败");
                            }
                        });

                    }

                }

                break;
        }
    }
    @OnClick({R.id.layout_clzl, R.id.layout_mrsj, R.id.tv_keep_and_go, R.id.btn_keep,R.id.tv_chose_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_clzl:
                TypeSelectWindow brandWindow = new TypeSelectWindow(this);
                brandWindow.showView(viewLine, brandAdapter, typeImpl);
                break;
            case R.id.layout_mrsj:
                DriverSelectActivity.actionStartForResult(this);
                break;
            case R.id.tv_keep_and_go:
                test(0);
                break;
            case R.id.btn_keep:
                test(1);
                break;
            case R.id.tv_chose_time:
                final Calendar ca = Calendar.getInstance();
                startYear = ca.get(Calendar.YEAR);
                startMonth = ca.get(Calendar.MONTH);
                startDay = ca.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(AddCarActivity.this, R.style.MyDatePickerDialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                startTime = (year + "-" + (monthOfYear + 1)
                                        + "-" + dayOfMonth);
                                tvChoseTime.setText(startTime);
                            }
                        }, startYear, startMonth, startDay).show();
                break;
        }
    }
    private String startTime = "";
    private int startYear, startMonth, startDay;
    private void test(int type) {
        if (TextUtils.isEmpty(etCph.getText().toString())) {
            ToastUtil.showToast("请输入车牌号");
            return;
        }
        if (TextUtils.isEmpty(etXszh.getText().toString())) {
            ToastUtil.showToast("请输入行驶证号");
            return;
        }
        if (TextUtils.isEmpty(tvChoseTime.getText().toString())) {
            ToastUtil.showToast("请选择初次上牌时间");
            return;
        }
        if (TextUtils.isEmpty(etZdzzzl.getText().toString())) {
            ToastUtil.showToast("请输入最大装载重量");
            return;
        }
        if (TextUtils.isEmpty(etZdzztj.getText().toString())) {
            ToastUtil.showToast("请输入最大装载体积");
            return;
        }
        if (TextUtils.isEmpty(tvClzl.getText().toString())||tvClzl.getText().toString().equals("请选择")){
            ToastUtil.showToast("请选择车辆种类");
            return;
        }
        if (TextUtils.isEmpty(etCx.getText().toString())) {
            ToastUtil.showToast("请输入车型");
            return;
        }
        if (TextUtils.isEmpty(etZzs.getText().toString())) {
            ToastUtil.showToast("请输入制造商");
            return;
        }
        if (TextUtils.isEmpty(tvMrsj.getText().toString())||tvMrsj.getText().toString().equals("请选择")){
            ToastUtil.showToast("请选择默认司机");
            return;
        }
//        if (TextUtils.isEmpty(etGps.getText().toString())) {
//            ToastUtil.showToast("请输入GPS");
//            return;
//        }
//        if (TextUtils.isEmpty(etYs.getText().toString())) {
//            ToastUtil.showToast("请输入颜色");
//            return;
//        }
        if (getIntent().getStringExtra(TYPE).equals("EDIT")){
            commit(getIntent().getStringExtra(VEHICLE_ID),type);
        }else{
            commit("",type);
        }
    }

    private void commit(String vehicle_id, final int type) {
        try {
            HttpUtil.createRequest(TAG,BaseUrl.getInstence().vehicleSave()).vehicleSave(PreferenceUtils.getInstance().getUserToken(),
                    vehicle_id,etCph.getText().toString(),etXszh.getText().toString(), Utils.dateToStamp(tvChoseTime.getText().toString()),etZdzzzl.getText().toString(),
                    etZdzztj.getText().toString(),carTypeId,etCx.getText().toString(),etZzs.getText().toString(),
                    driverId,etGps.getText().toString(),etYs.getText().toString(),etRemarks.getText().toString(),rbZ.isChecked() ? "1" : "2"
                    ).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.body().getCode() == 1) {
                        ToastUtil.showToast("保存成功");
                        if (type == 0) {
                            AddCarActivity.actionStart(AddCarActivity.this,"ADD","NULL");
                        }
                        finish();
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
    ToastUtil.showToast("网络异常:提交失败");
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
