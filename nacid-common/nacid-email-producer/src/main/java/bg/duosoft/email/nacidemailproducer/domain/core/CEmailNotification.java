package bg.duosoft.email.nacidemailproducer.domain.core;

import bg.duosoft.email.nacidemailproducer.utils.MailConstants;
import lombok.*;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CEmailNotification implements Serializable {
    private Integer id;
    private String subject;
    private String text;
    private String recipients;
    private String replyTo;
    private String cc;
    private String bcc;
    private Date createdDate;
    private Date sentDate;
    private String comment;
    private Boolean skipSending;
    private Boolean isHtml;

    public String[] splitRecipients() {
        return splitParticipants(this.recipients);
    }

    public String[] splitBcc() {
        return splitParticipants(this.bcc);
    }

    public String[] splitCc() {
        return splitParticipants(this.cc);
    }

    private String[] splitParticipants(String participants) {
        if (!StringUtils.hasText(participants)) {
            return null;
        }
        return participants.split(MailConstants.EMAIL_PARTICIPANTS_DELIMITER);
    }

}
