package com.coder4;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

public class EncoderHelper {

    public static Bitmap createQRImage(String data, int width, int height)
    {
        try
        {
            // judge data
            if (data == null || "".equals(data) || data.length() < 1)
            {
                return null;
            }
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // utf-8 encoding
            hints.put(EncodeHintType.MARGIN, 0); // default no border
            // Make matrix
            BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, width,
                    height, hints);
            int[] pixels = new int[width * height];
            // Generate image to pixels
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    if (bitMatrix.get(x, y))
                    {
                        pixels[y * width + x] = 0xff000000;
                    }
                    else
                    {
                        pixels[y * width + x] = 0xffffffff;
                    }
                }
            }
            // pixels to image
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
