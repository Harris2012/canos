package cn.ranta.canos.utility;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.ranta.canos.config.BasicConfig;

public final class StorageUtility {

    public static File getOutputFile(Context context) {

        String outputFolderPath = BasicConfig.getOutputFolderPath();
        File outputFolder = new File(outputFolderPath);

        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        if (!outputFolder.exists()) {
            if (!outputFolder.mkdirs()) {
                Toast.makeText(context, "没有写存储的权限", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());

        return new File(outputFolder + File.separator + "IMG_" + timeStamp + ".jpg");
    }
}
