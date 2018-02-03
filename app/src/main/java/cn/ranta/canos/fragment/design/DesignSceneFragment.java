package cn.ranta.canos.fragment.design;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.bean.BgColorBean;
import cn.ranta.canos.bean.BgImageBean;
import cn.ranta.canos.config.BasicConfig;
import cn.ranta.canos.model.ImageModel;
import cn.ranta.canos.model.AreaModel;
import cn.ranta.canos.model.PointModel;
import cn.ranta.canos.repository.SceneRepository;
import cn.ranta.canos.repository.entity.SceneEntity;
import cn.ranta.canos.utility.BitmapUtility;
import cn.ranta.canos.utility.PuzzleViewUtility;
import cn.ranta.canos.view.PuzzleView;

public class DesignSceneFragment extends Fragment {

    PuzzleView puzzleView;

    List<String> filePathList;

    public DesignSceneFragment() {
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_design_scene, container, false);

        puzzleView = (PuzzleView) view.findViewById(R.id.puzzleview_design_scene);
        puzzleView.initCanvasSize(768, 1080);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        filePathList = (List<String>) bundle.getSerializable("filePathList");

        if (filePathList != null && filePathList.size() > 0) {

            List<AreaModel> areaModelList = getAreaModelList(getActivity(), filePathList);

            puzzleView.setAreaModelList(areaModelList);
        }
    }

//    private final View.OnClickListener textLayerClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//            TextView textView = (TextView) view;
//            textView.setText(textView.getText() + "a");
//        }
//    };

    private static List<AreaModel> getAreaModelList(Context context, List<String> filePathList) {

        List<AreaModel> areaModelList = null;

        SceneRepository sceneRepository = new SceneRepository(context, BasicConfig.getCanosDBName());

        int imageCount = filePathList.size();

        List<SceneEntity> sceneEntityList = sceneRepository.getSceneEntityList(imageCount);
        if (sceneEntityList != null && sceneEntityList.size() > 0) {

            SceneEntity sceneEntity = sceneEntityList.get(0);

            areaModelList = GetGroupModelList(sceneEntity.getPathData());

            for (int i = 0, imageModelListSize = areaModelList.size(); i < imageModelListSize; i++) {

                AreaModel areaModel = areaModelList.get(i);

                List<PointModel> pointModelList = areaModel.PointModelList;

                float areaLeft = PuzzleViewUtility.getMinX(pointModelList);
                float areaTop = PuzzleViewUtility.getMinY(pointModelList);
                float areaWidth = PuzzleViewUtility.getWidth(pointModelList);
                float areaHeight = PuzzleViewUtility.getHeight(pointModelList);
                Bitmap cuttedBitmap = BitmapUtility.GetBitmapSample(filePathList.get(i), (int) areaWidth, (int) areaHeight);
                Bitmap scaledBitmap = BitmapUtility.ScaleImage(cuttedBitmap, (int) areaWidth, (int) areaHeight);

                Path path = new Path();
                path.moveTo(pointModelList.get(0).LocationX, pointModelList.get(0).LocationY);
                for (int pointIndex = 0, pointCount = pointModelList.size(); pointIndex < pointCount; pointIndex++) {

                    path.lineTo(pointModelList.get(pointIndex).LocationX, pointModelList.get(pointIndex).LocationY);
                }
                path.close();

                areaModel.AreaLeft = areaLeft;
                areaModel.AreaTop = areaTop;
                areaModel.AreaWidth = areaWidth;
                areaModel.AreaHeight = areaHeight;
                areaModel.OriginPath = path;

                ImageModel imageModel = new ImageModel();
                imageModel.ImageFilePath = filePathList.get(i);
                imageModel.ScaledBitmap = scaledBitmap;
                imageModel.ImageWidth = scaledBitmap.getWidth();
                imageModel.ImageHeight = scaledBitmap.getHeight();
                areaModel.ImageModel = imageModel;
            }
        }

        return areaModelList;
    }

    public void SaveAs(File outputFile) {
        puzzleView.SaveAs(outputFile);
    }

    public void setBgImage(BgImageBean bgImageBean) {

        AssetManager assetManager = getResources().getAssets();

        try {

            InputStream stream = assetManager.open(bgImageBean.getImagePath());

            Bitmap bitmap = BitmapFactory.decodeStream(stream);

            puzzleView.setCanvasBackgroundImage(bitmap);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void setBgColor(BgColorBean bgColorBean) {

        puzzleView.setCanvasBackgroundColor(bgColorBean.getColorValue());
    }

    private static List<AreaModel> GetGroupModelList(String pathData) {

        List<AreaModel> groupModelList = new ArrayList<>();

        if (pathData != null) {

            String[] groupList = pathData.split("\\|");
            for (String group : groupList) {

                AreaModel areaModel = new AreaModel();
                List<PointModel> pointModelList = new ArrayList<>();
                areaModel.PointModelList = pointModelList;

                String[] pointList = group.split(";");
                for (String point : pointList) {

                    String[] locationList = point.split(",");

                    PointModel pointModel = new PointModel();
                    pointModel.LocationX = Float.parseFloat(locationList[0]);
                    pointModel.LocationY = Float.parseFloat(locationList[1]);

                    pointModelList.add(pointModel);
                }

                groupModelList.add(areaModel);
            }
        }

        return groupModelList;
    }
}
