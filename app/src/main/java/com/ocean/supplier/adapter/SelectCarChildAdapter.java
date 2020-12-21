package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.activity.MultiCarDistributionOfGoodsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCarChildAdapter extends RecyclerView.Adapter {



    private Context context;
//    private List<VehicleGetResult.ListBean> datas;

    public SelectCarChildAdapter(Context context) {
        this.context = context;
    }

//    public void setDatas() {
//        this.datas = datas;
//        notifyDataSetChanged();
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_car_child, parent, false);
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
viewHolder.layoutToNext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        MultiCarDistributionOfGoodsActivity.actionStart(context);
    }
});
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.iv_bg)
        ImageView ivBg;
        @BindView(R.id.tv_list_num)
        TextView tvListNum;
        @BindView(R.id.iv_right)
        ImageView ivRight;
        @BindView(R.id.layout_to_next)
        RelativeLayout layoutToNext;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}