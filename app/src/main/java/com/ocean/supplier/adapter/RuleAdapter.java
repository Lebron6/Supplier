package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.entity.StaffAddInit;
import com.ocean.supplier.view.togglebutton.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RuleAdapter extends RecyclerView.Adapter {


    private Context context;
    private StaffAddInit datas;

    public RuleAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(StaffAddInit datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
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
        viewHolder.toggle.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on){
                    datas.getData().get(position).setSelectStatus(1);
                    setDatas(datas);
                }

            }
        });
        viewHolder.tvName.setText(datas.getData().get(position).getName());
        if (datas.getData().get(position).getSelectStatus()==1){
            viewHolder.toggle.setToggleOn();
        }else{
            viewHolder.toggle.setToggleOff();
        }
    }

    @Override
    public int getItemCount() {
        return datas.getData().size();
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

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.toggle)
        ToggleButton toggle;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}