package cn.ranta.canos.utility;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.util.List;

import cn.ranta.canos.model.PointModel;

public final class PuzzleViewUtility {

    public static float getMinX(List<PointModel> pointModelList) {
        float minX = pointModelList.get(0).LocationX;

        for (int i = 1, size = pointModelList.size(); i < size; i++) {

            PointModel pointModel = pointModelList.get(i);

            minX = Math.min(minX, pointModel.LocationX);
        }

        return minX;
    }

    public static float getMinY(List<PointModel> pointModelList) {
        float minY = pointModelList.get(0).LocationY;

        for (int i = 1, size = pointModelList.size(); i < size; i++) {

            PointModel pointModel = pointModelList.get(i);

            minY = Math.min(minY, pointModel.LocationY);
        }

        return minY;
    }

    public static float getWidth(List<PointModel> pointModelList) {

        float minX = pointModelList.get(0).LocationX;
        float maxX = pointModelList.get(0).LocationX;

        for (int i = 1, size = pointModelList.size(); i < size; i++) {

            PointModel pointModel = pointModelList.get(i);

            minX = Math.min(minX, pointModel.LocationX);
            maxX = Math.max(maxX, pointModel.LocationX);
        }

        return maxX - minX;
    }

    public static float getHeight(List<PointModel> pointModelList) {

        float minY = pointModelList.get(0).LocationY;
        float maxY = pointModelList.get(0).LocationY;

        for (int i = 1, size = pointModelList.size(); i < size; i++) {

            PointModel pointModel = pointModelList.get(i);

            minY = Math.min(minY, pointModel.LocationY);
            maxY = Math.max(maxY, pointModel.LocationY);
        }

        return maxY - minY;
    }
}
