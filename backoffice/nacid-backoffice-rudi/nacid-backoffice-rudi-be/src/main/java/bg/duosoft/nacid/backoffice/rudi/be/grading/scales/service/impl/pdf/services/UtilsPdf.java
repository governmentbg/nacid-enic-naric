package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.impl.pdf.services;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UtilsPdf {

    public static BaseFont loadFontFromClassPath(String fontPath) {
        try (InputStream fontStream = HeaderAndFooterPageEvent.class.getClassLoader().getResourceAsStream(fontPath)) {
            if (fontStream == null) {
                throw new RuntimeException("Font not found: " + fontPath);
            }

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;

            while ((length = fontStream.read(buffer)) != -1) {
                byteStream.write(buffer, 0, length);
            }

            return BaseFont.createFont(
                    fontPath,
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED,
                    false,
                    byteStream.toByteArray(),
                    null
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to load font: " + fontPath, e);
        }
    }

    public static Image loadImageFromClasPath(String imagePath) {
        try (InputStream imageStream = HeaderAndFooterPageEvent.class.getClassLoader().getResourceAsStream(imagePath)) {
            if (imageStream == null) {
                throw new RuntimeException("Image not found: " + imagePath);
            }
            byte[] imageBytes = imageStream.readAllBytes();
            return Image.getInstance(imageBytes);
        } catch (IOException | BadElementException e) {
            throw new RuntimeException("Failed to load image: " + imagePath, e);
        }
    }
}
