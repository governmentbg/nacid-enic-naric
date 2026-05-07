package bg.duosoft.email.nacidemailproducer.domain.core;

import bg.duosoft.email.nacidemailproducer.enums.EmailTemporaryKeyType;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CEmailTemporaryKey implements Serializable {
    private Integer id;
    private EmailTemporaryKeyType type;
    private String key;
    private String user;
    private Date createdDate;
    private Date expirationDate;
    private Date usedOnDate;
    private String extraData;
}
