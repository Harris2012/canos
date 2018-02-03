package cn.ranta.canos.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.repository.consts.BgImageField;
import cn.ranta.canos.repository.consts.TableName;
import cn.ranta.canos.repository.entity.BgImageEntity;

public class BgImageRepository extends RepositoryBase {

    public BgImageRepository(Context context, String dbName) {
        super(context, dbName);
    }

    public List<BgImageEntity> getBgImageEntityList() {

        List<BgImageEntity> entityList = new ArrayList<>();

        SQLiteDatabase canosDatabase = canosSQLiteOpenHelper.getReadableDatabase();

        String[] columns = new String[]{
                BgImageField.ImageKey,
                BgImageField.ImagePath
        };
        Cursor cursor = canosDatabase.query(TableName.BgImage, columns, null, null, null, null, null);

        int keyColumn = cursor.getColumnIndex(BgImageField.ImageKey);
        int pathColumn = cursor.getColumnIndex(BgImageField.ImagePath);

        while (cursor.moveToNext()) {

            BgImageEntity bgImageEntity = new BgImageEntity();

            int keyValue = cursor.getInt(keyColumn);
            String pathValue = cursor.getString(pathColumn);

            bgImageEntity.setImageKey(keyValue);
            bgImageEntity.setImagePath(pathValue);

            entityList.add(bgImageEntity);
        }

        return entityList;
    }
}
