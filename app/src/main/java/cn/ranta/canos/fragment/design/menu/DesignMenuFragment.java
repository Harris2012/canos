package cn.ranta.canos.fragment.design.menu;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.ranta.canos.R;
import cn.ranta.canos.activity.DesignActivity;

public class DesignMenuFragment extends Fragment {

    Button btnBackground;
    Button btnText;

    public DesignMenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_design_menu, container, false);

        btnBackground = (Button) view.findViewById(R.id.btn_design_toolbar_background);
        btnText = (Button) view.findViewById(R.id.btn_design_toolbar_text);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        btnBackground.setOnClickListener(click);
        btnText.setOnClickListener(click);
    }

    private final View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            DesignActivity designActivity = (DesignActivity) getActivity();

            switch (view.getId()) {
                case R.id.btn_design_toolbar_background: {

                    designActivity.showBgMainFragment();
                }

                break;
                case R.id.btn_design_toolbar_text: {

                    designActivity.showTextFragment();
                }

                break;
                default:
                    break;
            }

        }
    };
}
