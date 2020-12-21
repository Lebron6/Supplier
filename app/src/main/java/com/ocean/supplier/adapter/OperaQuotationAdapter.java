package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.entity.OperaGoodsListData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OperaQuotationAdapter extends RecyclerView.Adapter {


    private Context context;
    private OperaGoodsListData datas;

    public OperaQuotationAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(OperaGoodsListData datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_opera_quotation_res, parent, false);
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
        viewHolder.tvOne.setText(datas.getGood_list().get(position).getWa_num());
        viewHolder.tvTwo.setText(datas.getGood_list().get(position).getName());
        viewHolder.tvThree.setText(datas.getGood_list().get(position).getGood_type());
        viewHolder.tvFour.setText(datas.getGood_list().get(position).getPk_name());
        viewHolder.tvFive.setText(datas.getGood_list().get(position).getAccept_num());
        viewHolder.tvSix.setText(datas.getGood_list().get(position).getWeight());
        viewHolder.tvSeven.setText(datas.getGood_list().get(position).getVolume());
        viewHolder.tvEight.setText(datas.getGood_list().get(position).getJnum());
        viewHolder.tvNine.setText(datas.getGood_list().get(position).getNum());

    }

    @Override
    public int getItemCount() {
        if (datas != null && datas.getGood_list() != null) {
            return datas.getGood_list().size();
        } else {
            return 0;
        }

    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_one)
        TextView tvOne;
        @BindView(R.id.tv_two)
        TextView tvTwo;
        @BindView(R.id.tv_three)
        TextView tvThree;
        @BindView(R.id.tv_four)
        TextView tvFour;
        @BindView(R.id.tv_five)
        TextView tvFive;
        @BindView(R.id.tv_six)
        TextView tvSix;
        @BindView(R.id.tv_seven)
        TextView tvSeven;
        @BindView(R.id.tv_eight)
        TextView tvEight;
        @BindView(R.id.tv_nine)
        TextView tvNine;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}