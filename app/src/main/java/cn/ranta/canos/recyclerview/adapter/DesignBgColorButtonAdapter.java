package cn.ranta.canos.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.activity.DesignActivity;
import cn.ranta.canos.bean.BgColorBean;
import cn.ranta.canos.recyclerview.viewholder.DesignBgColorButtonViewHolder;

public class DesignBgColorButtonAdapter extends RecyclerView.Adapter<DesignBgColorButtonViewHolder> {

    private DesignActivity designActivity;
    private List<BgColorBean> bgColorBeanList;

    public DesignBgColorButtonAdapter(DesignActivity designActivity, List<BgColorBean> bgColorBeanList) {

        this.designActivity = designActivity;
        this.bgColorBeanList = bgColorBeanList;
    }

    @Override
    public DesignBgColorButtonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(this.designActivity);

        View itemView = inflater.inflate(R.layout.recyclerview_design_bgcolor_item, parent, false);

        return new DesignBgColorButtonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DesignBgColorButtonViewHolder holder, final int position) {

        final BgColorBean bgColorBean = bgColorBeanList.get(position);

        holder.setColor(bgColorBean.getColorValue());

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designActivity.setBgColor(bgColorBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bgColorBeanList.size();
    }
}