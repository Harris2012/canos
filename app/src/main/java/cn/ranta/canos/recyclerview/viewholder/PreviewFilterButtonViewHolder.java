package cn.ranta.canos.recyclerview.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import cn.ranta.canos.R;
import cn.ranta.canos.listener.AnimateFirstDisplayListener;

public class PreviewFilterButtonViewHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public PreviewFilterButtonViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.textview_design_filter_item);
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {

        itemView.setOnClickListener(onClickListener);
    }
}
