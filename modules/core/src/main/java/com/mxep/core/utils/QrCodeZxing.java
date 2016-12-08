/**
 * 2014-12-22
 * model
 */
package com.mxep.core.utils;

/**
 * @author hxj
 */

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.mxep.core.utils.http.HttpUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

/**
 * java生成二维条码
 *
 * @author Administrator
 */
public class QrCodeZxing {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format "
                    + format + " to " + file);
        }
    }

    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format "
                    + format);
        }
    }

    /**
     * 创建条码
     *
     * @param imageServer 图片服务器地址
     * @param contents    条码内容
     * @param fileName    文件后缀名
     * @return
     */
    public static String createQr(String imageServer, String contents, String fileName) {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
        BitMatrix matrix = null;
        String ret = "";
        try {
            matrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.QR_CODE, 300, 300, hints);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            String url = QrCodeZxing.IMAGE_DOMAIN + "/upload?ftype=image";
            QrCodeZxing.writeToStream(matrix, "jpg", baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ret = HttpUtils.uploadMultipart(imageServer, bais, fileName, fileName + ".jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static String createBarcode(String imageServer, String contents, String fileName) {
        String ret = "";
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, 105);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.EAN_13, codeWidth, 50, null);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            String url = QrCodeZxing.IMAGE_DOMAIN + "/upload?ftype=image";
            QrCodeZxing.writeToStream(bitMatrix, "jpg", baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ret = HttpUtils.uploadMultipart(imageServer, bais, fileName, fileName + ".jpg");
            System.err.println(ret);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * CODE_128
     *
     * @param contents
     * @param fileName
     * @return
     */
    public static String createBarcode128C(String imageServer, String contents, String fileName) {
        String ret = "";
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.CODE_128, 128, 23, null);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            String url = QrCodeZxing.IMAGE_DOMAIN + "/upload?ftype=image";
            QrCodeZxing.writeToStream(bitMatrix, "jpg", baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ret = HttpUtils.uploadMultipart(imageServer, bais, fileName, fileName + ".jpg");
            System.err.println(ret);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 主函数
     */
    public static void main(String[] args) {
        createBarcode("http://test.image.ehfarm.com/upload?ftype=image", "6261228572777", 2706 + "");
    }
}

//String barCode = StringUtils.rightPad("" + 2, 8, "0")
//+ DateUtils.date2Str(new Date(), "HHmm");
//Integer i1 = 0;
//Integer i2 = 0;
//for (int i=0;i<barCode.length();i++){
//if (i/2 == 0){
//i1 += Integer.parseInt(barCode.substring(i,i+1));
//}else{
//i2 += Integer.parseInt(barCode.substring(i,i+1));
//}
//}
//Integer ret = i2*3+i1;
//System.err.println(barCode + (10 - (ret % 10)));


//String contents = "洗洗的订单编号";  
//Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
//hints.put(EncodeHintType.CHARACTER_SET, "GBK");  
//BitMatrix matrix = null;  
//try {  
//    matrix = new MultiFormatWriter().encode(contents,  
//            BarcodeFormat.QR_CODE, 300, 300, hints);  
//
//} catch (Exception e) {  
//    e.printStackTrace();  
//}  
//ByteArrayOutputStream baos = new ByteArrayOutputStream();
//String url = "http://192.168.1.180:88/upload?ftype=image";
//try {  
//	QrCodeZxing.writeToStream(matrix, "jpg", baos);
//	ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
//	String ret = HttpUtils.uploadMultipart(url,bais,"hxj","hxj.jpg");
//	System.err.println(ret);
////	File file = new File("C:/qrcodeImage.png");
////	QrCodeZxing.writeToFile(matrix, "png", file);  
//} catch (IOException e) {  
//    e.printStackTrace();  
//} 
