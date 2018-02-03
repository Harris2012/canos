package cn.ranta.canos.fragment.pick;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.R;
import cn.ranta.canos.activity.PickImageActivity;
import cn.ranta.canos.asynctask.LoadFolderAsyncTask;
import cn.ranta.canos.bean.ImageFolderBean;
import cn.ranta.canos.recyclerview.adapter.PickFolderAdapter;

public class PickFolderFragment extends Fragment {

    RecyclerView recyclerView;

    PickFolderAdapter pickFolderAdapter;
    private List<ImageFolderBean> beanList;

    public PickFolderFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_pick_folder, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        beanList = new ArrayList<>();

        pickFolderAdapter = new PickFolderAdapter(this, beanList);

        recyclerView = (RecyclerView) view.findViewById(R.id.pick_image_folder_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pickFolderAdapter);

        LoadFolderAsyncTask asyncTask = new LoadFolderAsyncTask(getActivity(), pickFolderAdapter);

        asyncTask.execute();
    }

    public void onViewHolderClicked(ImageFolderBean folderBean) {

        String imagePath = folderBean.getImagePath();
        File imageFile = new File(imagePath);
        String folderPath = imageFile.getParentFile().getAbsolutePath();

        PickImageActivity pickImageActivity = (PickImageActivity) getActivity();
        pickImageActivity.showPickFileFragment(folderPath);
    }
}
