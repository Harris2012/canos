package cn.ranta.canos.recyclerview.viewholder;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ranta.canos.R;

public class DesignBgImageButtonViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public DesignBgImageButtonViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.imageview_design_bgimage_item);
    }

    public void setBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {

        itemView.setOnClickListener(onClickListener);
    }
}
