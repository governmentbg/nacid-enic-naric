package bg.duosoft.nacid.backoffice.core.be.service.report.impl.docx;

import bg.duosoft.nacidshareddata.util.date.DateUtils;
import com.spire.doc.Document;

import java.util.Date;
import java.util.Map;

/**
 * User: ggeorgiev
 * Date: 16.11.2022
 * Time: 18:03
 */

abstract class ReportServiceExecutorBase {

    /**
     * Nalaga se da se podavat groupNames + fieldNames na process method-a a ne da si gi chete vytre v Executor-a, zashtoto kato se vikne document.getMailMerge().setHideEmptyGroup(true), sled izpylnenie na grupite,
     * imenata na empty grupite veche ne se vry6tat ot document.getMailMerge().getGroupNames(), no se vry6tat ot document.getMailMerge().getFieldNames().
     * Primer: ako v dokumenta ima grupa s ime cc_group_applications, koqto e prazna, to predi da se mail-merge-nat grupite, document.getMailMerge().getGroupNames() q vry6ta, no sled kato se izpylni mail-merge-a na grupite,getGroupNames() ne q vry6ta, no getFieldNames() prodyljava da q vry6ta,
     * koeto si e problem - ne znam pri mail-merge-vane na field-ovete che trqbva da q ignoriram tazi grupa!!!!!
     * @param document
     * @param fieldNames
     * @param groupNames
     * @param customValues
     * @param sqlParams
     */
    protected abstract void process(Document document, String[] fieldNames, String[] groupNames, Map<String, Object> customValues, Map<String, Object> sqlParams);

    protected String getValueAsString(Object o) {
        if (o == null) {
            return "";
        } else if (o instanceof Date d) {
            return DateUtils.formatDate(d);
        } else {
            return o.toString();
        }
    }
}
