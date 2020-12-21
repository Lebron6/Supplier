package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.entity.DeliveryList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompleteAdapter extends RecyclerView.Adapter {



    private Context context;
    private List<DeliveryList.ListBean> datas;

    public CompleteAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<DeliveryList.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complete, parent, false);
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
viewHolder.tvCarNum.setText(datas.get(position).getVehicle_num());
viewHolder.tvTime.setText(datas.get(position).getCreateTime());
viewHolder.tvCompanyName.setText(datas.get(position).getTlogistics_name());
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

        @BindView(R.id.tv_company_name)
        TextView tvCompanyName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.layout_center)
        LinearLayout layoutCenter;
        @BindView(R.id.tv_car_num)
        TextView tvCarNum;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}