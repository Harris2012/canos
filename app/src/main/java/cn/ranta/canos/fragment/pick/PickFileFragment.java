package cn.ranta.canos.fragment.pick;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.activity.PickImageActivity;
import cn.ranta.canos.asynctask.LoadFileAsyncTask;
import cn.ranta.canos.bean.ImageFileBean;
import cn.ranta.canos.recyclerview.adapter.PickImageAdapter;

public class PickFileFragment extends Fragment {

    RecyclerView recyclerView;

    PickImageAdapter pickImageAdapter;

    private String folderPath;
    private List<ImageFileBean> beanList;

    public PickFileFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        folderPath = bundle.getString("folderPath");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_pick_file, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        beanList = new ArrayList<>();

        pickImageAdapter = new PickImageAdapter(this, beanList);

        recyclerView = (RecyclerView) view.findViewById(R.id.pick_image_image_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pickImageAdapter);

        LoadFileAsyncTask asyncTask = new LoadFileAsyncTask(getActivity(), folderPath, pickImageAdapter);

        asyncTask.execute();
    }

    /**
     * 点击选择获取取消图片
     *
     * @param fileBean
     * @return若操作成功，返回true，否则返回false
     */
    public boolean onFileBeanClicked(ImageFileBean fileBean) {

        PickImageActivity pickImageActivity = (PickImageActivity) getActivity();

        return pickImageActivity.addOrRemoveFilePath(fileBean);
    }

    /**
     * 已经选择的文件路径中是否包含指定路径
     */
    public boolean containsFilePath(String filePath) {

        PickImageActivity pickImageActivity = (PickImageActivity) getActivity();

        return pickImageActivity.containsFilePath(filePath);
    }
}
