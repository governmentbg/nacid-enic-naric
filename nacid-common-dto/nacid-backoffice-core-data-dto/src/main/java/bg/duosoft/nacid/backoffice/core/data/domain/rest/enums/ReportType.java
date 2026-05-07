package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;

/**
 * User: ggeorgiev
 * Date: 13.03.2023
 * Time: 15:11
 */
public enum ReportType {
    PDF("application/pdf", "pdf"), DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx"), XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
    private String mimeType;
    private String extension;
    private ReportType(String mimeType, String extension) {
        this.mimeType = mimeType;
        this.extension = extension;
    }

    public String getMimeType() {
        return mimeType;
    }
    public String getExtension() {
        return extension;
    }
    public static ReportType getByExtension(String extension) {
        return Arrays.stream(ReportType.values()).filter(r -> r.getExtension().equalsIgnoreCase(extension)).findFirst().orElse(null);
    }
}
