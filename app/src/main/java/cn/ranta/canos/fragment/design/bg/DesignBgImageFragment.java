package cn.ranta.canos.fragment.design.bg;


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
import cn.ranta.canos.bean.BgImageBean;
import cn.ranta.canos.config.BasicConfig;
import cn.ranta.canos.recyclerview.adapter.DesignBgImageButtonAdapter;
import cn.ranta.canos.repository.BgImageRepository;
import cn.ranta.canos.repository.entity.BgImageEntity;

public class DesignBgImageFragment extends DesignBgBaseFragment {

    RecyclerView designBgImageRecyclerView;

    public DesignBgImageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_design_background_image, container, false);

        designBgImageRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_design_bgimage);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        designBgImageRecyclerView.setHasFixedSize(true);
        designBgImageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 6);
        designBgImageRecyclerView.setLayoutManager(gridLayoutManager);
        List<BgImageBean> bgImageBeanList = getBgImageBeanList();
        DesignBgImageButtonAdapter designBgImageButtonAdapter = new DesignBgImageButtonAdapter((DesignActivity) getActivity(), bgImageBeanList);
        designBgImageRecyclerView.setAdapter(designBgImageButtonAdapter);

    }

    private List<BgImageBean> getBgImageBeanList() {
        List<BgImageBean> bgImageBeanList = new ArrayList<>();

        BgImageRepository bgImageRepository = new BgImageRepository(getActivity(), BasicConfig.getCanosDBName());

        List<BgImageEntity> entityList = bgImageRepository.getBgImageEntityList();
        if (entityList != null && entityList.size() > 0) {
            for (BgImageEntity entity : entityList) {
                bgImageBeanList.add(new BgImageBean(entity.getImageKey(), entity.getImagePath()));
            }
        }

        return bgImageBeanList;
    }
}
