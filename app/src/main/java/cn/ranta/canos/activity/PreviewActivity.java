package cn.ranta.canos.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import cn.ranta.canos.R;

public class PreviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        String imageUri = getIntent().getStringExtra("imageUri");

        Bundle bundle = new Bundle();
        bundle.putString("imageUri", imageUri);

//        PreviewFilterFragment previewFragment = new PreviewFilterFragment();
//        previewFragment.setArguments(bundle);

//        PreviewBorderFragment previewFragment = new PreviewBorderFragment();
//        previewFragment.setArguments(bundle);

        //getFragmentManager().beginTransaction().add(R.id.preview_container, previewFragment).commit();
    }
}
