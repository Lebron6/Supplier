package com.ocean.supplier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.supplier.R;
import com.ocean.supplier.entity.QuotationData;
import com.ocean.supplier.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuotationAdapter extends RecyclerView.Adapter {



    private Context context;
    private QuotationData datas;

    public QuotationAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(QuotationData datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quotation, parent, false);
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
        QuotationResAdapter adapter=new QuotationResAdapter(context);
        adapter.setDatas(datas.getList().get(position));
        RecyclerViewHelper.initRecyclerViewH(context,viewHolder.rvH,false,adapter);
    }

    @Override
    public int getItemCount() {
        if (datas != null && datas.getList() != null) {
            return datas.getList().size();
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
        @BindView(R.id.rv_h)
        RecyclerView rvH;
        @BindView(R.id.layout_title_type)
        LinearLayout layoutTitleType;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}