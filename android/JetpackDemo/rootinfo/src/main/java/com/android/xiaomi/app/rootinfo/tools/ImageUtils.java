package com.android.xiaomi.app.rootinfo.tools;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public final class ImageUtils {

    public static Bitmap createBitmapFromDrawable(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();

        if ((intrinsicWidth <= 0) || (intrinsicHeight <= 0)) {
            return null;
        }
        Bitmap mBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mBitmap);
        drawable.setBounds(0, 0, mCanvas.getWidth(), mCanvas.getHeight());
        drawable.draw(mCanvas);
        return mBitmap;
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        return bitmap2Bytes(bitmap, false);
    }

    public static byte[] bitmap2Bytes(final Bitmap bmp, final boolean needRecycle) {
        return bitmap2BytesScaled(bmp, needRecycle, true, 100);
    }

    public static byte[] bitmap2BytesScaled(final Bitmap bmp, final boolean needRecycle,
                                            boolean isLossless, int quality) {
        if (bmp == null) {
            return null;
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        if (isLossless) {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        }
        else {
            bmp.compress(Bitmap.CompressFormat.JPEG, quality, output);
        }
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
        }

        return result;
    }

    public static Bitmap bytes2Bitmap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return null;
    }

    /**
     * 根据路径获取图片并压缩
     *
     * @param srcPath
     * @return bitmap
     */
    public static Bitmap bitmapCompress(String srcPath) {
        // 现在主流手机比较多是800*480分辨率， 默认这个分辨率
        return bitmapCompress(srcPath, 480, 800);
    }

    /**
     * 根据路径获取图片并压缩
     *
     * @param srcPath
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap bitmapCompress(String srcPath, int reqWidth, int reqHeight) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;

        newOpts.inSampleSize = calculateInSampleSize(newOpts, reqWidth, reqHeight);// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }

    /**
     * 图片按比例大小压缩方法
     *
     * @param image
     * @return bitmap
     */
    public static Bitmap compressForWatchHead(Bitmap image) {
        final int srcWidth = image.getWidth();
        final int srcHeight = image.getHeight();
        if (srcWidth < 500 && srcHeight < 500) {
            return image; // no scale
        }

        int destWidth = 400; // px
        int destHeight = 400; // px
        // keep ratio and limit max size
        if (srcWidth > srcHeight) {
            destHeight = destWidth * srcHeight / srcWidth;
        } else if (srcWidth < srcHeight) {
            destWidth = destHeight * srcWidth / srcHeight;
        }

        return Bitmap.createScaledBitmap(image, destWidth, destHeight, true);
    }

    /**
     * 图片按质量压缩方法
     *
     * @param image
     * @return bitmap
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 10) { // 循环判断如果压缩后图片是否大于10kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 根据传过来的宽高,计算压缩比例
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return size
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
        }
        return inSampleSize;
    }

    /**
     * 按照指定的寬高加载图片
     *
     * @param filePath
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromFile(String filePath, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap decodeSampledBitmapFromStream(InputStream stream, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(stream, null, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(stream, null, options);
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            left = 0;
            top = 0;
            right = width;
            bottom = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(roundPx, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);

        return output;
    }

    public static Bitmap createDrawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                return bitmap;
            }
        }
        return ImageUtils.createBitmapFromDrawable(drawable);
    }

    public static Bitmap getBitmapFromView(ImageView view) {
        Bitmap bitmap = null;
        if (view != null) {
            Drawable drawable = view.getDrawable();
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            }
        }
        return bitmap;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                    drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        }
    }
}
