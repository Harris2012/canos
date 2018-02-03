package cn.ranta.canos.asynctask;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.bean.ImageFileBean;
import cn.ranta.canos.recyclerview.adapter.PickImageAdapter;

public class LoadFileAsyncTask extends AsyncTask<Integer, Integer, List<ImageFileBean>> {

    private static String[] columns = { MediaStore.Images.Media.DATA };

    Context context;

    String folderPath;
    PickImageAdapter pickImageAdapter;

    public LoadFileAsyncTask(Context context, String folderPath, PickImageAdapter pickImageAdapter) {
        super();

        this.context = context;

        this.folderPath = folderPath;
        this.pickImageAdapter = pickImageAdapter;
    }

    @Override
    protected List<ImageFileBean> doInBackground(Integer... params) {

        List<ImageFileBean> returnVaue = new ArrayList<>();

        //根据时间升序
        String sortOrder = MediaStore.Images.Media.DATE_MODIFIED;

        String whereClause = MediaStore.Images.ImageColumns.DATA + " like'" + folderPath + "/%'";

        //获取大图的游标
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                whereClause,
                null,
                sortOrder);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                int filePathColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

                do {

                    String filePath = cursor.getString(filePathColumn);
                    if (!Environment.getExternalStorageDirectory().getPath().contains(filePath)) {

                        ImageFileBean fileBean = new ImageFileBean();

                        fileBean.setFilePath(filePath);

                        returnVaue.add(0, fileBean);
                    }

                } while (cursor.moveToNext());
            } else {
                Log.d("cursor.is.empty", "cursor is empty");
            }
        } else {
            Log.d("cursor.is.null", "cursor is null");
        }
        return returnVaue;
    }

    @Override
    protected void onPostExecute(List<ImageFileBean> result) {

        pickImageAdapter.setImageFileBeanList(result);
    }
}