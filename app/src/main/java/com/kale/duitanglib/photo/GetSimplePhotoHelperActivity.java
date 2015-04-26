package com.kale.duitanglib.photo;

import com.kale.lib.activity.KaleBaseActivity;
import com.kale.lib.photo.GetSimplePhotoHelper;
import com.kale.lib.photo.SimplePhoto;
import com.orhanobut.logger.Logger;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * @author Jack Tony
 * @brief
 * @date 2015/4/26
 */
public class GetSimplePhotoHelperActivity extends KaleBaseActivity {

    GetSimplePhotoHelper mPhotoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        Uri uri = photo.uri;
        Logger.d(uri.toString());
        Logger.d(photo.degree + "");
        
        /*ImageView view = getView(R.id.imageView);
        view.setImageBitmap(photo.bitmap);*/
    }
}
