package cn.ranta.canos.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.bean.ImageFileBean;
import cn.ranta.canos.fragment.pick.PickFileFragment;
import cn.ranta.canos.recyclerview.viewholder.PickImageViewHolder;

public class PickImageAdapter extends RecyclerView.Adapter<PickImageViewHolder> {

    private PickFileFragment context;
    private List<ImageFileBean> beanList;

    public PickImageAdapter(PickFileFragment context, List<ImageFileBean> beanList) {

        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public PickImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context.getActivity());

        View imageView = inflater.inflate(R.layout.recyclerview_item_image, parent, false);

        return new PickImageViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(PickImageViewHolder pickImageViewHolder, final int position) {

        final ImageFileBean fileBean = beanList.get(position);

        pickImageViewHolder.setFirstImagePath(fileBean.getFilePath());
        pickImageViewHolder.setImageSelected(context.containsFilePath(fileBean.getFilePath()));

        pickImageViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(context.onFileBeanClicked(fileBean))
                {
                    notifyItemChanged(position);
                }
            }
        });
    }

    public void setImageFileBeanList(List<ImageFileBean> beanList) {

        this.beanList = beanList != null ? beanList : new ArrayList<ImageFileBean>();

        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }
}

