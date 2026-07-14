package bg.duosoft.nacid.clients.signature.model;

import lombok.Data;

@Data
public class SignFileRequest {
    private String uuid;
    private byte[] content;
    private String fileName;
    private String userName;
    private String callbackUrl;
    private boolean overrideOnChanged;
    private String description;
    private String confirmCustomerSignatureUrl;
}
