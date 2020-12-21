package com.ocean.supplier.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.ocean.supplier.R;

import static com.ocean.supplier.tools.Utils.createQRCodeBitmap;


/**
 * 驳回操作单原因
 */
public class QrCodeDialog extends Dialog {


    ImageView iv_qrcode;
    private Context context;
    private String content;

    public QrCodeDialog(Context context) {
        super(context);
    }

    public QrCodeDialog(Context context, int theme, String content) {
        super(context, theme);
        this.context = context;
        this.content = content;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_qr_code);
        initView();
    }

    private void initView() {
        iv_qrcode=findViewById(R.id.iv_qrcode);
//        Bitmap qrCodeBitmap = Utils.createQRCodeBitmap(content, 300, 300, "UTF-8", "H", "1", Color.GREEN, WHITE);
        Bitmap qrCodeBitmap = createQRCodeBitmap(content);
    iv_qrcode.setImageBitmap(qrCodeBitmap);

    }

}
