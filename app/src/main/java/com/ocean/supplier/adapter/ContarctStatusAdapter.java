package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.entity.ContractList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContarctStatusAdapter extends RecyclerView.Adapter {



    private Context context;
    List<ContractList.ListBean> datas;

    public ContarctStatusAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ContractList.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contract_status, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
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
        viewHolder.tvLsh.setText(datas.get(position).getConstract_sn());
        viewHolder.tvCyf.setText(datas.get(position).getTlogistics_name());
        viewHolder.tvYxq.setText(datas.get(position).getStartTime()+"-"+datas.get(position).getEndTime());

        switch (datas.get(position).getStatus()) {
            case "1"://完成
                viewHolder.tvStatus.setText("完成");
                break;
            case "2"://待确认
                viewHolder.tvStatus.setText("待确认");
                break;
            case "3"://进行中
                viewHolder.tvStatus.setText("进行中");
                break;
            case "4"://驳回
                viewHolder.tvStatus.setText("驳回");
                break;
        }
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
        @BindView(R.id.tv_lsh)
        TextView tvLsh;
        @BindView(R.id.iv_copy)
        ImageView ivCopy;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_cyf)
        TextView tvCyf;
        @BindView(R.id.tv_yxq)
        TextView tvYxq;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}