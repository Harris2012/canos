package cn.ranta.canos;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

import cn.ranta.canos.config.BasicConfig;

public class CanosApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {

        BasicConfig.setCanosDBName("canos.db");

        BasicConfig.setAppFolderRoot(Environment.getExternalStorageDirectory().getAbsolutePath() + "/canos");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiskCache(new File(BasicConfig.getCacheFolderPath())))
                .memoryCacheSizePercentage(8)
                .memoryCacheExtraOptions(480, 800)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();


        ImageLoader.getInstance().init(config);

    }
}
