package cn.ranta.canos.fragment.design;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.bean.FilterBean;
import cn.ranta.canos.enums.SceneKey;
import cn.ranta.canos.recyclerview.adapter.PreviewFilterButtonAdapter;

public class DesignModeFilterFragment extends Fragment {

    RecyclerView previewFilterRecyclerView;

    PreviewFilterButtonAdapter previewFilterButtonAdapter;

    public DesignModeFilterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_design_mode_filter, container, false);

        previewFilterRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_preview_filter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        previewFilterRecyclerView.setHasFixedSize(true);
        previewFilterRecyclerView.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        previewFilterRecyclerView.setLayoutManager(linearLayoutManager);

        List<FilterBean> filterBeanList = getSceneBeanList();
        previewFilterButtonAdapter = new PreviewFilterButtonAdapter(this, filterBeanList);
        previewFilterRecyclerView.setAdapter(previewFilterButtonAdapter);
    }

    private List<FilterBean> getSceneBeanList() {
        List<FilterBean> filterBeanList = new ArrayList<>();

        filterBeanList.add(new FilterBean(SceneKey.Gray, "Gray"));
        filterBeanList.add(new FilterBean(SceneKey.Mosatic, "Mosatic"));
        filterBeanList.add(new FilterBean(SceneKey.Lomo, "Lomo"));
        filterBeanList.add(new FilterBean(SceneKey.Nostalgic, "Nostalgic"));
        filterBeanList.add(new FilterBean(SceneKey.Comics, "Comics"));
        filterBeanList.add(new FilterBean(SceneKey.Brown, "Brown"));
        filterBeanList.add(new FilterBean(SceneKey.SketchPencil, "SketchPencil"));

        return filterBeanList;
    }

    public void setCurrentFilter(int position) {

    }
}
