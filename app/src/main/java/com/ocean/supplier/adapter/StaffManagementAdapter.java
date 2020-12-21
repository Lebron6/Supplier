package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ocean.supplier.R;
import com.ocean.supplier.entity.StaffList;
import com.ocean.supplier.tools.GlideCircleTransform;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StaffManagementAdapter extends RecyclerView.Adapter {


    private Context context;
    private StaffList datas;

    public StaffManagementAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(StaffList datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_staff_management, parent, false);
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
        if (TextUtils.isEmpty(datas.getList().get(position).getDepartment())) {
            viewHolder.tvStaffName.setText(datas.getList().get(position).getUsername());
        } else {
            viewHolder.tvStaffName.setText(datas.getList().get(position).getDepartment() + "-" + datas.getList().get(position).getUsername());
        }
        viewHolder.tvUpdataTime.setText("更新时间：" + datas.getList().get(position).getCreatetime());
        Glide.with(context).load(datas.getList().get(position).getHeadimg()).bitmapTransform(new GlideCircleTransform(context)).into(viewHolder.imageUser);
        if (datas.getList().get(position).getSelectStatus() == 0) {
            viewHolder.cbSelect.setChecked(false);
        } else {
            viewHolder.cbSelect.setChecked(true);
        }
        viewHolder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    datas.getList().get(position).setSelectStatus(1);//本地设置已选择
                } else {
                    datas.getList().get(position).setSelectStatus(0);//本地设置已选择
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
        @BindView(R.id.cb_select)
        CheckBox cbSelect;
        @BindView(R.id.image_user)
        ImageView imageUser;
        @BindView(R.id.tv_staff_name)
        TextView tvStaffName;
        @BindView(R.id.tv_updata_time)
        TextView tvUpdataTime;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}