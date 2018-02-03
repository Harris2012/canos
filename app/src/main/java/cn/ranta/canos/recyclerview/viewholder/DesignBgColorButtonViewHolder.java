package cn.ranta.canos.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import cn.ranta.canos.R;

public class DesignBgColorButtonViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public DesignBgColorButtonViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.imageview_design_bgcolor_item);
    }

    public void setColor(int colorValue) {
        imageView.setBackgroundColor(colorValue);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {

        itemView.setOnClickListener(onClickListener);
    }
}
