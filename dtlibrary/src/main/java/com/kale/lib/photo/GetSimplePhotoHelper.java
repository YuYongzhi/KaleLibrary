package com.kale.lib.photo;


import com.kale.lib.utils.BitmapUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;

/**
 * @author Jack Tony
 *         从相册或者从照相机得到一个图片，没有裁剪功能
 *         <code>
 * @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
 * super.onActivityResult(requestCode, resultCode, data);
 * imageView.setImageBitmap(task.getSelectedPhoto(resultCode, data));
 * }
 * </code>
 * @date 2015/4/24
 */
public class GetSimplePhotoHelper {

    public enum FROM_WAY {
        FROM_ALBUM, FROM_CAMERA;
    }

    private Activity mActivity;

    public static final int Get_PHOTO_RESULT_OK = 4603;

    private String mPicFilePath;

    private FROM_WAY mFromWay;

    public GetSimplePhotoHelper(Activity activity) {
        mActivity = activity;
    }

    /**
     * 从相册或照相机获得一张图片
     *
     * @param way         获取图片的途径
     * @param picFilePath 如果需要保存从相机拍摄的图片，请指定保存图片的全部路径(通过相机拍照时才有效)
     *                    eg:GetPhotoHelper.choicePhoto(GetPhotoHelper.FROM_WAY.FROM_CAMERA, Environment.getExternalStorageDirectory()+ "/temp.jpg");
     */
    public void choicePhoto(FROM_WAY way, String picFilePath) {
        mFromWay = way;
        mPicFilePath = picFilePath;
        if (way == FROM_WAY.FROM_ALBUM) {
            choicePhotoFromAlbum();
        } else if (way == FROM_WAY.FROM_CAMERA) {
            choicePhotoFromCamera(picFilePath);
        }
    }

    /**
     * 启动相册的activity
     */
    private void choicePhotoFromAlbum() {
        Intent intent = new Intent(mActivity, GetSimplePhotoActivity.class);
        intent.putExtra(GetSimplePhotoActivity.KEY_FROM_WAY, GetSimplePhotoActivity.VALUE_FROM_ALBUM);
        mActivity.startActivityForResult(intent, 0);
    }

    /**
     * 启动相机的activity
     */
    private void choicePhotoFromCamera(String picFilePath) {
        Intent intent = new Intent(mActivity, GetSimplePhotoActivity.class);
        intent.putExtra(GetSimplePhotoActivity.KEY_FROM_WAY, GetSimplePhotoActivity.VALUE_FROM_CAMERA);
        intent.putExtra(GetSimplePhotoActivity.KEY_PHOTO_PATH, picFilePath);
        mActivity.startActivityForResult(intent, 0);
    }

    

    /**
     * 得到已经选择好的图片，这个方法必须在onActivityResult中进行回调
     *
     * @return 已经选择好的bitmap
     */
    public SimplePhoto getSelectedPhoto(int resultCode, Intent data) {
        SimplePhoto photo = new SimplePhoto();
        if (resultCode == GetSimplePhotoHelper.Get_PHOTO_RESULT_OK) {
            Uri uri = data.getParcelableExtra(GetSimplePhotoActivity.KEY_CHOICE_PHOTO_OK_URI);
            //Logger.d("uri = " + uri);
            Bitmap bitmap = BitmapFactory.decodeFile(uri.toString());
           // Logger.d("方向 =" + GetSimplePhotoUtil.getOrientation(uri));
            if (bitmap != null && uri != null) {
                bitmap = BitmapUtil.rotateBitmap(bitmap, GetSimplePhotoUtil.getPhotoDegreeByUri(uri));
            }

            photo.bitmap = bitmap;
            photo.uri = uri;
            photo.degree = GetSimplePhotoUtil.getPhotoDegreeByUri(uri);
            
            // 如果来源是相机，而且没有指定图片保存的目录，那么使用完毕后就立刻删除相片
            if (mFromWay == FROM_WAY.FROM_CAMERA && mPicFilePath == null) {
                File tempPicFile = new File(uri.toString());
                if (tempPicFile != null) {
                    tempPicFile.delete();//设置成功后清除之前的照片文件
                }
            }
        }
        return photo;
    }
    

}
