package com.kale.duitanglib.photo;

import com.kale.duitanglib.R;
import com.kale.lib.activity.KaleBaseActivity;
import com.kale.lib.photo.GetSimplePhotoHelper;
import com.kale.lib.photo.SimplePhoto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * @author Jack Tony
 * @brief
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
 * @date 2015/4/26
 */
public class GetSimplePhotoHelperActivity extends KaleBaseActivity {

    GetSimplePhotoHelper mPhotoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.get_simplephoto_helper_activity_main);
        
        // 去选择图片
        mPhotoHelper = new GetSimplePhotoHelper(this);
        mPhotoHelper.choicePhoto(GetSimplePhotoHelper.FROM_WAY.FROM_ALBUM, null);
        //mPhotoHelper.choicePhoto(GetSimplePhotoHelper.FROM_WAY.FROM_CAMERA, null);
        //mPhotoHelper.choicePhoto(GetSimplePhotoHelper.FROM_WAY.FROM_CAMERA, Environment.getExternalStorageDirectory()+ "/my_temp.jpg");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 通过回调得到图片

        SimplePhoto photo = mPhotoHelper.getSelectedPhoto(resultCode, data);
        ImageView imageView = getView(R.id.imageView);
        if (photo != null) {

            imageView.setImageBitmap(photo.bitmap);
            Uri uri = photo.uri;
            /*Logger.d("uri = " + uri);
            Logger.d(photo.degree + "");*/
        }
        
    }
}
