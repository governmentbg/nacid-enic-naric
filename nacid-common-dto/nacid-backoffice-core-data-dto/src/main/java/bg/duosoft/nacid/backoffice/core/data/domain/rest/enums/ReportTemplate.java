package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

public enum ReportTemplate {
    APP_REPORT_RUDI_SAR("rudi/applications_report_sar.xlsx"),
    APP_REPORT_RUDI_DOCREC("rudi/applications_report_docrec.xlsx"),
    APP_REPORT_RUDI_UDIREC("rudi/applications_report_udirec.xlsx"),
    APP_REPORT_REGPROF("regprof/applications_report.xlsx"),
    APP_REPORT_LIBSERV_BIBLIO_REF("libserv/applications_report_bibliographic_reference.xlsx"),
    APP_REPORT_LIBSERV_DOC_DELIVERY("libserv/applications_report_document_delivery.xlsx"),
    APP_REPORT_LIBSERV_INQUIRY("libserv/applications_report_inquiry.xlsx"),
    APP_REPORT_LIBSERV_OFFICIAL_NOTE("libserv/applications_report_official_note.xlsx"),
    APP_REPORT_SECONDARY("se/applications_report.xlsx"),
    RUDI_COMMON_REPORT("rudi/common_report.xlsx"),
    REGPROF_COMMON_REPORT("regprof/common_report.xlsx");


    private final String template;

    public String template() {
        return template;
    }

    ReportTemplate(String template) {
        this.template = template;
    }
}
