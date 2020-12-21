package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.entity.AcceptDetailsData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToBeAcceptDetailsAdapter extends RecyclerView.Adapter {


    private Context context;
    private AcceptDetailsData datas;

    public ToBeAcceptDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(AcceptDetailsData datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tobe_complete_details, parent, false);
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

        viewHolder.tvBillNum.setText(datas.getList().get(position).getSerial_num());

        viewHolder.tvNameT.setText(datas.getList().get(position).getDelivery_name());
        viewHolder.tvPhoneT.setText(datas.getList().get(position).getDelivery_tel());
        viewHolder.tvAddrT.setText(datas.getList().get(position).getDelivery_address());

        viewHolder.tvNameS.setText(datas.getList().get(position).getUnload_name());
        viewHolder.tvPhoneS.setText(datas.getList().get(position).getUnload_tel());
        viewHolder.tvAddrS.setText(datas.getList().get(position).getUnload_address());

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


        @BindView(R.id.tv_bill_num)
        TextView tvBillNum;
        @BindView(R.id.layout_top)
        RelativeLayout layoutTop;
        @BindView(R.id.view_top)
        View viewTop;
        @BindView(R.id.txt_t)
        TextView txtT;
        @BindView(R.id.txt_s)
        TextView txtS;
        @BindView(R.id.layout_line)
        RelativeLayout layoutLine;
        @BindView(R.id.tv_name_t)
        TextView tvNameT;
        @BindView(R.id.tv_phone_t)
        TextView tvPhoneT;
        @BindView(R.id.tv_addr_t)
        TextView tvAddrT;

        @BindView(R.id.layout_t_info)
        LinearLayout layoutTInfo;
        @BindView(R.id.tv_name_s)
        TextView tvNameS;
        @BindView(R.id.tv_phone_s)
        TextView tvPhoneS;
        @BindView(R.id.tv_addr_s)
        TextView tvAddrS;

        @BindView(R.id.layout_s_info)
        LinearLayout layoutSInfo;
        @BindView(R.id.layout_addr)
        RelativeLayout layoutAddr;
        @BindView(R.id.view_line)
        View viewLine;
        @BindView(R.id.layout_bottom)
        RelativeLayout layoutBottom;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}