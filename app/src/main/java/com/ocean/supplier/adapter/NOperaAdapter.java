package com.ocean.supplier.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ocean.supplier.R;
import com.ocean.supplier.activity.DispatchActivity;
import com.ocean.supplier.activity.OperaDetailsActivity;
import com.ocean.supplier.activity.OperationTrackActivity;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.callback.NotiImp;
import com.ocean.supplier.dialog.QrCodeDialog;
import com.ocean.supplier.dialog.RejectOperaRemarksDialog;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.NOperaListData;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NOperaAdapter extends RecyclerView.Adapter {


    private Context context;
    private int type;
    private List<NOperaListData.ListBean> listBeans;

    public NOperaAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    NotiImp imp;

    public void setNotiImp(NotiImp imp) {
        this.imp = imp;
    }

    public void setDatas(List<NOperaListData.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_n_opera, parent, false);
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
        viewHolder.tvJiaohdz.setText(listBeans.get(position).getDelivery_address());
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

        switch (listBeans.get(position).getS_status()) {//供应商状态 1=受理；2=驳回；3=收货；4=调度；5=途中 ；6=完成
            case "1":
                viewHolder.tvXq.setVisibility(View.VISIBLE);
                viewHolder.tvBh.setVisibility(View.VISIBLE);
                viewHolder.tvSl.setVisibility(View.VISIBLE);
                viewHolder.tvSjbh.setVisibility(View.GONE);
                viewHolder.tvCkewm.setVisibility(View.GONE);
                viewHolder.tvSh.setVisibility(View.GONE);
                viewHolder.tvCkgj.setVisibility(View.GONE);
                viewHolder.tvDd.setVisibility(View.GONE);
                viewHolder.tvGysbh.setVisibility(View.GONE);
                break;
            case "2":
                viewHolder.tvXq.setVisibility(View.VISIBLE);
                viewHolder.tvBh.setVisibility(View.GONE);
                viewHolder.tvSl.setVisibility(View.GONE);
                viewHolder.tvCkewm.setVisibility(View.GONE);
                viewHolder.tvSh.setVisibility(View.GONE);
                viewHolder.tvCkgj.setVisibility(View.GONE);
                viewHolder.tvDd.setVisibility(View.GONE);
                viewHolder.tvGysbh.setVisibility(View.VISIBLE);
                viewHolder.tvSjbh.setVisibility(View.GONE);
                break;
            case "3":
                viewHolder.tvXq.setVisibility(View.VISIBLE);
                viewHolder.tvBh.setVisibility(View.GONE);
                viewHolder.tvSl.setVisibility(View.GONE);
                viewHolder.tvCkewm.setVisibility(View.VISIBLE);
                if (listBeans.get(position).getShow_button().equals("1")) {
                    viewHolder.tvSh.setEnabled(false);
                    viewHolder.tvSh.setBackgroundResource(R.drawable.bg_gray_button);
                }
                viewHolder.tvSh.setVisibility(View.VISIBLE);
                viewHolder.tvCkgj.setVisibility(View.GONE);
                viewHolder.tvDd.setVisibility(View.GONE);
                viewHolder.tvGysbh.setVisibility(View.GONE);
                viewHolder.tvSjbh.setVisibility(View.GONE);
                break;
            case "4":
                viewHolder.tvXq.setVisibility(View.VISIBLE);
                viewHolder.tvBh.setVisibility(View.GONE);
                viewHolder.tvSl.setVisibility(View.GONE);

                viewHolder.tvSh.setVisibility(View.GONE);
                viewHolder.tvCkgj.setVisibility(View.GONE);
//                if (listBeans.get(position).getShow_button().equals("1")) {
//                    viewHolder.tvDd.setEnabled(false);
//                    viewHolder.tvDd.setBackgroundResource(R.drawable.bg_gray_button);
//                }
                viewHolder.tvDd.setVisibility(View.VISIBLE);
                viewHolder.tvGysbh.setVisibility(View.GONE);
                if (listBeans.get(position).getIs_d_reject() == 1) {
                    viewHolder.tvSjbh.setVisibility(View.VISIBLE);
                    viewHolder.tvCkewm.setVisibility(View.GONE);
                } else {
                    viewHolder.tvSjbh.setVisibility(View.GONE);
                    viewHolder.tvCkewm.setVisibility(View.VISIBLE);
                }
                break;
            case "5":
                viewHolder.tvXq.setVisibility(View.VISIBLE);
                viewHolder.tvBh.setVisibility(View.GONE);
                viewHolder.tvSl.setVisibility(View.GONE);
                viewHolder.tvCkewm.setVisibility(View.VISIBLE);
                viewHolder.tvSh.setVisibility(View.GONE);
                viewHolder.tvCkgj.setVisibility(View.VISIBLE);
                viewHolder.tvDd.setVisibility(View.GONE);
                viewHolder.tvGysbh.setVisibility(View.GONE);
                viewHolder.tvSjbh.setVisibility(View.GONE);
                break;
            case "6":
                viewHolder.tvXq.setVisibility(View.VISIBLE);
                viewHolder.tvBh.setVisibility(View.GONE);
                viewHolder.tvSl.setVisibility(View.GONE);
                viewHolder.tvCkewm.setVisibility(View.VISIBLE);
                viewHolder.tvSh.setVisibility(View.GONE);
                viewHolder.tvCkgj.setVisibility(View.GONE);
                viewHolder.tvDd.setVisibility(View.GONE);
                viewHolder.tvGysbh.setVisibility(View.GONE);
                viewHolder.tvSjbh.setVisibility(View.GONE);
                break;
        }

        viewHolder.tvSl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl(listBeans.get(position).getOs_id());
            }
        });

        viewHolder.tvBh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bh(listBeans.get(position).getOs_id());
            }
        });

        viewHolder.tvSh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sh(listBeans.get(position).getOs_id());
            }
        });

        viewHolder.tvDd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DispatchActivity.actionStart(context, listBeans.get(position).getOs_id());
            }
        });
        viewHolder.layoutXq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperaDetailsActivity.actionStart(context, listBeans.get(position).getOs_id());
            }
        });
        viewHolder.tvCkgj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperationTrackActivity.actionStart(context, listBeans.get(position).getOs_id());
            }
        });
        viewHolder.tvCkewm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> map=new HashMap<>();
                map.put("o_id",listBeans.get(position).getO_id());
                QrCodeDialog dialog=new QrCodeDialog(context,R.style.MyDialog,new Gson().toJson(map));
                dialog.show();
            }
        });
    }

    //收货
    private void sh(String os_id) {
        HttpUtil.createRequest(BaseUrl.getInstence().operationReceive()).operationReceive(PreferenceUtils.getInstance().getUserToken(), os_id).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("收货成功");
                    imp.noti();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:操作失败");
            }
        });
    }

    //驳回
    private void bh(String os_id) {
        RejectOperaRemarksDialog dialog = new RejectOperaRemarksDialog(context, R.style.Theme_AppCompat_Dialog, os_id);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                imp.noti();
            }
        });
    }

    //受理
    private void sl(String os_id) {
        HttpUtil.createRequest(BaseUrl.getInstence().operationAccept()).shipperConfirm(PreferenceUtils.getInstance().getUserToken(), os_id).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("受理成功");
                    imp.noti();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:操作失败");
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
        @BindView(R.id.tv_jiaohdz)
        TextView tvJiaohdz;
        @BindView(R.id.tv_yqddsj)
        TextView tvYqddsj;
        @BindView(R.id.tv_xq)
        TextView tvXq;
        @BindView(R.id.tv_ckewm)
        TextView tvCkewm;
        @BindView(R.id.tv_bh)
        TextView tvBh;
        @BindView(R.id.tv_sjbh)
        TextView tvSjbh;
        @BindView(R.id.tv_sl)
        TextView tvSl;
        @BindView(R.id.tv_gysbh)
        TextView tvGysbh;
        @BindView(R.id.layout_xq)
        RelativeLayout layoutXq;
        @BindView(R.id.tv_dd)
        TextView tvDd;
        @BindView(R.id.tv_sh)
        TextView tvSh;
        @BindView(R.id.tv_ckgj)
        TextView tvCkgj;
        @BindView(R.id.layout_vis)
        LinearLayout layoutVis;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}