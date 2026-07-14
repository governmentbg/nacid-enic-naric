package bg.duosoft.nacid.backoffice.core.be.service.report.impl.docx;

import bg.duosoft.nacid.backoffice.core.be.service.report.impl.FieldSqlExecutor;
import bg.duosoft.nacid.backoffice.core.be.service.report.impl.SqlExecutorBase;
import com.spire.doc.Document;
import com.spire.doc.fields.TextRange;
import com.spire.doc.formatting.CharacterFormat;
import com.spire.doc.reporting.MergeFieldEventArgs;
import com.spire.doc.reporting.MergeFieldEventHandler;
import com.spire.doc.reporting.MergeImageFieldEventArgs;
import com.spire.doc.reporting.MergeImageFieldEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * User: ggeorgiev
 * Date: 16.11.2022
 * Time: 18:02
 */
@Slf4j
@Service
@RequiredArgsConstructor
class ReportServiceFieldExecutor extends ReportServiceExecutorBase {
    private final FieldSqlExecutor fieldSqlExecutor;
    protected void process(Document document, String[] fieldNames, String[] groupNames,  Map<String, Object> customValues, Map<String, Object> sqlParams) {

        Map<String, FieldSqlExecutor.FieldValue> fieldValues = fieldSqlExecutor.getFieldValues(fieldNames, groupNames, customValues, sqlParams);

        document.getMailMerge().MergeImageField = new MergeImageFieldEventHandler() {
            @Override
            public void invoke(Object sender, MergeImageFieldEventArgs args) {
                mailMergeImage(fieldValues, sender, args);
            }
        };
        document.getMailMerge().MergeField = new MergeFieldEventHandler() {
            @Override
            public void invoke(Object o, MergeFieldEventArgs field) {
                mailMergeField(fieldValues, o, field);
            }
        };

        try {
            document.getMailMerge().execute(fieldNames, fieldNames);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void mailMergeImage(Map<String, FieldSqlExecutor.FieldValue> fieldValues, Object sender, MergeImageFieldEventArgs field) {
        String fieldName = field.getFieldName();
        log.trace("Merge Image Field : " + fieldName);
        Object value = fieldValues.get(fieldName).getObject();
        if (value instanceof byte[] arr) {
            field.setImageBytes(arr);
        } else if (value == null) {
            //doing nothing!!!!
            log.warn("There is no value for fieldName: " + fieldName);
        } else {
            throw new RuntimeException("FieldName:" + fieldName + " Unknown type..." + value.getClass().getName());
        }
    }

    private void mailMergeField(Map<String, FieldSqlExecutor.FieldValue> fieldValues, Object sender, MergeFieldEventArgs field) {
        String fieldName = field.getFieldName();
        log.trace("Merge field :" + field.getFieldName());
        FieldSqlExecutor.FieldValue fv = fieldValues.get(fieldName);
        if (fv.isHtml()) {
            _processHtmlMailMergeField(fv.getObject(), sender, field);
        } else {
            _processPlainMailMergeField(fv.getObject(), sender, field);
        }
    }

    private void _processHtmlMailMergeField(Object fieldValue, Object sender, MergeFieldEventArgs field) {
        CharacterFormat cf = null;
        if (field.getCurrentMergeField().getOwnerParagraph().getFirstChild() instanceof TextRange tr) {
            cf = tr.getCharacterFormat();
        }
        field.setText("");
        String val = getValueAsString(fieldValue);
        if (StringUtils.hasText(val)) {
            field.getCurrentMergeField().getOwnerParagraph().appendHTML(val);
            Iterator iterator = field.getCurrentMergeField().getOwnerParagraph().getChildObjects().iterator();
            while (iterator.hasNext()) {
                if (iterator.next() instanceof TextRange tr) {
                    tr.applyCharacterFormat(cf);
                }
            }
        }

    }
    private void _processPlainMailMergeField(Object fieldValue, Object sender, MergeFieldEventArgs field) {
        String value = getValueAsString(fieldValue);
        field.setText(value);
    }
}
