package cn.ranta.canos.asynctask;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.bean.ImageFolderBean;
import cn.ranta.canos.recyclerview.adapter.PickFolderAdapter;

public class LoadFolderAsyncTask extends AsyncTask<Integer, Integer, List<ImageFolderBean>> {

    /*查询id、  缩略图、原图、文件夹ID、 文件夹名、 文件夹分类的图片总数*/
    private static String[] columns = {
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            "COUNT(1) AS count",
            MediaStore.Images.Media.DATA
    };

    Context context;
    PickFolderAdapter pickFolderAdapter;

    public LoadFolderAsyncTask(Context context, PickFolderAdapter pickFolderAdapter) {
        super();

        this.context = context;
        this.pickFolderAdapter = pickFolderAdapter;
    }

    @Override
    protected List<ImageFolderBean> doInBackground(Integer... params) {

        List<ImageFolderBean> returnVaue = new ArrayList<>();

        String selection = "0==0) GROUP BY (" + MediaStore.Images.Media.BUCKET_ID;
        String sortOrder = MediaStore.Images.Media.DATE_MODIFIED;

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                selection,
                null,
                sortOrder);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                int buckedNameColumn = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                int countColumn = cursor.getColumnIndex("count");
                int dataColumn= cursor.getColumnIndex(MediaStore.Images.Media.DATA);

                do {

                    String bucketName = cursor.getString(buckedNameColumn);
                    if (!Environment.getExternalStorageDirectory().getPath().contains(bucketName)) {

                        ImageFolderBean folderBean = new ImageFolderBean();

                        folderBean.setName(bucketName);
                        folderBean.setPictureCount(cursor.getInt(countColumn));
                        folderBean.setImagePath(cursor.getString(dataColumn));

                        returnVaue.add(0, folderBean);
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
    protected void onPostExecute(List<ImageFolderBean> beanList) {

        pickFolderAdapter.setImageFolderBeanList(beanList);
    }
}
