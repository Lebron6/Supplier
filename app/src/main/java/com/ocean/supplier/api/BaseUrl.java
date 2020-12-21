package com.ocean.supplier.api;

/**
 * Created by Administrator on 2018/1/4.
 */

public class BaseUrl {

    private static BaseUrl baseUrl;

    public static BaseUrl getInstence() {
        if (baseUrl == null) {
            return new BaseUrl();
        }
        return baseUrl;
    }

        public String ipAddress  = "http://sp.oceanscm.com/";   //测试服务器
//    public String ipAddress = "http://sp.idalc.com/";   //线上服务器

    /**
     * 密码登录
     */
    public String passwordLogin() {
        return ipAddress + "/member/login/login_password/";
    }

    /**
     * 发送验证码
     */
    public String sendSMS() {
        return ipAddress + "/member/login/send_sms/";
    }

    /**
     * 忘记密码
     */
    public String passwordForget() {
        return ipAddress + "/member/login/password_forget/";
    }


    /**
     * 注册
     */
    public String loginRegister() {
        return ipAddress + "/member/login/register/";
    }

    /**
     * 获取司机列表
     */
    public String getDriverList() {
        return ipAddress + "/member/driver/get_list/";
    }

    /**
     * 启用、禁用、删除司机（支持批量）
     */
    public String changeDriverStatus() {
        return ipAddress + "/member/driver/change_status/";
    }

    /**
     * 编辑司机-获取信息
     */
    public String driverGetInfo() {
        return ipAddress + "/member/driver/get_info/";
    }

    /**
     * 新增/编辑保存
     */
    public String driverSave() {
        return ipAddress + "/member/driver/save/";
    }

    /**
     * 车辆列表
     */
    public String vehicleGetList() {
        return ipAddress + "/member/vehicle/get_list/";
    }

    /**
     * 启用、禁用、删除车辆（支持批量）
     */
    public String vehicleChangeStatus() {
        return ipAddress + "/member/vehicle/change_status/";
    }

    /**
     * 新增/编辑车辆-获取车辆类型
     */
    public String vehicleGetCarType() {
        return ipAddress + "/member/vehicle/get_car_type/";
    }

    /**
     * 新增/编辑保存
     */
    public String vehicleSave() {
        return ipAddress + "/member/vehicle/save/";
    }

    /**
     * 新增/编辑保存
     */
    public String vehicleGetInfo() {
        return ipAddress + "/member/vehicle/get_info/";
    }

    /**
     * 新增司机-搜索司机
     */
    public String driverAddSearch() {
        return ipAddress + "/member/driver/add_search/";
    }

    /**
     * 合同列表-----待调试
     */
    public String constractList() {
        return ipAddress + "/transport/constract/list/";
    }

    /**
     * 合同详情
     */
    public String constractInfo() {
        return ipAddress + "/transport/constract/info/";
    }

    /**
     * 驳回合同
     */
    public String constractChangeStatus() {
        return ipAddress + "/transport/constract/change_status/";
    }

    /**
     * 查看报价单
     */
    public String quotationInfo() {
        return ipAddress + "/transport/constract/quotation_info/";
    }

    /**
     * 提单受理列表
     */
    public String deliveryBillList() {
        return ipAddress + "/transport/deliveryBill/list/";
    }

    /**
     * 公司信息
     */
    public String companyInfo() {
        return ipAddress + "/center/company/info/";
    }

    /**
     * 货物清单
     */
    public String deliveryBillGoodsList() {
        return ipAddress + "/transport/deliveryBill/goods_list/";
    }

    /**
     * 提单详情
     */
    public String deliveryBillInfo() {
        return ipAddress + "/transport/deliveryBill/info/";
    }

    /**
     * 接单
     */
    public String acceptDelivery() {
        return ipAddress + "/transport/deliveryBill/accept_delivery/";
    }

    /**
     * 车辆列表
     */
    public String getVehicleList() {
        return ipAddress + "/transport/deliveryBill/get_vehicle_list/";
    }

    /**
     * 选择车辆
     */
    public String chooseVehicle() {
        return ipAddress + "/transport/deliveryBill/choose_vehicle/";
    }

