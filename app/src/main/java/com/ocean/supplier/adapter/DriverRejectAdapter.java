package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.entity.OperaDetails;
import com.ocean.supplier.entity.VehicleGetResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DriverRejectAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<OperaDetails.DRejectListBean> datas;

    public DriverRejectAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<OperaDetails.DRejectListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_driver_reject, parent, false);
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
        viewHolder.tvRejectTitle.setText("操作单被司机"+datas.get(position).getName()+"驳回了");
        viewHolder.tvRejectTime.setText(datas.get(position).getReject_time());
        viewHolder.tcRejectContent.setText(datas.get(position).getReject());
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

        @BindView(R.id.tv_reject_time)
        TextView tvRejectTime;
        @BindView(R.id.tc_reject_content)
        TextView tcRejectContent;
        @BindView(R.id.layout_t)
        LinearLayout layoutT;
        @BindView(R.id.tv_reject_title)
        TextView tvRejectTitle;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}