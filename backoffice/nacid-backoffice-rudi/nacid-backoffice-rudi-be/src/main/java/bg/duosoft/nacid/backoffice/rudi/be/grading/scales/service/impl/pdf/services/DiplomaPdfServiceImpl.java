package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.impl.pdf.services;


import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request.DiplomaDetailsDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.EqualizationSubjectDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.CountriesService;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.DiplomaPdfService;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.GradesEqualizationService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiplomaPdfServiceImpl implements DiplomaPdfService {

    private final CountriesService countriesService;
    private final GradesEqualizationService gradesEqualizationService;


    @Override
    public ResponseEntity<byte[]> createPdfFile(DiplomaDetailsDto diplomaDetailsDto) {


        String fileName = "newPdf.pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-file-name", fileName);
        headers.set("Content-Disposition", String.format("%s;filename*=UTF-16''%s;filename=\"%s\"", "attachment", fileName, fileName));
        headers.set("Access-Control-Expose-Headers", "x-file-name");
        headers.set("Access-Control-Allow-Headers", "x-file-name");
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pagePrepare(diplomaDetailsDto), headers, HttpStatus.OK);


    }

    private byte[] pagePrepare(DiplomaDetailsDto diplomaDetailsDto) {

        String countryName = countriesService.getCountryByCountryCode(diplomaDetailsDto.getCountryCode()).getNameBg();
        String year = String.valueOf(diplomaDetailsDto.getYear());
        List<EqualizationSubjectDto> equalizationSubjects = gradesEqualizationService.gradeEqualization(diplomaDetailsDto);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 85, 36);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setPageEvent(new HeaderAndFooterPageEvent());
            document.open();

            BaseFont baseFont = UtilsPdf.loadFontFromClassPath("fonts/TimesNewRoman_font.ttf");

            Font fontTitle = new Font(baseFont, 25, Font.BOLD);
            Font fontRegular = new Font(baseFont, 14, Font.NORMAL);
            Font fontItalic = new Font(baseFont, 16, Font.ITALIC);
            Font fontTableBold = new Font(baseFont, 10, Font.BOLD);
            Font fontTableItalic = new Font(baseFont, 10, Font.ITALIC);

            Paragraph paragraph = new Paragraph("Д И П Л О М А", fontTitle);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingBefore(20f);
            document.add(paragraph);

            document.add(centeredPara("№ ", "2587", fontRegular, fontItalic));
            document.add(spacedCenteredPara("Студент: ", "John Miller", fontRegular, fontItalic, 10f));
            document.add(centeredPara("Издадена от: ", countryName, fontRegular, fontItalic));
            document.add(centeredPara("Година на издаване: ", year, fontRegular, fontItalic));
            document.add(spacedCenteredPara("Учебно заведение: ", "Business High School", fontRegular, fontItalic, 0f));

            String[] headers = {"№", "Учебен предмет", "Оценка", "Приравнена оценка"};
            int[] columnWidths = {1, 4, 2, 3};

            Table table = new Table(4);
            table.setWidth(100);
            table.setWidths(columnWidths);
            table.setPadding(3);

            for (String header : headers) {
                Cell cell = new Cell(new Paragraph(header, fontTableBold));
                cell.setGrayFill(0.9f);
                cell.setHeader(true);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            int count = 0;
            double sumOfGrades = 0;

            for (EqualizationSubjectDto subject : equalizationSubjects) {
                count++;
                Double gradeBg = subject.getSubjectGradeBg();
                sumOfGrades += (gradeBg != null) ? gradeBg : 0;

                String[] cellTexts = {
                        count + ".",
                        subject.getSubjectName(),
                        subject.getSubjectGrade(),
                        (gradeBg != null) ? String.format("%s %.2f", subject.getSubjectGradeBgText(), gradeBg) : "Няма съответствие"
                };

                for (String text : cellTexts) {
                    Cell cell = new Cell(new Paragraph(text, fontTableItalic));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
            }

            document.add(table);

            double averageGrade = (count > 0) ? sumOfGrades / count : 0;
            Paragraph avgParagraph = new Paragraph(
                    String.format("Среден успех:   %s %.2f", getGradeText(averageGrade), averageGrade),
                    new Font(baseFont, 12, Font.ITALIC)
            );
            avgParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(avgParagraph);

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Error generating diploma PDF", e);
        }

        return outputStream.toByteArray();
    }


    private Paragraph centeredPara(String label, String value, Font labelFont, Font valueFont) {
        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_CENTER);
        p.add(new Chunk(label, labelFont));
        p.add(new Chunk(value, valueFont));
        return p;
    }

    private Paragraph spacedCenteredPara(String label, String value, Font labelFont, Font valueFont, float spacingBefore) {
        Paragraph p = centeredPara(label, value, labelFont, valueFont);
        p.setSpacingBefore(spacingBefore);
        return p;
    }

    private String getGradeText(double averageGrade) {
        if (averageGrade >= 5.5 & averageGrade <= 6) return "Отличен";
        if (averageGrade >= 4.5) return "Много добър";
        if (averageGrade >= 3.5) return "Добър";
        if (averageGrade >= 2.5) return "Среден";
        if (averageGrade >= 2) return "Слаб";
        return "****";
    }
}
