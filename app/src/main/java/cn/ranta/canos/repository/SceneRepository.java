package cn.ranta.canos.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.config.BasicConfig;
import cn.ranta.canos.repository.consts.SceneField;
import cn.ranta.canos.repository.consts.TableName;
import cn.ranta.canos.repository.entity.SceneEntity;

public class SceneRepository extends RepositoryBase {

    public SceneRepository(Context context, String dbName) {
        super(context, dbName);
    }

    public List<SceneEntity> getSceneEntityList(int imageCount) {

        List<SceneEntity> entityList = new ArrayList<>();

        SQLiteDatabase canosDatabase = canosSQLiteOpenHelper.getReadableDatabase();

        String[] columns = new String[]{
                SceneField.ImageCount,
                SceneField.PathData
        };

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(imageCount);

        Cursor cursor = canosDatabase.query(TableName.Scene, columns, String.format("%s = ?", SceneField.ImageCount), parameters, null, null, null);

        int imageCountColumn = cursor.getColumnIndex(SceneField.ImageCount);
        int pathDataColumn = cursor.getColumnIndex(SceneField.PathData);

        while (cursor.moveToNext()) {

            SceneEntity sceneEntity = new SceneEntity();

            int imageCountValue = cursor.getInt(imageCountColumn);
            String pathDataValue = cursor.getString(pathDataColumn);

            sceneEntity.setImageCount(imageCountValue);
            sceneEntity.setPathData(pathDataValue);

            entityList.add(sceneEntity);
        }

        return entityList;
    }
}
