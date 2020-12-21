package com.ocean.supplier.api;


import com.ocean.supplier.entity.AcceptDetailsData;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.CarList;
import com.ocean.supplier.entity.CompanyInfo;
import com.ocean.supplier.entity.ContractDetails;
import com.ocean.supplier.entity.ContractList;
import com.ocean.supplier.entity.DeliveryList;
import com.ocean.supplier.entity.DispactchList;
import com.ocean.supplier.entity.DispatchAddress;
import com.ocean.supplier.entity.DispatchCarList;
import com.ocean.supplier.entity.DispatchDriverInfo;
import com.ocean.supplier.entity.DriverAddSearch;
import com.ocean.supplier.entity.DriverInfo;
import com.ocean.supplier.entity.DriverList;
import com.ocean.supplier.entity.GetCarType;
import com.ocean.supplier.entity.LoginResult;
import com.ocean.supplier.entity.NOperaListData;
import com.ocean.supplier.entity.OperaDetails;
import com.ocean.supplier.entity.OperaGoodsListData;
import com.ocean.supplier.entity.QuotationData;
import com.ocean.supplier.entity.RegisterResult;
import com.ocean.supplier.entity.SettingInfo;
import com.ocean.supplier.entity.SettingResult;
import com.ocean.supplier.entity.StaffAddInit;
import com.ocean.supplier.entity.StaffList;
import com.ocean.supplier.entity.TPLList;
import com.ocean.supplier.entity.Track;
import com.ocean.supplier.entity.UpLoadResult;
import com.ocean.supplier.entity.VehicleGetInfo;
import com.ocean.supplier.entity.VehicleGetResult;
import com.ocean.supplier.entity.YOperaListData;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by James on 2018/1/4.
 */

public interface SupplierApi {

    String Content_Type = "translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=";

    /**
     * 密码登录
     * @param phone
     * @param password
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<LoginResult>> userLogin(@Field("company_no") String company_no, @Field("phone") String phone, @Field("password") String password);

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> sendSMS(@Field("phone") String phone);

    /**
     * 忘记密码
     *
     * @param phone
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> passwordForget(@Field("company_no") String company_no, @Field("phone") String phone, @Field("code") String code, @Field("password") String password);

    /**
     * 注册
     *
     * @return
     */
    @POST(Content_Type)
    @Multipart
    Call<ApiResponse<RegisterResult>> register(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part part);

    /**
     * 注册
     *
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<RegisterResult>> register(@Field("company_name") String company_name,
                                               @Field("phone") String phone,
                                               @Field("code") String code,
                                               @Field("password") String password);

    /**
     * 司机列表
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<DriverList>> driverList(@Header("token") String token);

    /**
     * 启用、禁用、删除司机（支持批量）
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> changeStatus(@Header("token") String token, @Field("s_driver_ids") String tl_driver_ids, @Field("type") String type
    );

    /**
     * 编辑司机-获取信息
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<DriverInfo>> driverGetInfo(@Header("token") String token, @Field("s_driver_id") String tl_driver_ids
    );

    /**
     * 新增/编辑保存
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> driverSave(@Header("token") String token, @Field("driver_id") String driver_id
            , @Field("remarks") String remarks, @Field("status") String status);


    /**
     * 车辆列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<VehicleGetResult>> vehicleGetList(@Header("token") String token, @Field("page") String page, @Field("str") String str
    );

    /**
     * 启用、禁用、删除车辆（支持批量）
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> vehicleChangeStatus(@Header("token") String token, @Field("vehicle_ids") String vehicle_ids, @Field("type") String type
    );


    /**
     * 新增/编辑车辆-获取车辆类型
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<GetCarType> vehicleGetCarType(@Header("token") String token);

    /**
     * 新增/编辑保存
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> vehicleSave(@Header("token") String token, @Field("vehicle_id") String vehicle_id, @Field("num") String num
            , @Field("run_num") String run_num, @Field("f_time") String f_time, @Field("max_weight") String max_weight,
                                  @Field("max_volume") String max_volume, @Field("car_type") String car_type
            , @Field("car_info") String car_info, @Field("make_car") String make_car, @Field("driver_id") String driver_id,
                                  @Field("gps") String gps, @Field("colour_car") String colour_car
            , @Field("remarks") String remarks, @Field("sys_status") String sys_status
    );

    /**
     * 编辑车辆-获取信息
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<VehicleGetInfo>> vehicleGetInfo(@Header("token") String token, @Field("vehicle_id") String vehicle_id
    );

    /**
     * 新增司机-搜索司机
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<DriverAddSearch>> driverAddSearch(@Header("token") String token, @Field("phone") String phone
    );

    /**
     * 合同列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<ContractList>> contractList(@Header("token") String token, @Field("status") String status, @Field("page") String page
    );


    /**
     * 合同详情
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<ContractDetails>> contractDetails(@Header("token") String token, @Field("co_id") String coid
    );

    /**
     * 驳回/通过合同
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> contractReject(@Header("token") String token, @Field("co_id") String coid, @Field("reject_remarks") String reject_remarks, @Field("type") String type
    );

    /**
     * 查看报价单
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<QuotationData>> contractReject(@Header("token") String token, @Field("q_id") String q_id
    );

    /**
     * 提单受理列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<DeliveryList>> deliveryBillList(@Header("token") String token, @Field("is_accept") String is_accept, @Field("page") String page
    );

    /**
     * 员工列表
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<StaffList>> staffList(@Header("token") String token
    );


    /**
     * 删除员工
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> deleteStaff(@Header("token") String token, @Field("sw_ids") String ids
    );

    /**
     * 员工添加
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> staffAdd(@Header("token") String token, @Field("username") String username
            , @Field("phone") String phone, @Field("email") String email, @Field("password") String password, @Field("sex") String sex
            , @Field("department") String department, @Field("position") String position, @Field("remarks") String remarks, @Field("auth") String auth
    );

    /**
     * 员工添加初始化
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<StaffAddInit> staffAddInit(@Header("token") String token
    );

    /**
     * 设置页面信息获取（包含个人资料）
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<SettingResult>> settinInfo(@Header("token") String token);

    /**
     * 修改头像
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @Multipart
    Call<ApiResponse<UpLoadResult>> changeImage(@Header("token") String token, @Header("type") String type, @Part MultipartBody.Part part);

    /**
     * 设置信息
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<SettingInfo>> settingInfo(@Header("token") String token);

    /**
     * 发送邮箱验证码
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> sendEmailCode(@Header("token") String token, @Field("email") String email
    );

    /**
     * 修改邮箱
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> saveEmail(@Header("token") String token, @Field("email") String email, @Field("code") String code
    );

    /**
     * 原密码验证
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> confirmPassword(@Header("token") String token, @Field("password") String password
    );

    /**
     * 新密码修改
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> changePassword(@Header("token") String token, @Field("password") String password
    );

    /**
     * 发送验证码到新手机号
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> sendSmsNew(@Header("token") String token, @Field("phone") String phone
    );

    /**
     * 新手机号修改
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> changePhone(@Header("token") String token, @Field("phone") String phone, @Field("code") String code
    );

    /**
     * 企业信息查看
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<CompanyInfo>> getCompanyInfo(@Header("token") String token
    );

    /**
     * 上传营业执照
     *
     * @return
     */
    @POST(Content_Type)
    @Multipart
    Call<ApiResponse<UpLoadResult>> upLoadFile(@Header("token") String token, @Header("type") String type, @Part MultipartBody.Part part);


