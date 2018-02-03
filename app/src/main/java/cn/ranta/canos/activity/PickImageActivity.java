package cn.ranta.canos.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cn.ranta.canos.R;
import cn.ranta.canos.bean.ImageFileBean;
import cn.ranta.canos.fragment.pick.PickFolderFragment;
import cn.ranta.canos.fragment.pick.PickFileFragment;
import cn.ranta.canos.fragment.pick.PickSelectedFragment;

public class PickImageActivity extends AppCompatActivity {

    PickFolderFragment pickFolderFragment;
    PickFileFragment pickFileFragment;
    PickSelectedFragment pickSelectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pick_image);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_pick_image_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        pickFolderFragment = new PickFolderFragment();
        pickFileFragment = new PickFileFragment();
        pickSelectedFragment = new PickSelectedFragment();

        getFragmentManager().beginTransaction()
                .add(R.id.container_pickimage_select, pickFolderFragment)
                .add(R.id.container_pickimage_selected, pickSelectedFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btn_menu_back: {

                FragmentManager fragmentManager = getFragmentManager();
                int count = fragmentManager.getBackStackEntryCount();
                if (count > 0) {
                    fragmentManager.popBackStack();
                } else {
                    this.finish();
                }
            }
            break;
            default:
                break;
        }

        return true;
    }

    // region Getter
    public PickFolderFragment getPickFolderFragment() {
        return pickFolderFragment;
    }

    public PickFileFragment getPickFileFragment() {
        return pickFileFragment;
    }

    public PickSelectedFragment getPickSelectedFragment() {
        return pickSelectedFragment;
    }
    // endregion

    /**
     * 点击确定
     */
    public final View.OnClickListener onConfirmButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent();
            intent.putExtra("filePathList", pickSelectedFragment.getFilePathList());

            setResult(Activity.RESULT_OK, intent);

            finish();
        }
    };

    // region Public Methods

    /**
     * 点击选择获取取消图片
     *
     * @param fileBean
     * @return若操作成功，返回true，否则返回false
     */
    public boolean addOrRemoveFilePath(ImageFileBean fileBean) {

        return pickSelectedFragment.addOrRemoveFilePath(fileBean);
    }

    /**
     * 已经选择的文件路径中是否包含指定路径
     */
    public boolean containsFilePath(String filePath) {

        return pickSelectedFragment.containsFilePath(filePath);
    }

    public void showPickFileFragment(String folderPath){

        Bundle bundle = new Bundle();
        bundle.putString("folderPath", folderPath);

        pickFileFragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_pickimage_select, pickFileFragment)
                .addToBackStack(null)
                .commit();
    }
    // endregion
}
