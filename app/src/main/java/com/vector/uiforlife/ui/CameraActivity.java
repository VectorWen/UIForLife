package com.vector.uiforlife.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.vector.uiforlife.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class CameraActivity extends BaseActivity {

    private static final int choose_picture = 0;
    private static final int choose_camera = 1;

    private File pictureFile;
    private Bitmap mBitmap; //选择到的图片
    private byte[] mBitmapData;; //图片压缩后的数据

    private ViewHolder mViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity);
        mViewHolder = new ViewHolder(getWindow().getDecorView());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK){
            return;
        }
        String fileSrc = null;
        if(requestCode == choose_camera){
            fileSrc = pictureFile.getAbsolutePath();
        }
        if(requestCode == choose_picture){
            mBitmap = data.getParcelableExtra("data");
            if(mBitmap!=null){
                //这里是截图的代码
                crop(data);
                return;
            }else if ("file".equals(data.getData().getScheme())) {
                // 有些低版本机型返回的Uri模式为file
                fileSrc = data.getData().getPath();
            } else {
                // Uri模型为content
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(data.getData(), proj,
                        null, null, null);
                cursor.moveToFirst();
                int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                fileSrc = cursor.getString(idx);
                cursor.close();
            }
        }

        // 获取图片的宽和高
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        mBitmap = BitmapFactory.decodeFile(fileSrc, options);

        // 压缩图片
        options.inSampleSize = Math.max(1, (int) Math.ceil(Math.max(
                (double) options.outWidth / 1024f,
                (double) options.outHeight / 1024f)));
        options.inJustDecodeBounds = false;
        mBitmap = BitmapFactory.decodeFile(fileSrc, options);

        // 部分手机会对图片做旋转，这里检测旋转角度
        int degree = readPictureDegree(fileSrc);
        if (degree != 0) {
            // 把图片旋转为正的方向
            mBitmap = rotateImage(degree, mBitmap);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //可根据流量及网络状况对图片进行压缩
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        mBitmapData = baos.toByteArray();
        mViewHolder.mImageView.setImageBitmap(mBitmap);
    }

    private void crop(Intent data) {
        mBitmap = data.getParcelableExtra("data");//我们就拿到了我们想要的bitmap
        // 部分手机会对图片做旋转，这里检测旋转角度
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //可根据流量及网络状况对图片进行压缩
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        mBitmapData = baos.toByteArray();
        mViewHolder.mImageView.setImageBitmap(mBitmap);
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path
     *            图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotateImage(int angle, Bitmap bitmap) {
        // 图片旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 得到旋转后的图片
        Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBitmap;
    }

    /**
     */
    class ViewHolder {

        @InjectView(R.id.image)
        ImageView mImageView;

        @OnClick(R.id.picture)
        void pictureClick(View view){

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            intent.putExtra("crop", "true");
            // 裁剪框的比例，1：1
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // 裁剪后输出图片的尺寸大小
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("noFaceDetection", true);// 取消人脸识别

            startActivityForResult(intent, choose_picture);
        }
        @OnClick(R.id.camera)
        void cameraClick(View view){
        // 设置相机拍照后照片保存路径
            pictureFile = new File(Environment.getExternalStorageDirectory(),
                    "picture" + System.currentTimeMillis()/1000 + ".jpg");
            // 启动拍照,并保存到临时文件
            Intent mIntent = new Intent();
            mIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            mIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(pictureFile));
            mIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
            startActivityForResult(mIntent, choose_camera);
        }

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