    /**
     * 企业信息保存
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> saveCompany(@Header("token") String token, @Field("company_name") String company_name, @Field("license") String license,
                                  @Field("tel") String tel, @Field("address") String address,
                                  @Field("account_bank") String account_bank, @Field("bank_card") String bank_card,
                                  @Field("tax_num") String tax_num
    );

    /**
     * 个人资料保存
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> infoSave(@Header("token") String token, @Field("username") String username,
                               @Field("sex") String sex, @Field("department") String department,
                               @Field("position") String position);

    /**
     * 绑定头像
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> bindUserIcon(@Header("token") String token, @Field("headimg") String headimg);

    /**
     * 可供调度的3pl列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<TPLList> tplList(@Header("token") String token, @Field("page") String page, @Field("str") String str
    );

    /**
     * 设置可调度的3pl
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> set_vehicle_tpl(@Header("token") String token, @Field("vehicle_ids") String vehicle_ids, @Field("t_ids") String t_ids
    );

    /**
     * 运输管理---提单管理--提单详情
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<AcceptDetailsData>> deliveryBillInfo(@Header("token") String token, @Field("sdl_id") String sdl_id, @Field("sdlv_id") String sdlv_id
    );


    /**
     * 获取车辆列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<CarList>> getVehicleList(@Header("token") String token, @Field("page") String page
    );

    /**
     * 接单
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> acceptDelivery(@Header("token") String token, @Field("sdl_id") String sdl_id, @Field("v_id") String v_id, @Field("sdlv_id") String sdlv_id
    );

    /**
     * 待供应商处理操作单列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<NOperaListData>> transportOperationList(@Header("token") String token, @Field("page") String page, @Field("type") String type, @Field("status") String status
    );

    /**
     * 已完成操作单列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<YOperaListData>> transportOperationCompleteList(@Header("token") String token, @Field("page") String page, @Field("type") String type
    );

    /**
     * 操作单详情
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<OperaDetails>> operationListingInfo(@Header("token") String token, @Field("os_id") String os_id
    );

    /**
     * 操作单-货物清单
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<OperaGoodsListData>> operationListingGoodsList(@Header("token") String token, @Field("o_id") String o_id
    );

    /**
     * 受理运单
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> shipperConfirm(@Header("token") String token, @Field("os_id") String os_id
    );

    /**
     * 驳回操作单
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> operaReject(@Header("token") String token, @Field("os_id") String os_id, @Field("reject") String reject
    );

    /**
     * 收货
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> operationReceive(@Header("token") String token, @Field("os_id") String os_id
    );

    /**
     * 调度获取司机列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<DispactchList>> operationDriverList(@Header("token") String token, @Field("page") String page
    );

    /**
     * 司机信息
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<DispatchDriverInfo> driverInfo(@Header("token") String token, @Field("d_id") String d_id, @Field("os_id") String os_id
    );

    /**
     * 调度获取车辆列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<DispatchCarList>> operationVehicleList(@Header("token") String token, @Field("page") String page
    );

    /**
     * 调度获取地址
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<DispatchAddress> supplierDeliveryAddress(@Header("token") String token, @Field("os_id") String os_id
    );

    /**
     * 调度获取地址
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> operationScheduling(@Header("token") String token, @Field("os_id") String os_id,
                                          @Field("d_id") String d_id, @Field("d_mobile") String d_mobile,
                                          @Field("v_id") String v_id, @Field("goods_jnum") String goods_jnum,
                                          @Field("goods_num") String goods_num, @Field("delivery_address") String delivery_address
    );

    /**
     * 轨迹查询
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<Track>> operationTrack(@Header("token") String token, @Field("os_id") String os_id
    );


}