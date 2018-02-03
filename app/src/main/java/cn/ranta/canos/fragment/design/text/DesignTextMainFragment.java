package cn.ranta.canos.fragment.design.text;


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
import cn.ranta.canos.viewpager.adapter.TextFragmentAdapter;

public class DesignTextMainFragment extends Fragment {

    Button btnClose;

    ViewPager viewPager;

    public DesignTextMainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_design_text_main, container, false);

        btnClose = (Button) view.findViewById(R.id.button_close);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager_design_toolbar_text);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btnClose.setOnClickListener(click);

        //region ViewPager
        List<DesignTextBaseFragment> fragmentList = new ArrayList<>();

        fragmentList.add(new DesignTextSizeFragment());
        fragmentList.add(new DesignTextLocationFragment());

        FragmentManager fragmentManager = getChildFragmentManager();
        TextFragmentAdapter textFragmentAdapter = new TextFragmentAdapter(fragmentManager, fragmentList);
        viewPager.setAdapter(textFragmentAdapter);
        //endregion
    }

    private final View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            DesignActivity designActivity = (DesignActivity) getActivity();

            designActivity.hideTextFragment();
        }
    };
}
