package com.ocean.supplier.dialog;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.ocean.supplier.R;
import com.ocean.supplier.callback.OnTypeSelectImp;

import java.util.List;

/**
 * Created by James on 2018/1/16.
 * 时间选择
 */
public class TypeSelectWindow {

    private ListView lv_quest;
    private List<String> questionList;
    private SimpleAdapter adapter;
    private PopupWindow popupWindow;
    private Activity context;
    private boolean bottom=true;

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public TypeSelectWindow(Activity context) {
        this.context=context;
    }

    /**
     * 问题验证popupWindow显示
     *
     * @param v
     * @param adapter
     */
    public  void showView(final View v, ArrayAdapter adapter, final OnTypeSelectImp listener) {
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.window_type, null);
        lv_quest = (ListView) contentView.findViewById(R.id.list_ques);
        lv_quest.setAdapter(adapter);
        lv_quest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.select(position);
                popupWindow.dismiss();
            }
        });
        contentView.measure(0, 0);
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        // 使其聚集
        popupWindow.setFocusable(true);

        popupWindow.setWidth((int ) v.getWidth());
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("mengdd", "onTouch : ");
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.item_bg_bill));
        if (bottom==true){
            popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        }else{
            popupWindow.showAsDropDown(v);
        }


    }

}
