package cn.ranta.canos.fragment.design.bg;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.activity.DesignActivity;
import cn.ranta.canos.viewpager.adapter.BackgroundFragmentAdapter;

public class DesignBgMainFragment extends Fragment {

    Button btnClose;

    ViewPager viewPager;

    public DesignBgMainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_design_background_main, container, false);

        btnClose = (Button) view.findViewById(R.id.button_close);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager_design_toolbar_background);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btnClose.setOnClickListener(click);

        //region ViewPager
        List<DesignBgBaseFragment> fragmentList = new ArrayList<>();

        fragmentList.add(new DesignBgColorFragment());
        fragmentList.add(new DesignBgImageFragment());

        FragmentManager fragmentManager = getChildFragmentManager();
        BackgroundFragmentAdapter backgroundFragmentAdapter = new BackgroundFragmentAdapter(fragmentManager, fragmentList);
        viewPager.setAdapter(backgroundFragmentAdapter);
        //endregion
    }

    /**
     * 关闭面板
     */
    private final View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            DesignActivity designActivity=(DesignActivity)getActivity();

            designActivity.hideBgMainFragment();
        }
    };
}
