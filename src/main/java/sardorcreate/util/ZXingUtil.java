package sardorcreate.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;

public class ZXingUtil {

    public static byte[] generatePdf(String assetCode, String str) throws Exception {

        byte[] qr = generateQr(assetCode, str);
        byte[] barcode = generateBarcode(assetCode, str);

        return PDFUtil.createPdf(qr, barcode, assetCode);
    }

    public static byte[] generateQr(String assetCode, String str) throws Exception {
        BitMatrix matrix = new QRCodeWriter()
                .encode("http://10.199.132.181:8080/api/" + str + "/get/" + assetCode, BarcodeFormat.QR_CODE, 50, 50);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", out);
        return out.toByteArray();
    }

    public static byte[] generateBarcode(String assetCode, String str) throws Exception {
        BitMatrix matrix = new Code128Writer()
                .encode("http://10.199.132.181:8080/api/" + str +"/get/" + assetCode, BarcodeFormat.CODE_128, 70, 60);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", out);
        return out.toByteArray();
    }
}
