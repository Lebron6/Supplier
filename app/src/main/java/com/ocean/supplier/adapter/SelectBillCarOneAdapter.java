package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.entity.CarList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectBillCarOneAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<CarList.ListBean> datas;

    public SelectBillCarOneAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CarList.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_bill_car_one, parent, false);
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

        viewHolder.tvCarNum.setText(datas.get(position).getNum());
        viewHolder.tvDriver.setText(datas.get(position).getDriver_name());
        viewHolder.tvTj.setText(datas.get(position).getRemainVolume());
        viewHolder.tvZl.setText(datas.get(position).getRemainWeight());
        if (datas.get(position).getType()==0){
            viewHolder.cbSelect.setChecked(false);
        }else{
            viewHolder.cbSelect.setChecked(true);
        }
        viewHolder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true){
                    for (int i = 0; i < datas.size(); i++) {
                        datas.get(i).setType(0);
                    }
                    datas.get(position).setType(1);//本地设置已选择
                }else{
                    datas.get(position).setType(0);//本地设置已选择
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


        @BindView(R.id.tv_car_num)
        TextView tvCarNum;
        @BindView(R.id.tv_driver)
        TextView tvDriver;
        @BindView(R.id.tv_zl)
        TextView tvZl;
        @BindView(R.id.tv_tj)
        TextView tvTj;
        @BindView(R.id.cb_select)
        CheckBox cbSelect;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}