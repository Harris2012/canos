package cn.ranta.canos.fragment.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import cn.ranta.canos.R;
import cn.ranta.canos.activity.PickImageActivity;
import cn.ranta.canos.activity.PreviewActivity;
import cn.ranta.canos.activity.DesignActivity;
import cn.ranta.canos.engine.filter.BasicFilter;
import cn.ranta.canos.enums.RequestCode;

public class MainFragment extends Fragment {

    Uri pictureUri;

    public MainFragment() {
    }

    protected Button btn_open_camera;
    protected Button btn_open_album;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        btn_open_camera = (Button) view.findViewById(R.id.btn_open_camera);
        btn_open_album = (Button) view.findViewById(R.id.btn_open_album);

        btn_open_camera.setOnClickListener(click);
        btn_open_album.setOnClickListener(click);
    }

    private final View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.btn_open_camera: {
                    //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    //pictureUri = Uri.fromFile(getOutputImageFile());

                    //intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);

                    //startActivityForResult(intent, RequestCode.PhotoCamera.IntValue());

                    Toast.makeText(getActivity(), "相机功能暂未开发", Toast.LENGTH_SHORT).show();
                }

                break;
                case R.id.btn_open_album: {

                    Intent pickImageIntent = new Intent(getActivity(), PickImageActivity.class);

                    startActivityForResult(pickImageIntent, RequestCode.PickImage.IntValue());
                }

//                break;
//                case R.id.btn_main_test: {
//                    BasicFilter basicFilter = new BasicFilter();
//
//                    String content = basicFilter.test();
//                    if (content != null && content.length() > 0) {
//                        Toast.makeText(getActivity(),content,Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getActivity(),"this is a test",Toast.LENGTH_SHORT).show();
//                    }
//                }

                break;
                default:
                    break;
            }

            //Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (resultCode == Activity.RESULT_OK) {

            RequestCode rc = RequestCode.ValueOf(requestCode);
            switch (rc) {
                case PhotoCamera: {

                    //广播
                    Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    scanIntent.setData(pictureUri);
                    getActivity().sendBroadcast(scanIntent);

                    //to preview
                    Intent previewIntent = new Intent(getActivity(), PreviewActivity.class);
                    previewIntent.putExtra("imageUri", pictureUri.getPath());
                    startActivityForResult(previewIntent, RequestCode.Preview.IntValue());
                }

                break;
                case PickImage: {

                    ArrayList<String> filePathList = (ArrayList<String>) intent.getSerializableExtra("filePathList");
                    if (filePathList != null && filePathList.size() > 0) {

                        Intent sceneIntent = new Intent(getActivity(), DesignActivity.class);
                        sceneIntent.putExtra("filePathList", filePathList);
                        startActivityForResult(sceneIntent, RequestCode.DesignScene.IntValue());

//                        Intent previewIntent = new Intent(getActivity(), PreviewActivity.class);
//                        previewIntent.putExtra("imageUri", filePathList.get(0));
//                        startActivityForResult(previewIntent, RequestCode.Preview.IntValue());

                    } else {
                        Log.d("filePathSet.null", " is null or empty");
                    }

//                    Intent previewIntent = new Intent(getActivity(), PreviewActivity.class);
//                    previewIntent.putExtra("imageUri", imageUri);
//                    startActivityForResult(previewIntent, RequestCode.Preview.IntValue());
                }

                break;
//                case CropImage: {
//                    final Uri resultUri = UCrop.getOutput(intent);
//
//                    Bitmap bitmap = GetBitmap(resultUri, 300, 200);
//
//                    afterView.setImageBitmap(bitmap);
//
//                    //广播
//                    Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                    scanIntent.setData(resultUri);
//                    getActivity().sendBroadcast(scanIntent);
//                }
//                break;
                case Preview: {
                    Log.d("FromPreview", "This is from preview.");
                }
                break;
                default:
                    break;
            }
        }

        //super.onActivityResult(requestCode, resultCode, intent);
    }
}
