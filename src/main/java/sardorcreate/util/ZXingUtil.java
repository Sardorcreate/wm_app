package sardorcreate.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ZXingUtil {

    public static byte[] generateZip(String assetCode, String str) throws Exception {

        byte[] qr = generateQr(assetCode, str);          // PNG bytes
        byte[] barcode = generateBarcode(assetCode, str); // PNG bytes

        Map<String, byte[]> files = new HashMap<>();
        files.put(assetCode + "_qr.png", qr);
        files.put(assetCode + "_barcode.png", barcode);

        return ZipUtil.zipImages(files);
    }

    public static byte[] generateQr(String assetCode, String str) throws Exception {
        BitMatrix matrix = new QRCodeWriter()
                .encode("http://10.32.79.181:8080/api/" + str + "/get/" + assetCode, BarcodeFormat.QR_CODE, 300, 300);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", out);
        return out.toByteArray();
    }

    public static byte[] generateBarcode(String assetCode, String str) throws Exception {
        BitMatrix matrix = new Code128Writer()
                .encode("http://10.32.79.181:8080/api/" + str +"/get/" + assetCode, BarcodeFormat.CODE_128, 400, 150);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", out);
        return out.toByteArray();
    }
}
