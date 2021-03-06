package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ocean.supplier.R;
import com.ocean.supplier.entity.DriverList;
import com.ocean.supplier.tools.GlideCircleTransform;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DriverEditAdapter extends RecyclerView.Adapter {



    private Context context;
    private DriverList datas;

    public DriverEditAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(DriverList datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_driver_edit, parent, false);
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
        final DriverList.ListBean bean = datas.getList().get(position);

        viewHolder.tvStaffName.setText(bean.getName());
        viewHolder.tvUpdataTime.setText(bean.getPhone());
        Glide.with(context).load(bean.getHeadimg()).bitmapTransform(new GlideCircleTransform(context)).into(viewHolder.imageUser);
        if (bean.getSelectStatus()==0){
            viewHolder.cbSelect.setChecked(false);
        }else{
            viewHolder.cbSelect.setChecked(true);
        }
viewHolder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b==true){
            bean.setSelectStatus(1);//本地设置已选择
        }else{
            bean.setSelectStatus(0);//本地设置已选择
        }
    }
});
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
        @BindView(R.id.image_user)
        ImageView imageUser;
        @BindView(R.id.tv_staff_name)
        TextView tvStaffName;
        @BindView(R.id.tv_updata_time)
        TextView tvUpdataTime;

        @BindView(R.id.cb_select)
        CheckBox cbSelect;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}