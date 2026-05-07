package bg.duosoft.nacidshareddata.service.report.impl;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
public class WatermarkPageEvent extends PdfPageEventHelper {
	private static String DRAFT_IMG_BG = "/common/img/draft_bg.png";
	private static String DRAFT_IMG_EN = "/common/img/draft_en.png";

	private boolean isDraft;
	private String localeCode;
	private String resourceDir;

	public WatermarkPageEvent(String resourceDir, boolean isDraft, String localeCode) {
		super();
		this.isDraft = isDraft;
		this.localeCode = localeCode;
		this.resourceDir = resourceDir;
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		String imgResource;

		if (localeCode != null && localeCode.equalsIgnoreCase("bg")) {
			imgResource = DRAFT_IMG_BG;
		} else {
			imgResource = DRAFT_IMG_EN;
		}

		if(isDraft) {
			try (InputStream inputStream = new FileInputStream(resourceDir + imgResource);
				 ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
				BufferedImage buff = ImageIO.read(inputStream);
				ImageIO.write(buff, "png", outputStream);
				Image img = Image.getInstance(outputStream.toByteArray());
				img.setAbsolutePosition(0, 0);
				writer.getDirectContentUnder().addImage(img);
			} catch (Exception e){
				log.warn("There was a problem adding watermark", e);
			}
		}
	}
}
