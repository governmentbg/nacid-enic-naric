package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.impl.pdf.services;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;

import java.awt.*;


public class HeaderAndFooterPageEvent extends PdfPageEventHelper {
    private final Image headerImage;
    private final Font headerFont;
    private final Color accentColor = new Color(51, 122, 183);

    public HeaderAndFooterPageEvent() {
        try {
            headerImage = UtilsPdf.loadImageFromClasPath("image/footer-nacid.png");
            headerImage.scaleToFit(60, 60);

            BaseFont baseFont = UtilsPdf.loadFontFromClassPath("fonts/TimesNewRoman_font.ttf");
            headerFont = new Font(baseFont, 8, Font.NORMAL, accentColor);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize header resources", e);
        }
    }

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        float pageWidth = document.right() - document.left();
        float centerX = document.left() + pageWidth / 2;

        try {
            float imageY = document.top() + document.topMargin() - headerImage.getScaledHeight();
            headerImage.setAbsolutePosition(centerX - headerImage.getScaledWidth() / 2, imageY);
            cb.addImage(headerImage);

            Phrase phrase1 = new Phrase("Национален център за", headerFont);
            Phrase phrase2 = new Phrase("информация и документация", headerFont);

            float textBaseY = imageY - 5;

            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, phrase1, centerX, textBaseY, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, phrase2, centerX, textBaseY - 10, 0);

            cb.setColorStroke(accentColor);
            cb.setLineWidth(1f);
            float lineY = textBaseY - 20;
            cb.moveTo(document.left(), lineY);
            cb.lineTo(document.right(), lineY);
            cb.stroke();

        } catch (DocumentException e) {
            throw new RuntimeException("Error rendering header", e);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        cb.setColorStroke(accentColor);
        cb.setLineWidth(1f);
        float lineY = document.bottom() + 5;
        cb.moveTo(document.left(), lineY);
        cb.lineTo(document.right(), lineY);
        cb.stroke();
    }
}
