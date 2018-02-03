package cn.ranta.canos.fragment.design.bg;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.activity.DesignActivity;
import cn.ranta.canos.bean.BgColorBean;
import cn.ranta.canos.config.BasicConfig;
import cn.ranta.canos.recyclerview.adapter.DesignBgColorButtonAdapter;
import cn.ranta.canos.repository.BgColorRepository;
import cn.ranta.canos.repository.entity.BgColorEntity;

public class DesignBgColorFragment extends DesignBgBaseFragment {

    RecyclerView designBgColorRecyclerView;

    public DesignBgColorFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_design_background_color, container, false);

        designBgColorRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_design_bgcolor);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        designBgColorRecyclerView.setHasFixedSize(true);
        designBgColorRecyclerView.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 6);
        designBgColorRecyclerView.setLayoutManager(gridLayoutManager);
        List<BgColorBean> bgColorBeanList = getBgColorBeanList();
        DesignBgColorButtonAdapter designBgColorButtonAdapter = new DesignBgColorButtonAdapter((DesignActivity) getActivity(), bgColorBeanList);
        designBgColorRecyclerView.setAdapter(designBgColorButtonAdapter);

    }

    private List<BgColorBean> getBgColorBeanList() {
        List<BgColorBean> bgColorBeanList = new ArrayList<>();

        BgColorRepository bgColorRepository = new BgColorRepository(getActivity(), BasicConfig.getCanosDBName());

        List<BgColorEntity> entityList = bgColorRepository.getBgColorEntityList();
        if (entityList != null && entityList.size() > 0) {
            for (BgColorEntity entity : entityList) {

                bgColorBeanList.add(new BgColorBean(entity.getColorKey(), Color.parseColor(entity.getColorValue())));
            }
        }

        return bgColorBeanList;
    }
}
