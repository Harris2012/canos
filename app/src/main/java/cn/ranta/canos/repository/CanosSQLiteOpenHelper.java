package cn.ranta.canos.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.support.design.widget.TabLayout;

import cn.ranta.canos.repository.consts.BgColorField;
import cn.ranta.canos.repository.consts.BgImageField;
import cn.ranta.canos.repository.consts.CanvasSizeField;
import cn.ranta.canos.repository.consts.SceneField;
import cn.ranta.canos.repository.consts.TableName;

public class CanosSQLiteOpenHelper extends SQLiteOpenHelper {

    public CanosSQLiteOpenHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
    }

    public CanosSQLiteOpenHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, dbName, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TableName.CanvasSize
                + " (id integer primary key autoincrement, "
                + CanvasSizeField.Orientation + " int, "
                + CanvasSizeField.Width + " int, "
                + CanvasSizeField.Height + " int, "
                + CanvasSizeField.Status + " int)");

        db.execSQL("create table " + TableName.Scene
                + " (id integer primary key autoincrement, "
                + SceneField.ImageCount + " int, "
                + SceneField.PathData + " varchar(1000), "
                + SceneField.Status + " int)");

        db.execSQL("create table " + TableName.BgImage
                + " (id integer primary key autoincrement, "
                + BgImageField.ImageKey + " int, "
                + BgImageField.ImagePath + " varchar(1000), "
                + BgImageField.Status + " int)");

        db.execSQL("create table " + TableName.BgColor
                + " (id integer primary key autoincrement, "
                + BgColorField.ColorKey + " int, "
                + BgColorField.ColorValue + " varchar(9), "
                + BgColorField.Status + " int)");

        init(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase canosDatabase, int oldVersion, int newVersion) {

    }

    private void init(SQLiteDatabase canosDatabase) {

        //CanvasSize
        {
            canosDatabase.insert(TableName.CanvasSize, null, getCanvasSizeEntity(0, 100, 100));
            canosDatabase.insert(TableName.CanvasSize, null, getCanvasSizeEntity(1, 9, 16));
            canosDatabase.insert(TableName.CanvasSize, null, getCanvasSizeEntity(1, 618, 1000));
            canosDatabase.insert(TableName.CanvasSize, null, getCanvasSizeEntity(1, 3, 4));
            canosDatabase.insert(TableName.CanvasSize, null, getCanvasSizeEntity(2, 16, 9));
            canosDatabase.insert(TableName.CanvasSize, null, getCanvasSizeEntity(2, 1000, 618));
            canosDatabase.insert(TableName.CanvasSize, null, getCanvasSizeEntity(2, 4, 3));
        }

        // region Scene
        {
            canosDatabase.insert(TableName.Scene, null, getSceneEntity(1,
                    "20f,20f;748f,20f;748f,540f;20f,540f"));

            canosDatabase.insert(TableName.Scene, null, getSceneEntity(2,
                    "0f,0f;768f,0f;768f,540f;0f,540f|0f,552f;768f,552f;768f,1080f;0f,1080f"));
            canosDatabase.insert(TableName.Scene, null, getSceneEntity(2,
                    "0f,0f;768f,0f;768f,396f;0f,396f|0f,408f;768f,408f;768f,1080f;0f,1080f"));

            canosDatabase.insert(TableName.Scene, null, getSceneEntity(3,
                    "0f,0f;768f,0f;768f,352.8f;0f,352.8f|0f,364.8f;768f,364.8f;768f,715.2f;0f,715.2f|0f,727.2f;768f,727.2f;768f,1080f;0f,1080f"));
            canosDatabase.insert(TableName.Scene, null, getSceneEntity(3,
                    "0f,0f;768f,0f;768f,540f;0f,540f|0f,552f;378f,552f;378f,1080f;0f,1080f|390f,552f;768f,552f;768f,1080f;390f,1080f"));

            canosDatabase.insert(TableName.Scene, null, getSceneEntity(4,
                    "0f,0f;378f,0f;378f,540f;0f,540f|390f,0f;768f,0f;768f,540f;390f,540f|390f,552f;768f,552f;768f,1080f;390f,1080f|0f,552f;378f,552f;378f,1080f;0f,1080f"));
            canosDatabase.insert(TableName.Scene, null, getSceneEntity(4,
                    "0f,0f;378f,0f;378f,720f;0f,720f|390f,0f;768f,0f;768f,360f;390f,360f|390f,372f;768f,372f;768f,1080f;390f,1080f|0f,732f;378f,732f;378f,1080f;0f,1080f"));

            canosDatabase.insert(TableName.Scene, null, getSceneEntity(5,
                    "0f,0f;378f,0f;378f,540f;0f,540f|390f,0f;768f,0f;768f,352.8f;390f,352.8f|390f,364.8f;768f,364.8f;768f,715.2f;390f,715.2f|390f,727.2f;768f,727.2f;768f,1080f;390f,1080f|0f,552f;378f,552f;378f,1080f;0f,1080f"));
            canosDatabase.insert(TableName.Scene, null, getSceneEntity(5,
                    "0f,0f;378f,0f;378f,352.8f;0f,352.8f|390f,0f;768f,0f;768f,352.8f;390f,352.8f|0f,364.8f;768f,364.8f;768f,715.2f;0f,715.2f|390f,727.2f;768f,727.2f;768f,1080f;390f,1080f|0f,727.2f;378f,727.2f;378f,1080f;0f,1080f"));
        }
        // endregion

        // region bgimage
        {
            for (int i = 1; i < 27; i++) {
                canosDatabase.insert(TableName.BgImage, null, getBackgroundEntity(i, String.format("canvas/background/bg_%d.png", i)));
            }
        }
        // endregion

        // region bgcolor
        {
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(1, "#CFE7FF"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(2, "#EEEDDE"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(3, "#003366"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(4, "#DEE8E6"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(5, "#1B93E4"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(6, "#54B486"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(7, "#2594C4"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(8, "#EDB7D7"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(9, "#0163B1"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(10, "#FDDDE0"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(11, "#EEEEEE"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(12, "#8AC7C2"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(13, "#FEA64F"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(14, "#A3BF12"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(15, "#FFFFFF"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(16, "#B7D4E6"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(17, "#FFFFFF"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(18, "#EDF48D"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(19, "#FF0000"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(20, "#FFFFFF"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(21, "#E4CEA1"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(22, "#E5E5E5"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(23, "#F3F3F3"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(24, "#EB2227"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(25, "#EA94AF"));
            canosDatabase.insert(TableName.BgColor, null, getBgColorEntity(26, "#DEFBF9"));

        }
        // endregion
    }

    private ContentValues getCanvasSizeEntity(int orientation, int width, int height) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(CanvasSizeField.Orientation, orientation);
        contentValues.put(CanvasSizeField.Width, width);
        contentValues.put(CanvasSizeField.Height, height);
        contentValues.put(CanvasSizeField.Status, 1);

        return contentValues;
    }

    private ContentValues getSceneEntity(int imageCount, String pathData) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(SceneField.ImageCount, imageCount);
        contentValues.put(SceneField.PathData, pathData);
        contentValues.put(SceneField.Status, 1);

        return contentValues;
    }

    private ContentValues getBackgroundEntity(int key, String path) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(BgImageField.ImageKey, key);
        contentValues.put(BgImageField.ImagePath, path);
        contentValues.put(BgImageField.Status, 1);

        return contentValues;
    }

    private ContentValues getBgColorEntity(int colorKey, String colorValue) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(BgColorField.ColorKey, colorKey);
        contentValues.put(BgColorField.ColorValue, colorValue);
        contentValues.put(BgColorField.Status, 1);

        return contentValues;
    }
}
