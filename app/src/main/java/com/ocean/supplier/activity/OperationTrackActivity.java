package com.ocean.supplier.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolylineOptions;
import com.ocean.supplier.R;
import com.ocean.supplier.adapter.TrackAdapter;
import com.ocean.supplier.api.BaseUrl;
import com.ocean.supplier.api.HttpUtil;
import com.ocean.supplier.entity.ApiResponse;
import com.ocean.supplier.entity.Track;
import com.ocean.supplier.tools.PreferenceUtils;
import com.ocean.supplier.tools.RecyclerViewHelper;
import com.ocean.supplier.tools.TitleManger;
import com.ocean.supplier.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/11/20.
 * 轨迹查询
 */
public class OperationTrackActivity extends BaseActivity {

    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.layout_show)
    LinearLayout layoutShow;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.layout_path)
    LinearLayout layoutPath;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState); // 此方法必须重写
        init();
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
//            setUpMap();//画线
        }
    }

    public static final String OS_ID = "os_id";

    public static void actionStart(Context context, String os_id) {
        Intent intent = new Intent(context, OperationTrackActivity.class);
        intent.putExtra(OS_ID, os_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("查看轨迹");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_operation_track;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        getTrackData();
    }

    private void getTrackData() {

        HttpUtil.createRequest(BaseUrl.getInstence().operationTrack()).operationTrack(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(OS_ID)).enqueue(new Callback<ApiResponse<Track>>() {
            @Override
            public void onResponse(Call<ApiResponse<Track>> call, Response<ApiResponse<Track>> response) {
                if (response.body().getCode() == 1) {
                    TrackAdapter adapter = new TrackAdapter(OperationTrackActivity.this);
                    adapter.setDatas(response.body().getData().getStatus_list());
                    RecyclerViewHelper.initRecyclerViewV(OperationTrackActivity.this, rvList, adapter);

                    for (int i = 0; i < response.body().getData().getTrack().size(); i++) {
                        List<LatLng> latLngs = new ArrayList<LatLng>();
                        for (int j = 0; j < response.body().getData().getTrack().get(i).size() ; j++) {
                            latLngs.add(new LatLng(Double.valueOf(response.body().getData().getTrack().get(i).get(j).getLat()),Double.valueOf(response.body().getData().getTrack().get(i).get(j).getLng())));
                        }
                    aMap.addPolyline(new PolylineOptions().
                                addAll(latLngs).width(10).color(Color.argb(255, 30,144,255)));
                    }

                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Track>> call, Throwable t) {
                ToastUtil.showToast("获取地址数据失败");
            }
        });

    }

    @OnClick(R.id.layout_show)
    public void onViewClicked() {
                rvList.setVisibility(rvList.getVisibility()==View.GONE?View.VISIBLE:View.GONE);
    }
}
