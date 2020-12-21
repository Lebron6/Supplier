package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.DriverList;
import com.ocean.supplier.entity.VehicleGetResult;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarManagementAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<VehicleGetResult.ListBean> datas;

    public CarManagementAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<VehicleGetResult.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car_management, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        final VehicleGetResult.ListBean bean = datas.get(position);
        if (bean.getSys_status().equals("1")){//正常
            viewHolder.tvSett.setBackgroundResource(R.drawable.tv_bg_half_pink);
            viewHolder.tvSett.setText("禁用");
            viewHolder.tvSett.setTextColor(context.getResources().getColor(R.color.colorOrange));
        }else{//禁用
            viewHolder.tvSett.setBackgroundResource(R.drawable.tv_bg_half_blue);
            viewHolder.tvSett.setText("启用");
            viewHolder.tvSett.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }
        viewHolder.tvCarNum.setText(bean.getNum());
        viewHolder.tvLarge.setText(bean.getMax_weight());
        viewHolder.tvCarType.setText(bean.getCar_info());
        viewHolder.tvDriver.setText(bean.getName());
        viewHolder.tvHd.setText(bean.getCar_type());

        viewHolder.tvSett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean.getSys_status().equals("1")){//状态正常，点击禁用
                    HttpUtil.createRequest("CarManagementAdapter", BaseUrl.getInstence().vehicleChangeStatus()).vehicleChangeStatus(PreferenceUtils.getInstance().getUserToken(),
                            bean.getVehicle_id(),"2").enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.body().getCode() == 1) {
                                ToastUtil.showToast("禁用成功");
                                bean.setSys_status("2");
                                notifyDataSetChanged();
                            } else {
                                ToastUtil.showToast(response.body().getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            ToastUtil.showToast("网络异常:操作失败");
                        }
                    });
                }else{
                    HttpUtil.createRequest("CarManagementAdapter", BaseUrl.getInstence().vehicleChangeStatus()).vehicleChangeStatus(PreferenceUtils.getInstance().getUserToken(),
                            bean.getVehicle_id(),"1").enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.body().getCode() == 1) {
                                ToastUtil.showToast("启用成功");
                                bean.setSys_status("1");
                                notifyDataSetChanged();
                            } else {
                                ToastUtil.showToast(response.body().getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            ToastUtil.showToast("网络异常:操作失败");
                        }
                    });
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_car_num)
        TextView tvCarNum;
        @BindView(R.id.tv_hd)
        TextView tvHd;
        @BindView(R.id.tv_sett)
        TextView tvSett;
        @BindView(R.id.tv_large)
        TextView tvLarge;
        @BindView(R.id.tv_car_type)
        TextView tvCarType;
        @BindView(R.id.tv_driver)
        TextView tvDriver;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}