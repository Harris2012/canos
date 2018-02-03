package cn.ranta.canos.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.bean.FilterBean;
import cn.ranta.canos.fragment.design.DesignModeFilterFragment;
import cn.ranta.canos.recyclerview.viewholder.PreviewFilterButtonViewHolder;

public class PreviewFilterButtonAdapter extends RecyclerView.Adapter<PreviewFilterButtonViewHolder> {

    private DesignModeFilterFragment context;
    private List<FilterBean> filterBeanList;

    public PreviewFilterButtonAdapter(DesignModeFilterFragment context, List<FilterBean> filterBeanList) {

        this.context = context;
        this.filterBeanList = filterBeanList;
    }

    @Override
    public PreviewFilterButtonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context.getActivity());

        View itemView = inflater.inflate(R.layout.recyclerview_design_filter_item, parent, false);

        return new PreviewFilterButtonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PreviewFilterButtonViewHolder holder, final int position) {

        FilterBean filterBean = filterBeanList.get(position);
        holder.setText(filterBean.getSceneName());

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.setCurrentFilter(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterBeanList.size();
    }
}