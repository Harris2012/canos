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

public class PickImageViewHolder extends RecyclerView.ViewHolder {

    private AnimateFirstDisplayListener animateFirstDisplayListener;

    ImageView iconImageView;
    ImageView selectedImageView;

    public PickImageViewHolder(View itemView) {
        super(itemView);

        animateFirstDisplayListener = new AnimateFirstDisplayListener();

        iconImageView = (ImageView) itemView.findViewById(R.id.image_icon);
        selectedImageView = (ImageView) itemView.findViewById(R.id.image_selected);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
    }

    public void setImageSelected(boolean selected) {
        if(selected){
            selectedImageView.setVisibility(View.VISIBLE);
        }
        else {
            selectedImageView.setVisibility(View.GONE);
        }
    }

    public void setFirstImagePath(String firstImagePath) {
        ImageLoader
                .getInstance()
                .displayImage(
                        ImageDownloader.Scheme.FILE.wrap(firstImagePath),
                        iconImageView,
                        buildDisplayImageOptionsDefault(R.drawable.defaultpic),
                        animateFirstDisplayListener);
    }

    /**
     * <li> 默认option配置 </li>,可设置默认显示图片
     */
    private DisplayImageOptions buildDisplayImageOptionsDefault(int drawable) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inSampleSize = 1024 * 2;

        return new DisplayImageOptions.Builder()
                .showImageOnLoading(drawable)
                .showImageForEmptyUri(drawable)
                .showImageOnFail(drawable)
                .decodingOptions(options)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }
}