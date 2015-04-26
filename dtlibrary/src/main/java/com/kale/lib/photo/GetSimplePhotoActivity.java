package com.kale.lib.photo;


import com.kale.lib.utils.IntentUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;

/**
 * @author Jack Tony
 * @brief
 * @date 2015/4/24
 */
public class GetSimplePhotoActivity extends Activity {

    private class RequestCode {

        public static final int ALBUM_OK = 1, ALBUM_OK_KITKAT = 2, CAMERA_OK = 3;
    }

    /**
     * 准备通过什么样的方式来获取图片
     */
    public static final String KEY_FROM_WAY = "key_from_way";

    /**
     * 图片的全部路径
     */
    public static final String KEY_PHOTO_PATH = "key_photo_path";

    /**
     * 裁剪好的图片uri
     */
    public static final String KEY_CHOICE_PHOTO_OK_URI = "key_crop_photo_uri";


    public static final int VALUE_FROM_ALBUM = 54345;

    public static final int VALUE_FROM_CAMERA = 46632;

    public static final String TEMP_PHOTO_FILE_NAME = "kale_temp_photo.jpg";

    private File tempPicFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!IntentUtil.isBundleEmpty(getIntent())) {
            Bundle bundle = getIntent().getExtras();
            if (bundle.getInt(KEY_FROM_WAY, VALUE_FROM_ALBUM) == VALUE_FROM_ALBUM) {
                // 进行版本判断 see:http://blog.csdn.net/tempersitu/article/details/20557383
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    GetSimplePhotoUtil.choicePicFromAlbum_kitkat(this, RequestCode.ALBUM_OK_KITKAT);
                } else {
                    // 4.4以下
                    GetSimplePhotoUtil.choicePicFromAlbum(this, RequestCode.ALBUM_OK);
                }
            } else {
                if (bundle.getString(KEY_PHOTO_PATH) == null) {
                    // 照相得到的图片默认的保存路径，用完后会自动删除
                    tempPicFile = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
                } else {
                    // 自定义照相得到的图片的保存路径，不会自动删除
                    tempPicFile = new File(bundle.getString(KEY_PHOTO_PATH));
                }
                tempPicFile.delete();// 清空之前的文件
                GetSimplePhotoUtil.choicePicFromCamera(this, tempPicFile, RequestCode.CAMERA_OK);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri;
        switch (requestCode) {
            // 如果是直接从相册获取
            case RequestCode.ALBUM_OK:
                //从相册中获取到图片
                if (data != null) {
                    uri = data.getData();
                    finishAndReturnBitmap(data, uri);
                } else {
                    finish();
                }
                break;
            case RequestCode.ALBUM_OK_KITKAT:
                if (data != null) {
                    uri = Uri.parse(GetSimplePhotoUtil.getPath(this, data.getData()));
                    finishAndReturnBitmap(data, uri);
                } else {
                    finish();
                }
                break;
            // 如果是调用相机拍照时
            case RequestCode.CAMERA_OK:
                // 当拍照到照片时操作
                if (tempPicFile.exists()) {
                    uri = Uri.parse(tempPicFile.getAbsolutePath());
                    finishAndReturnBitmap(new Intent(), uri);
                } else {
                    finish();
                }
                break;
            default:
                break;
        }
    }

    public Bitmap finishAndReturnBitmap(Intent data, Uri uri) {
        //Logger.t(TAG).d("uri =" + uri);
        //设置返回数据
        data.putExtra(KEY_CHOICE_PHOTO_OK_URI, uri);
        setResult(GetSimplePhotoHelper.Get_PHOTO_RESULT_OK, data);//设置给之前启动它的activity的一个返回码
        finish();
        return null;
    }
}
