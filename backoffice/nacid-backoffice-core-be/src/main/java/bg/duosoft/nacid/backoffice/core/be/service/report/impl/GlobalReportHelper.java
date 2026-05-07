package bg.duosoft.nacid.backoffice.core.be.service.report.impl;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationPropertiesService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportFilter;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationProperty;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationPropertyDTO;
import bg.duosoft.nacidbackofficeshareddata.service.QrService;
import bg.duosoft.nacidshareddata.util.appreport.MetadataKey;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.Map;
import java.util.Optional;

public class GlobalReportHelper {
    public static void generateQRCodeImageByCertNumber(GenerateReportFilter filter, QrService qrService, ApplicationPropertiesService applicationPropertiesService) {
        Map<Integer, Map<String, Object>> customValues = filter.getCustomValues();
        if (!CollectionUtils.isEmpty(customValues)) {
            for (Integer applicationId : customValues.keySet()) {
                Map<String, Object> appRelatedCustomValues = customValues.get(applicationId);
                if (!CollectionUtils.isEmpty(appRelatedCustomValues)) {
                    String uuid = appRelatedCustomValues.get(MetadataKey.AR_CERTIFICATE_UUID_KEY).toString();
                    if (StringUtils.hasText(uuid)){
                        ApplicationPropertyDTO applicationProperty = applicationPropertiesService.selectById(ApplicationProperty.RUDI_QR_CODE_URL.code());
                        String barcodeText = applicationProperty.getValue().replace("{0}", uuid);

                        Integer height = Optional.ofNullable(applicationPropertiesService.selectById(ApplicationProperty.RUDI_CERTIFICATE_HEIGHT.code())).map(r -> r.getValue()).map(Integer::parseInt).orElse(120);
                        Integer width = Optional.ofNullable(applicationPropertiesService.selectById(ApplicationProperty.RUDI_CERTIFICATE_WIDTH.code())).map(r -> r.getValue()).map(Integer::parseInt).orElse(120);
                        appRelatedCustomValues.put(MetadataKey.AR_CERTIFICATE_QR_KEY, qrService.generateQRCodeImage(barcodeText, height, width));
                    }
                }

            }
        }
    }
}
