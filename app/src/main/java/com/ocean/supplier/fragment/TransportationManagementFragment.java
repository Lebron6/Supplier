package com.ocean.supplier.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ocean.supplier.R;
import com.ocean.supplier.activity.AcceptanceOfBillLadingActivity;
import com.ocean.supplier.activity.CarManagementActivity;
import com.ocean.supplier.activity.ContractManagementAvtivity;
import com.ocean.supplier.activity.DriverManagementActivity;
import com.ocean.supplier.activity.OperationSheetManagementActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by James on 2020/6/29.
 * 运输管理
 */
public class TransportationManagementFragment extends BaseFragment {

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_transportation_management;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.layout_opera_managemnet,R.id.layout_driver_management, R.id.layout_car_management, R.id.layout_Accept_lading, R.id.layout_contract_management})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_opera_managemnet:
                OperationSheetManagementActivity.actionStart(getActivity());
                break;
            case R.id.layout_driver_management:
                DriverManagementActivity.actionStart(getActivity());
                break;
            case R.id.layout_car_management:
                CarManagementActivity.actionStart(getActivity());
                break;
            case R.id.layout_Accept_lading:
                AcceptanceOfBillLadingActivity.actionStart(getActivity());
                break;
            case R.id.layout_contract_management:
                ContractManagementAvtivity.actionStart(getActivity());
                break;
        }
    }
}
