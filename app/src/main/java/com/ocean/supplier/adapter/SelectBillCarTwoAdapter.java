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
import com.ocean.supplier.activity.MultiCarDistributionOfGoodsActivity;
import com.ocean.supplier.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectBillCarTwoAdapter extends RecyclerView.Adapter {



    private Context context;
//    private List<VehicleGetResult.ListBean> datas;

    public SelectBillCarTwoAdapter(Context context) {
        this.context = context;
    }

//    public void setDatas() {
//        this.datas = datas;
//        notifyDataSetChanged();
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_bill_car_two, parent, false);
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
        SelectCarChildAdapter adapter=new SelectCarChildAdapter(context);
        RecyclerViewHelper.initRecyclerViewV(context,viewHolder.rvList,adapter);
//        adapter.setOnItemClickLitener(new SelectCarChildAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
//            }
//        });

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
        @BindView(R.id.tv_car_num)
        TextView tvCarNum;
        @BindView(R.id.tv_driver)
        TextView tvDriver;
        @BindView(R.id.layout_top)
        LinearLayout layoutTop;
        @BindView(R.id.view_top)
        View viewTop;
        @BindView(R.id.rv_list)
        RecyclerView rvList;
        @BindView(R.id.layout_bottom)
        RelativeLayout layoutBottom;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}