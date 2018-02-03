package cn.ranta.canos.recyclerview.adapter;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.activity.DesignActivity;
import cn.ranta.canos.bean.BgImageBean;
import cn.ranta.canos.recyclerview.viewholder.DesignBgImageButtonViewHolder;

public class DesignBgImageButtonAdapter extends RecyclerView.Adapter<DesignBgImageButtonViewHolder> {

    private DesignActivity designActivity;
    private List<BgImageBean> bgImageBeanList;

    public DesignBgImageButtonAdapter(DesignActivity designActivity, List<BgImageBean> bgImageBeanList) {

        this.designActivity = designActivity;
        this.bgImageBeanList = bgImageBeanList;
    }

    @Override
    public DesignBgImageButtonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(this.designActivity);

        View itemView = inflater.inflate(R.layout.recyclerview_design_bgimage_item, parent, false);

        return new DesignBgImageButtonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DesignBgImageButtonViewHolder holder, final int position) {

        final BgImageBean bgImageBean = bgImageBeanList.get(position);

        try {

            AssetManager assetManager = this.designActivity.getResources().getAssets();

            InputStream stream = assetManager.open(bgImageBean.getImagePath());

            Bitmap bitmap = BitmapFactory.decodeStream(stream);

            holder.setBitmap(bitmap);

        } catch (IOException e) {

            e.printStackTrace();
        }

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designActivity.setBgImage(bgImageBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bgImageBeanList.size();
    }
}