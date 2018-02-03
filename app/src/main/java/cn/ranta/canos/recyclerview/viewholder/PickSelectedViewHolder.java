package cn.ranta.canos.recyclerview.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import cn.ranta.canos.R;
import cn.ranta.canos.listener.AnimateFirstDisplayListener;

public class PickSelectedViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    private AnimateFirstDisplayListener animateFirstDisplayListener;

    public PickSelectedViewHolder(View itemView) {
        super(itemView);

        animateFirstDisplayListener = new AnimateFirstDisplayListener();

        imageView = (ImageView) itemView.findViewById(R.id.recyclerview_imageview);
    }

    public void setFilePath(String filePath) {
        ImageLoader
                .getInstance()
                .displayImage(
                        ImageDownloader.Scheme.FILE.wrap(filePath),
                        imageView,
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
