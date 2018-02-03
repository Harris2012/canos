package cn.ranta.canos.activity;

import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import cn.ranta.canos.R;
import cn.ranta.canos.bean.BgColorBean;
import cn.ranta.canos.bean.BgImageBean;
import cn.ranta.canos.fragment.design.DesignModeFilterFragment;
import cn.ranta.canos.fragment.design.DesignSceneFragment;
import cn.ranta.canos.fragment.design.bg.DesignBgMainFragment;
import cn.ranta.canos.fragment.design.menu.DesignMenuFragment;
import cn.ranta.canos.fragment.design.text.DesignTextMainFragment;
import cn.ranta.canos.utility.StorageUtility;

public class DesignActivity extends AppCompatActivity {

    DesignMenuFragment designMenuFragment;

    DesignBgMainFragment designBgMainFragment;
    DesignTextMainFragment designTextMainFragment;

    DesignSceneFragment designSceneFragment;
    DesignModeFilterFragment designModeFilterFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_design);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_design);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        ArrayList<String> filePathList = (ArrayList<String>) getIntent().getSerializableExtra("filePathList");

        Bundle bundle = new Bundle();
        bundle.putSerializable("filePathList", filePathList);

        designMenuFragment = new DesignMenuFragment();
        designBgMainFragment = new DesignBgMainFragment();
        designTextMainFragment = new DesignTextMainFragment();

        designModeFilterFragment = new DesignModeFilterFragment();
        designSceneFragment = new DesignSceneFragment();
        designSceneFragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .add(R.id.container_design, designSceneFragment)
                .add(R.id.container_design_menu, designMenuFragment)
                //.add(R.id.container_design_background, designBgMainFragment)
                //.add(R.id.container_design_border, designModeBorderFragment)
                //.add(R.id.container_design_toolbar_filter, designModeFilterFragment)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_save, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.btn_menu_save) {

            File targetFile = StorageUtility.getOutputFile(this);

            designSceneFragment.SaveAs(targetFile);

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setBgImage(BgImageBean bgImageBean) {

        designSceneFragment.setBgImage(bgImageBean);
    }

    public void setBgColor(BgColorBean bgColorBean) {

        designSceneFragment.setBgColor(bgColorBean);
    }

    public void showBgMainFragment() {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.hide(designMenuFragment);

        if (designBgMainFragment.getId() == 0) {
            fragmentTransaction.add(R.id.container_design_background, designBgMainFragment);
        } else {
            fragmentTransaction.show(designBgMainFragment);
        }

        fragmentTransaction.commit();
    }

    public void hideBgMainFragment() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.hide(designBgMainFragment);
        fragmentTransaction.show(designMenuFragment);

        fragmentTransaction.commit();
    }

    public void showTextFragment() {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.hide(designMenuFragment);

        if (designTextMainFragment.getId() == 0) {
            fragmentTransaction.add(R.id.container_design_text, designTextMainFragment);
        } else {
            fragmentTransaction.show(designTextMainFragment);
        }

        fragmentTransaction.commit();
    }

    public void hideTextFragment() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.hide(designTextMainFragment);
        fragmentTransaction.show(designMenuFragment);

        fragmentTransaction.commit();
    }
}
