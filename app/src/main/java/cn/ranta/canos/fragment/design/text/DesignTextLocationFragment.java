package cn.ranta.canos.fragment.design.text;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ranta.canos.R;

public class DesignTextLocationFragment extends DesignTextBaseFragment {

    public DesignTextLocationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_design_text_location, container, false);
    }

}
