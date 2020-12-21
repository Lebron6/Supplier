package com.ocean.supplier.tools;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by James on 2020/8/17.
 */
public class Utils {
    public static String stringToKeep2Point(String values){
        Double d= Double.parseDouble(values);
        DecimalFormat df = new DecimalFormat("0.00");
        String s = df.format(d);
        return s;
    }
    public static Bitmap createQRCodeBitmap(String content) {
        // 鐢ㄤ簬璁剧疆QR浜岀淮鐮佸弬鏁?
        Hashtable<EncodeHintType, Object> qrParam = new Hashtable<>();
        // 璁剧疆QR浜岀淮鐮佺殑绾犻敊绾у埆鈥斺€旇繖閲岄€夋嫨鏈€楂楬绾у埆
        qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 璁剧疆缂栫爜鏂瑰紡
        qrParam.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        // 鐢熸垚QR浜岀淮鐮佹暟鎹€斺€旇繖閲屽彧鏄緱鍒颁竴涓敱true鍜宖alse缁勬垚鐨勬暟缁?
        // 鍙傛暟椤哄簭鍒嗗埆涓猴細缂栫爜鍐呭锛岀紪鐮佺被鍨嬶紝鐢熸垚鍥剧墖瀹藉害锛岀敓鎴愬浘鐗囬珮搴︼紝璁剧疆鍙傛暟
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE,360, 360, qrParam);

            // 寮€濮嬪埄鐢ㄤ簩缁寸爜鏁版嵁鍒涘缓Bitmap鍥剧墖锛屽垎鍒涓洪粦鐧戒袱鑹?
            int w = bitMatrix.getWidth();
            int h = bitMatrix.getHeight();
            int[] data = new int[w * h];

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (bitMatrix.get(x, y))
                        data[y * w + x] = 0xff000000;// 榛戣壊
                    else
                        data[y * w + x] = 0x00ffffff;// -1 鐩稿綋浜?xffffffff 鐧借壊
                }
            }

            // 鍒涘缓涓€寮燽itmap鍥剧墖锛岄噰鐢ㄦ渶楂樼殑鍥剧墖鏁堟灉ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            // 灏嗕笂闈㈢殑浜岀淮鐮侀鑹叉暟缁勪紶鍏ワ紝鐢熸垚鍥剧墖棰滆壊
            bitmap.setPixels(data, 0, w, 0, 0, w, h);

            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatTimeS(long seconds) {
        int temp = 0;
        StringBuffer sb = new StringBuffer();
        if (seconds > 3600) {
            temp = (int) (seconds / 3600);
            sb.append((seconds / 3600) < 10 ? "0" + temp + ":" : temp + ":");
            temp = (int) (seconds % 3600 / 60);
            changeSeconds(seconds, temp, sb);
        } else {
            temp = (int) (seconds % 3600 / 60);
            changeSeconds(seconds, temp, sb);
        }
        return sb.toString();
    }
    private static void changeSeconds(long seconds, int temp, StringBuffer sb) {
        sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");
        temp = (int) (seconds % 3600 % 60);
        sb.append((temp < 10) ? "0" + temp : "" + temp);
    }
        /**
         * 判断是否是手机号码
         */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        }
        else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        java.text.SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res.substring(0,res.length()-3);
    }
}
