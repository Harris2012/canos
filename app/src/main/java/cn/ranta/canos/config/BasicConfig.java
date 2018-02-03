package cn.ranta.canos.config;

import android.os.Environment;

public class BasicConfig {

    private static volatile String appFolderRoot;

    private static volatile String cacheFolderPath;

    private static volatile String outputFolderPath;

    private static volatile String canosDBName;

    public static String getAppFolderRoot() {
        return appFolderRoot;
    }

    public static void setAppFolderRoot(String appFolderRoot) {
        BasicConfig.appFolderRoot = appFolderRoot;
        BasicConfig.cacheFolderPath = appFolderRoot + "/cache";
        BasicConfig.outputFolderPath = appFolderRoot + "/output";
    }

    public static String getCacheFolderPath() {
        return cacheFolderPath;
    }

    public static String getOutputFolderPath() {
        return outputFolderPath;
    }

    public static String getCanosDBName() {
        return canosDBName;
    }

    public static void setCanosDBName(String canosDBName) {
        BasicConfig.canosDBName = canosDBName;
    }
}
