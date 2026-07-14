package bg.duosoft.email.nacidemailproducer.domain.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailNotificationDTO implements Serializable {
    private Integer id;
    private String type;
    private String message;
    private String recipients;
    private String subject;
}
