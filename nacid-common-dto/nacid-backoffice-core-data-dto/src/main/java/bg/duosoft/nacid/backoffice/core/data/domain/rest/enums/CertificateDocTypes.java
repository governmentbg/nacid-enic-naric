package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Objects;
import java.util.Optional;

/**
 * User: ggeorgiev
 * Date: 17.11.2025
 * Time: 15:05
 */
public enum CertificateDocTypes {
    CERTIFICATE(1, 14, 16, 46,42),
    OFFICIAL_NOTE(205, 213, 212, 214,216),
    VERIFICATION_LETTER(204, 210, 209, 211,215),
    ;

    private final Integer certificateDocTypeId;
    private final Integer obviousMistakeDocTypeId;
    private final Integer duplicateDocTypeId;
    private final Integer destroyedDocTypeId;
    private final Integer invalidatedDocTypeId;


    CertificateDocTypes(Integer certificateDocTypeId, Integer obviousMistakeDocTypeId, Integer duplicateDocTypeId, Integer destroyedDocTypeId, Integer invalidatedDocTypeId) {
        this.certificateDocTypeId = certificateDocTypeId;
        this.obviousMistakeDocTypeId = obviousMistakeDocTypeId;
        this.duplicateDocTypeId = duplicateDocTypeId;
        this.destroyedDocTypeId = destroyedDocTypeId;
        this.invalidatedDocTypeId = invalidatedDocTypeId;
    }

    public Integer getCertificateDocTypeId() {
        return certificateDocTypeId;
    }

    public Integer getObviousMistakeDocTypeId() {
        return obviousMistakeDocTypeId;
    }

    public Integer getDuplicateDocTypeId() {
        return duplicateDocTypeId;
    }

    public Integer getDestroyedDocTypeId() {
        return destroyedDocTypeId;
    }

    public Integer getInvalidatedDocTypeId() {
        return invalidatedDocTypeId;
    }

    public boolean isAnyDocType(Integer docType) {
        return isCertificateDocType(docType) || isDuplicateOrObviousErrorDocType(docType);
    }

    public boolean isCertificateDocType(Integer docTypeId) {
        return Objects.equals(certificateDocTypeId, docTypeId);
    }

    public boolean isDuplicateOrObviousErrorDocType(Integer docTypeId) {
        return Objects.equals(duplicateDocTypeId, docTypeId) || Objects.equals(obviousMistakeDocTypeId, docTypeId);
    }

    public static Optional<CertificateDocTypes> getCertificateDocTypes(ApplicationType applicationType, ApplicationSubType applicationSubType) {
        CertificateDocTypes res = switch (applicationType) {
            case RUDI -> CERTIFICATE;
            case REGPROF -> CERTIFICATE;
            case SE_RECOGNITION -> {
                yield switch (applicationSubType) {
                    case SE_RECOGNITION -> CERTIFICATE;
                    case SE_OFFICIAL_NOTE -> OFFICIAL_NOTE;
                    case SE_VERIFICATION_LETTER -> VERIFICATION_LETTER;
                    default -> null;
                };
            }
            default -> null;
        };
        return res == null ? Optional.empty() : Optional.of(res);
    }
}
