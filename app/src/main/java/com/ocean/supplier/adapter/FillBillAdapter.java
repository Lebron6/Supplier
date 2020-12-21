package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.supplier.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FillBillAdapter extends RecyclerView.Adapter {



    private Context context;

    public FillBillAdapter(Context context) {
        this.context = context;
    }

//    public void setDatas(HangData.OrdersBean ordersBean) {
//        this.datas = ordersBean;
//        notifyDataSetChanged();
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fill_bill, parent, false);
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
        viewHolder.ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.layoutC.setVisibility(viewHolder.layoutC.getVisibility()==View.GONE?View.VISIBLE:View.GONE);
                viewHolder.layoutT.setVisibility(viewHolder.layoutT.getVisibility()==View.GONE?View.VISIBLE:View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private OnItemClickLitener mOnItemClickLitener;

    @OnClick(R.id.iv_show)
    public void onViewClicked() {
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_select_bill)
        CheckBox cbSelectBill;
        @BindView(R.id.txt_goods_num)
        TextView txtGoodsNum;
        @BindView(R.id.tv_goods_num)
        TextView tvGoodsNum;
        @BindView(R.id.iv_show)
        ImageView ivShow;
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.package_name)
        TextView packageName;
        @BindView(R.id.tv_weight)
        TextView tvWeight;
        @BindView(R.id.layout_t)
        LinearLayout layoutT;
        @BindView(R.id.tv_admissions)
        TextView tvAdmissions;
        @BindView(R.id.tv_volume)
        TextView tvVolume;
        @BindView(R.id.layout_c)
        LinearLayout layoutC;
        @BindView(R.id.et_goods_num)
        EditText etGoodsNum;
        @BindView(R.id.tv_type_num)
        EditText tvTypeNum;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}