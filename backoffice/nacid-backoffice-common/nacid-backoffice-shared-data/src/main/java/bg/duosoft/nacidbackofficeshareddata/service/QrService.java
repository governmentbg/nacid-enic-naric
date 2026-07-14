package bg.duosoft.nacidbackofficeshareddata.service;

import com.google.zxing.EncodeHintType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

/**
 * User: Georgi
 * Date: 6.7.2020 г.
 * Time: 13:51
 */
@Service
public class QrService {

    public byte[] generateQRCodeImage(String barcodeText, Integer height, Integer width) {
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(width, height)
                .withHint(EncodeHintType.MARGIN, 1)
                .stream();
        return stream.toByteArray();
    }
}
