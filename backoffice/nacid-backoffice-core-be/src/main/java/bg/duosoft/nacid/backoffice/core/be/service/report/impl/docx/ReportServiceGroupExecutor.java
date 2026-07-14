package bg.duosoft.nacid.backoffice.core.be.service.report.impl.docx;

import bg.duosoft.nacid.backoffice.core.be.service.report.impl.GroupSqlExecutor;
import com.spire.doc.Document;
import com.spire.doc.reporting.MailMergeDataTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 16.11.2022
 * Time: 18:02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceGroupExecutor extends ReportServiceExecutorBase {
    private final GroupSqlExecutor groupSqlExecutor;

    protected void process(Document document, String[] fieldNames, String[] groupNames, Map<String, Object> customValues, Map<String, Object> sqlParams ) {
        Set<String> groupNamesSet = Arrays.stream(groupNames).collect(Collectors.toSet());
        if (groupNamesSet != null && groupNamesSet.size() > 0) {
            Map<String, List<Map<String, Object>>> groupValues = groupSqlExecutor.getGroupValues(fieldNames, groupNames, customValues, sqlParams);
            for (String group : groupNamesSet) {
                log.trace("Executing group " + group + "..Elements size:" + (groupValues.get(group) == null ? 0 : groupValues.get(group).size()));
                MailMergeDataTable table = new MailMergeDataTable(group, groupValues.get(group));
                try {
                    document.getMailMerge().executeGroup(table);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
