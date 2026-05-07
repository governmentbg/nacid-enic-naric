package bg.duosoft.nacid.clients.signature.model;

import lombok.Data;
import java.math.BigInteger;
import java.util.Date;

@Data
public class SignerDetails {
    private String signerName;
    private String signerOrganization;
    private BigInteger signerSerialNumber;
    private String signerIssuer;
    private Date validFrom;
    private Date validTo;
    private Date signedDate;
    private String signerEmail;
}
