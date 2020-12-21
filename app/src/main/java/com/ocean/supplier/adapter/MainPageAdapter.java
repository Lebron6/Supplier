package com.ocean.supplier.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.ocean.supplier.fragment.HomeFragment;
import com.ocean.supplier.fragment.MineFragment;
import com.ocean.supplier.fragment.OperaFragment;
import com.ocean.supplier.fragment.TransportationManagementFragment;


public class MainPageAdapter extends FragmentPagerAdapter {


    private OperaFragment homeFragment;
    private TransportationManagementFragment transportationManagementFragment;
    private MineFragment mineFragment;

    public MainPageAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            if(homeFragment==null){
                homeFragment = new OperaFragment();
                return homeFragment;
            }else{
                return homeFragment;
            }
        }else if (position==1){
            if(transportationManagementFragment ==null){
                transportationManagementFragment = new TransportationManagementFragment();
                return transportationManagementFragment;
            }else{
                return transportationManagementFragment;
            }
        }else if (position==2){
            if(mineFragment ==null){
                mineFragment = new MineFragment();
                return mineFragment;
            }else{
                return mineFragment;
            }
        }else {
return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}
