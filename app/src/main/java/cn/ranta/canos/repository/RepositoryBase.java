package cn.ranta.canos.repository;

import android.content.Context;
import android.text.style.StrikethroughSpan;

import cn.ranta.canos.config.BasicConfig;

public abstract class RepositoryBase {

    private Context context;

    private String dbName;

    CanosSQLiteOpenHelper canosSQLiteOpenHelper;

    public RepositoryBase(Context context, String dbName) {

        this.context = context;
        this.dbName = dbName;

        this.canosSQLiteOpenHelper = new CanosSQLiteOpenHelper(context, dbName, null, 1);
    }

    public Context getContext() {
        return context;
    }
}
