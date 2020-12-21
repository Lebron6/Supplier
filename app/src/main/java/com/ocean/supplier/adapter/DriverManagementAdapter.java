package com.ocean.supplier.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ocean.supplier.R;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.DriverList;
import com.ocean.supplier.tools.GlideCircleTransform;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverManagementAdapter extends RecyclerView.Adapter {



    private Context context;
    private DriverList datas;

    public DriverManagementAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(DriverList datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_driver_management, parent, false);
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
        final DriverList.ListBean bean = datas.getList().get(position);
        viewHolder.tvStaffName.setText(bean.getName());
        viewHolder.tvUpdataTime.setText(bean.getPhone());
        Glide.with(context).load(bean.getHeadimg()).bitmapTransform(new GlideCircleTransform(context)).into(viewHolder.imageUser);
        if (bean.getStatus().equals("1")){//正常
            viewHolder.tvSett.setBackgroundResource(R.drawable.tv_bg_half_pink);

            viewHolder.tvSett.setText("禁用");
            viewHolder.tvSett.setTextColor(context.getResources().getColor(R.color.colorOrange));
        }else{//禁用
            viewHolder.tvSett.setBackgroundResource(R.drawable.tv_bg_half_blue);
            viewHolder.tvSett.setText("启用");
            viewHolder.tvSett.setTextColor(context.getResources().getColor(R.color.colorWhite));

        }

        viewHolder.tvSett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean.getStatus().equals("1")){//状态正常，点击禁用
                    HttpUtil.createRequest("DriverManagementAdapter", BaseUrl.getInstence().changeDriverStatus()).changeStatus(PreferenceUtils.getInstance().getUserToken(),
                            bean.getS_driver_id(),"2").enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.body().getCode() == 1) {
                                ToastUtil.showToast("禁用成功");
                                bean.setStatus("2");
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
                    HttpUtil.createRequest("DriverManagementAdapter", BaseUrl.getInstence().changeDriverStatus()).changeStatus(PreferenceUtils.getInstance().getUserToken(),
                            bean.getS_driver_id(),"1").enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.body().getCode() == 1) {
                                ToastUtil.showToast("启用成功");
                                bean.setStatus("1");
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
        viewHolder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone(bean.getPhone());
            }
        });

    }
    /**
     * 拨打电话（直接拨打电话）
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return datas.getList().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_user)
        ImageView imageUser;
        @BindView(R.id.tv_staff_name)
        TextView tvStaffName;
        @BindView(R.id.tv_updata_time)
        TextView tvUpdataTime;
        @BindView(R.id.iv_call)
        ImageView ivCall;
        @BindView(R.id.tv_sett)
        TextView tvSett;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}