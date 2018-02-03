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

public class PickFolderViewHolder extends RecyclerView.ViewHolder {

    private AnimateFirstDisplayListener animateFirstDisplayListener;

    ImageView imageView;
    private TextView titleTextView;
    private TextView countTextView;

    public PickFolderViewHolder(View itemView) {
        super(itemView);

        animateFirstDisplayListener = new AnimateFirstDisplayListener();

        imageView = (ImageView) itemView.findViewById(R.id.folder_icon);
        titleTextView = (TextView) itemView.findViewById(R.id.folder_title);
        countTextView = (TextView) itemView.findViewById(R.id.folder_count);

    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    public void setCount(int count) {
        countTextView.setText(String.valueOf(count));
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
    }

    public void setFirstImagePath(String firstImagePath) {
        ImageLoader
                .getInstance()
                .displayImage(
                        ImageDownloader.Scheme.FILE.wrap(firstImagePath),
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
