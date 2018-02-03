package cn.ranta.canos.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.repository.consts.BgColorField;
import cn.ranta.canos.repository.consts.TableName;
import cn.ranta.canos.repository.entity.BgColorEntity;
import cn.ranta.canos.repository.entity.BgImageEntity;

public class BgColorRepository extends RepositoryBase {

    public BgColorRepository(Context context, String dbName) {
        super(context, dbName);
    }

    public List<BgColorEntity> getBgColorEntityList() {

        List<BgColorEntity> entityList = new ArrayList<>();

        SQLiteDatabase canosDatabase = canosSQLiteOpenHelper.getReadableDatabase();

        String[] columns = new String[]{
                BgColorField.ColorKey,
                BgColorField.ColorValue
        };

        Cursor cursor = canosDatabase.query(TableName.BgColor, columns, null, null, null, null, null);

        int colorKeyColumn = cursor.getColumnIndex(BgColorField.ColorKey);
        int colorValueColumn = cursor.getColumnIndex(BgColorField.ColorValue);

        while (cursor.moveToNext()) {

            BgColorEntity bgColorEntity = new BgColorEntity();

            int colorKey = cursor.getInt(colorKeyColumn);
            String colorValue = cursor.getString(colorValueColumn);

            bgColorEntity.setColorKey(colorKey);
            bgColorEntity.setColorValue(colorValue);

            entityList.add(bgColorEntity);
        }

        return entityList;
    }
}
