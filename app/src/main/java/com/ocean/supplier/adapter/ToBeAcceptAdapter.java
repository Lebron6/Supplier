package com.ocean.supplier.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ocean.supplier.R;
import com.ocean.supplier.entity.DeliveryList;
import com.ocean.supplier.tools.TimeUtils;
import com.ocean.supplier.tools.ToastUtil;
import com.ocean.supplier.tools.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToBeAcceptAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<DeliveryList.ListBean> datas;

    public ToBeAcceptAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<DeliveryList.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_to_be_accept, parent, false);
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
        final DeliveryList.ListBean bean = datas.get(position);
        viewHolder.tvCompanyName.setText(bean.getTlogistics_name());
        viewHolder.tvPrice.setText("¥" + Utils.stringToKeep2Point(bean.getPrice()));
        viewHolder.tvTime.setText(bean.getCreateTime());
        new CountDownTimer(bean.getDowntime()*1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("剩余时间",""+millisUntilFinished);
                Log.e("剩余服务器时间",""+millisUntilFinished);
                viewHolder.tvTimeCount.setText(TimeUtils.getHours(millisUntilFinished / 1000)+":"+TimeUtils.getMins(millisUntilFinished / 1000)+":"+TimeUtils.getSeconds(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                viewHolder.tvTimeCount.setText("已结束");
            }
        }.start();
        if (bean.getDowntime()==0){
            viewHolder.tvAccept.setText("已结束");
            viewHolder.tvAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showToast("已结束接单");
                }
            });
        }else{
            viewHolder.tvAccept.setText("接单");
            viewHolder.tvAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
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

        @BindView(R.id.tv_time_count)
        TextView tvTimeCount;
        @BindView(R.id.tv_company_name)
        TextView tvCompanyName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.txt_price)
        TextView txtPrice;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_accept)
        TextView tvAccept;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}