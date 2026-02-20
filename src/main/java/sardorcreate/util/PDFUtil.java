package sardorcreate.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public class PDFUtil {

    public static byte[] createPdf(byte[] qr, byte[] barcode, String assetCode) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        document.add(new Paragraph("Inventarizatsiya ID:" + assetCode));
        document.add(new Paragraph(" "));

        Image qrImage = Image.getInstance(qr);
        qrImage.scaleToFit(50, 50);
        qrImage.setAlignment(Image.ALIGN_CENTER);

        Image barcodeImage = Image.getInstance(barcode);
        barcodeImage.scaleToFit(70, 60);
        barcodeImage.setAlignment(Image.ALIGN_CENTER);

        document.add(new Paragraph("QR Kod"));
        document.add(qrImage);

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Shtrix Kod"));
        document.add(barcodeImage);

        document.close();
        return out.toByteArray();
    }
}