package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.entity.OperaDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OperaDetailsJAdapter extends RecyclerView.Adapter {


    private Context context;
    List<OperaDetails.PickupAddressBean> listBeans;

    public OperaDetailsJAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<OperaDetails.PickupAddressBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_opera_details_j, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        viewHolder.tvName.setText(listBeans.get(position).getName());
        viewHolder.tvInfo.setText(listBeans.get(position).getInfo());
        viewHolder.tvUserPhone.setText(listBeans.get(position).getContract_name()+","+listBeans.get(position).getContract_tel());
        viewHolder.tvTime.setText("要求到达时间"+listBeans.get(position).getArrive_time());
        viewHolder.tvTime.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_t)
        TextView txtT;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_info)
        TextView tvInfo;
        @BindView(R.id.tv_user_phone)
        TextView tvUserPhone;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}