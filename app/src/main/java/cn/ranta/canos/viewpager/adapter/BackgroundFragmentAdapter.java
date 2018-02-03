package cn.ranta.canos.viewpager.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.List;

import cn.ranta.canos.fragment.design.bg.DesignBgBaseFragment;

public class BackgroundFragmentAdapter extends FragmentStatePagerAdapter {

    List<DesignBgBaseFragment> fragmentList;

    public BackgroundFragmentAdapter(FragmentManager fragmentManager, List<DesignBgBaseFragment> fragmentList) {

        super(fragmentManager);

        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
