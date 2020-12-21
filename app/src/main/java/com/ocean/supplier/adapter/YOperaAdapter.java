package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.activity.OperaDetailsActivity;
import com.ocean.supplier.dialog.QrCodeDialog;
import com.ocean.supplier.entity.YOperaListData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YOperaAdapter extends RecyclerView.Adapter {



    private Context context;
    private int type;
    private List<YOperaListData.ListBean> listBeans;

    public YOperaAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public void setDatas(List<YOperaListData.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_y_opera, parent, false);
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
        viewHolder.tvHwxx.setText(listBeans.get(position).getGoods_jnum() + "件," + listBeans.get(position).getTotal_weight() + "kg," + listBeans.get(position).getTotal_volume() + "m³");
        viewHolder.tvBzlx.setText(listBeans.get(position).getPk_name());
        viewHolder.tvJhdz.setText(listBeans.get(position).getPickup_address());
        viewHolder.tvJiaohuodz.setText(listBeans.get(position).getDelivery_address());
        viewHolder.tvAddrS.setText(listBeans.get(position).getStart_city());
        viewHolder.tvAddrE.setText(listBeans.get(position).getEnd_city());
        viewHolder.tvYqddsj.setText(listBeans.get(position).getArrival_time());
        viewHolder.tvStatus.setText(listBeans.get(position).getS_status());
        if (!TextUtils.isEmpty(listBeans.get(position).getS_type())) {
            switch (listBeans.get(position).getS_type()) {
                //供应商类型 1 提货 2 干线 3 派送 4 交货 5 其他
                case "1":
                    viewHolder.tvStatus.setText("提货");
                    break;
                case "2":
                    viewHolder.tvStatus.setText("干线");
                    break;
                case "3":
                    viewHolder.tvStatus.setText("派送");
                    break;
                case "4":
                    viewHolder.tvStatus.setText("交货");
                    break;
                case "5":
                    viewHolder.tvStatus.setText("其它");
                    break;

            }
        }
        viewHolder.signFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> map=new HashMap<>();
                map.put("o_id",listBeans.get(position).getO_id());
                Log.e("map.toString",map.toString());
                QrCodeDialog dialog=new QrCodeDialog(context,R.style.MyDialog,map.toString());
                dialog.show();
            }
        });
        viewHolder.tvDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperaDetailsActivity.actionStart(context,listBeans.get(position).getOs_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_addr_s)
        TextView tvAddrS;
        @BindView(R.id.iv_grass)
        ImageView ivGrass;
        @BindView(R.id.tv_addr_e)
        TextView tvAddrE;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_hwxx)
        TextView tvHwxx;
        @BindView(R.id.tv_bzlx)
        TextView tvBzlx;
        @BindView(R.id.tv_jhdz)
        TextView tvJhdz;
        @BindView(R.id.tv_jiaohuodz)
        TextView tvJiaohuodz;
        @BindView(R.id.tv_shdz)
        TextView tvShdz;
        @BindView(R.id.tv_yqddsj)
        TextView tvYqddsj;
        @BindView(R.id.tv_Details)
        TextView tvDetails;
        @BindView(R.id.sign_for)
        TextView signFor;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}