    /**
     * 员工列表
     */
    public String staffList() {
        return ipAddress + "/center/worker/list/";
    }

    /**
     * 员工删除
     */
    public String staffDelete() {
        return ipAddress + "/center/worker/delete/";
    }

    /**
     * 员工添加
     */
    public String staffAdd() {
        return ipAddress + "/center/worker/save/";
    }

    /**
     * 员工添加初始化
     */
    public String initUser() {
        return ipAddress + "/center/worker/auth_list/";
    }

    /**
     * 个人信息
     */
    public String getInfo() {
        return ipAddress + "/center/setting/index/";
    }

    /**
     * 设置列表
     */
    public String settingInfo() {
        return ipAddress + "/center/setting/info/";
    }

    /**
     * 上传图片
     */
    public String uploadfile() {
        return ipAddress + "/member/uploads/uploadfile/";
    }

    /**
     * 输入新邮箱发送验证码
     */
    public String sendEmail() {
        return ipAddress + "/center/setting/send_email/";
    }

    /**
     * 绑定邮箱
     */
    public String bindEmail() {
        return ipAddress + "/center/setting/change_email/";
    }

    /**
     * 验证原密码
     */
    public String confirmPassword() {
        return ipAddress + "/center/setting/confirm_password/";
    }

    /**
     * 保存密码
     */
    public String changePassword() {
        return ipAddress + "/center/setting/change_password/";
    }

    /**
     * 输入新手机号码发送验证码
     */
    public String sendSms() {
        return ipAddress + "/center/setting/send_sms/";
    }

    /**
     * 修改手机号
     */
    public String savePhone() {
        return ipAddress + "/center/setting/change_phone/";
    }

    /**
     * 公司编辑
     */
    public String companyEdit() {
        return ipAddress + "/center/company/edit/";
    }

    /**
     * 公司编辑
     */
    public String settingEdit() {
        return ipAddress + "/center/setting/edit/";
    }

    /**
     * 可供调度的3pl列表
     */
    public String tplList() {
        return ipAddress + "/member/vehicle/tpl_list/";
    }

    /**
     * 设置可调度的3pl
     */
    public String setVehicleTpl() {
        return ipAddress + "/member/vehicle/set_vehicle_tpl/";
    }

    /**
     * 待供应商处理操作单列表
     */
    public String transportOperationList() {
        return ipAddress + "/transport/operation/list/";
    }

    /**
     * 已完成操作单列表
     */
    public String transportOperationCompleteList() {
        return ipAddress + "/transport/operation/complete_list/";
    }

    /**
     * 操作单详情
     */
    public String transportOperationInfo() {
        return ipAddress + "/transport/operation/info/";
    }

    /**
     * 货物明细清单
     */
    public String operationGoodsList() {
        return ipAddress + "/transport/operation/goods_list/";
    }

    /**
     * 受理
     */
    public String operationAccept() {
        return ipAddress + "/transport/operation/accept/";
    }

    /**
     * 驳回
     */
    public String operationReject() {
        return ipAddress + "/transport/operation/reject/";
    }

    /**
     * 收货
     */
    public String operationReceive() {
        return ipAddress + "/transport/operation/receive/";
    }

    /**
     * 调度选择司机/获取列表
     */
    public String operationDriverList() {
        return ipAddress + "/transport/operation/driver_list_page/";
    }

    /**
     * 司机信息
     */
    public String driverInfo() {
        return ipAddress + "/transport/operation/driver_info/";
    }

    /**
     * 调度选择车辆/获取列表
     */
    public String operationVehicleList() {
        return ipAddress + "/transport/operation/vehicle_list/";
    }

    /**
     * 调度选择地址/获取列表
     */
    public String supplierDeliveryAddress() {
        return ipAddress + "/transport/operation/supplier_delivery_address/";
    }

    /**
     * 调度
     */
    public String operationScheduling() {
        return ipAddress + "/transport/operation/scheduling/";
    }

    /**
     * 轨迹查询
     */
    public String operationTrack() {
        return ipAddress + "/transport/operation/track/";
    }
}
