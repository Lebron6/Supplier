package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ocean.supplier.R;
import com.ocean.supplier.entity.VehicleGetResult;
import com.ocean.supplier.tools.GlideCircleTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarEditAdapter extends RecyclerView.Adapter {



    private Context context;
    private List<VehicleGetResult.ListBean> datas;

    public CarEditAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<VehicleGetResult.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car_edit, parent, false);
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
        if (bean.getSelectStatus() == 0) {
            viewHolder.cbSelect.setChecked(false);
        } else {
            viewHolder.cbSelect.setChecked(true);
        }
        viewHolder.tvCarNum.setText(bean.getNum());
        viewHolder.tvLarge.setText(bean.getMax_weight());
        viewHolder.tvCarType.setText(bean.getCar_info());
        viewHolder.tvDriver.setText(bean.getName());
        viewHolder.tvHd.setText(bean.getCar_type());

        viewHolder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    bean.setSelectStatus(1);//本地设置已选择
                } else {
                    bean.setSelectStatus(0);//本地设置已选择
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
        @BindView(R.id.cb_select)
        CheckBox cbSelect;
        @BindView(R.id.tv_car_num)
        TextView tvCarNum;
        @BindView(R.id.tv_hd)
        TextView tvHd;
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