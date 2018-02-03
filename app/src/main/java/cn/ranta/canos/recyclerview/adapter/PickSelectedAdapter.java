package cn.ranta.canos.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.activity.PickImageActivity;
import cn.ranta.canos.fragment.pick.PickSelectedFragment;
import cn.ranta.canos.recyclerview.viewholder.PickSelectedViewHolder;

public class PickSelectedAdapter extends RecyclerView.Adapter<PickSelectedViewHolder> {

    private PickSelectedFragment context;
    private List<String> filePathList;

    public PickSelectedAdapter(PickSelectedFragment context, ArrayList<String> filePathList) {

        this.context = context;
        this.filePathList = filePathList;
    }

    @Override
    public PickSelectedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context.getActivity());

        View view = inflater.inflate(R.layout.recyclerview_item_selected_image, parent, false);

        return new PickSelectedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PickSelectedViewHolder holder, int position) {

        final String filePath = filePathList.get(position);

        holder.setFilePath(filePath);

//        holder.setTitle(folderBean.getName());
//        holder.setCount(folderBean.getPictureCount());
//        holder.setFirstImagePath(folderBean.getImagePath());
//
//        holder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                context.onViewHolderClicked(folderBean);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return this.filePathList.size();
    }
}
