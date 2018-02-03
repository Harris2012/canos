package cn.ranta.canos.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.bean.ImageFolderBean;
import cn.ranta.canos.fragment.pick.PickFolderFragment;
import cn.ranta.canos.recyclerview.viewholder.PickFolderViewHolder;

public class PickFolderAdapter extends RecyclerView.Adapter<PickFolderViewHolder> {

    private PickFolderFragment context;
    private List<ImageFolderBean> beanList;

    public PickFolderAdapter(PickFolderFragment context, List<ImageFolderBean> beanList) {

        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public PickFolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context.getActivity());

        View view = inflater.inflate(R.layout.recyclerview_item_folder, parent, false);

        return new PickFolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PickFolderViewHolder holder, final int position) {

        final ImageFolderBean folderBean = beanList.get(position);

        holder.setTitle(folderBean.getName());
        holder.setCount(folderBean.getPictureCount());
        holder.setFirstImagePath(folderBean.getImagePath());

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.onViewHolderClicked(folderBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public void setImageFolderBeanList(List<ImageFolderBean> beanList) {

        this.beanList = beanList != null ? beanList : new ArrayList<ImageFolderBean>();

        this.notifyDataSetChanged();
    }
}
