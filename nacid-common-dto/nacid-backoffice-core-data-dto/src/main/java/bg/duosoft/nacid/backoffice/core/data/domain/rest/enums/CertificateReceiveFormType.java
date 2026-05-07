package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum CertificateReceiveFormType {

    PAPER("PAP"),
    ELECTRONIC("E"),
    PAPER_AND_ELECTRONIC("PE");
    private final String code;

    public String code() {
        return code;
    }

    CertificateReceiveFormType(String code) {
        this.code = code;
    }

    public static CertificateReceiveFormType selectByCode(String code) {
        return Arrays.stream(CertificateReceiveFormType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[CertificateReceiveForm] Unknown reference data code! Code: " + code));
    }
}
