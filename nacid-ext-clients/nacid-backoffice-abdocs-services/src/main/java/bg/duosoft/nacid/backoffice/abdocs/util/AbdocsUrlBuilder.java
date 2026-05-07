package bg.duosoft.nacid.backoffice.abdocs.util;

import bg.duosoft.nacid.backoffice.abdocs.properties.AbdocsPropertyAccess;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsService;
import bg.duosoft.nacid.backoffice.abdocs.service.security.AbdocsTokenHolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AbdocsUrlBuilder {
    public static final String VIEW_DOCUMENT_WITH_AUTH = "{url}#/externalLogin?token={token}&resource=docs/edit/{docId}/view";
    public static final String VIEW_RAS_DOCUMENT_WITH_AUTH = "{url}/services/regPreview/{rasId}?token={token}";
    public static final String VIEW_APOSTILLE_DOCUMENT_WITH_AUTH = "{url}/application/{docId}?token={token}";

    private final AbdocsService abdocsService;
    private final AbdocsTokenHolderService abdocsTokenHolderService;
    private final AbdocsPropertyAccess abdocsPropertyAccess;

    public String viewDocWithAuth(String regNumber) {
        try {
            Integer docId = abdocsService.getDocumentIdByRegNumber(regNumber);
            if (Objects.nonNull(docId)) {
                return viewDocWithAuth(docId);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public String viewDocWithAuth(Integer documentId) {
        String accessToken = abdocsTokenHolderService.selectAccessToken(AbdocsServiceSecurityUtils.getUsername());
        if (!StringUtils.hasText(accessToken)) {
            return null;
        }

        return VIEW_DOCUMENT_WITH_AUTH
                .replace("{url}", abdocsPropertyAccess.getAbdocsBaseUrl())
                .replace("{token}", accessToken)
                .replace("{docId}", String.valueOf(documentId));
    }

    public String viewRasApplication(String rasId) {
        if (!StringUtils.hasText(rasId)) {
            return null;
        }

        String accessToken = abdocsTokenHolderService.selectAccessToken(AbdocsServiceSecurityUtils.getUsername());
        if (!StringUtils.hasText(accessToken)) {
            return null;
        }

        return VIEW_RAS_DOCUMENT_WITH_AUTH
                .replace("{url}", abdocsPropertyAccess.getRasBaseUrl())
                .replace("{token}", accessToken)
                .replace("{rasId}", rasId);
    }

    public String viewApostilleApplication(String apnId) {
        if (!StringUtils.hasText(apnId)) {
            return null;
        }

        String accessToken = abdocsTokenHolderService.selectAccessToken(AbdocsServiceSecurityUtils.getUsername());
        if (!StringUtils.hasText(accessToken)) {
            return null;
        }

        return VIEW_APOSTILLE_DOCUMENT_WITH_AUTH
                .replace("{url}", abdocsPropertyAccess.getApostilleBaseUrl())
                .replace("{token}", accessToken)
                .replace("{docId}", apnId);
    }


}
