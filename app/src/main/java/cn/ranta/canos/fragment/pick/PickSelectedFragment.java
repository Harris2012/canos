package cn.ranta.canos.fragment.pick;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import cn.ranta.canos.R;
import cn.ranta.canos.activity.PickImageActivity;
import cn.ranta.canos.bean.ImageFileBean;
import cn.ranta.canos.recyclerview.adapter.PickImageAdapter;
import cn.ranta.canos.recyclerview.adapter.PickSelectedAdapter;

public class PickSelectedFragment extends Fragment {

    private ArrayList<String> filePathList;

    RecyclerView recyclerView;
    Button btn_confirm;

    PickSelectedAdapter pickSelectedAdapter;

    public PickSelectedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pick_selected, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        btn_confirm = (Button) view.findViewById(R.id.btn_confirm);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        filePathList = new ArrayList<>();

        pickSelectedAdapter = new PickSelectedAdapter(this, filePathList);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pickSelectedAdapter);


        PickImageActivity pickImageActivity = (PickImageActivity) getActivity();
        btn_confirm.setOnClickListener(pickImageActivity.onConfirmButtonClicked);
    }

    public ArrayList<String> getFilePathList() {
        return filePathList;
    }

    public boolean addFilePath(String filePath) {

        if (filePathList.contains(filePath)) {

            Toast.makeText(this.getActivity(), "图片不能重复添加", Toast.LENGTH_SHORT).show();

            return false;
        }

        if (filePathList.size() >= 9) {
            Toast.makeText(this.getActivity(), "您最多可以选择9张图片", Toast.LENGTH_SHORT).show();

            return false;
        }

        filePathList.add(filePath);

        onFileAddedOrRemoved();

        return true;
    }

    public boolean removeFilePath(String filePath) {

        if (!filePathList.contains(filePath)) {

            Toast.makeText(this.getActivity(), "您没有选择过该文件", Toast.LENGTH_SHORT).show();

            return false;
        }

        filePathList.remove(filePath);

        onFileAddedOrRemoved();

        return true;
    }

    public boolean containsFilePath(String filePath) {

        return filePathList.contains(filePath);
    }

    private void onFileAddedOrRemoved() {

        if (filePathList.size() > 0) {

            btn_confirm.setEnabled(true);
            btn_confirm.setText(getResources().getString(R.string.btn_text_confirm_number, filePathList.size()));

        } else {

            btn_confirm.setEnabled(false);
            btn_confirm.setText(getResources().getString(R.string.btn_text_confirm));
        }

        pickSelectedAdapter.notifyDataSetChanged();
    }

    public boolean addOrRemoveFilePath(ImageFileBean fileBean) {

        String filePath = fileBean.getFilePath();

        if (filePathList.contains(filePath)) {

            return removeFilePath(filePath);
        } else {

            return addFilePath(filePath);
        }
    }
}
