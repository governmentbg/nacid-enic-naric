package bg.duosoft.email.nacidemailproducer.domain.core;

import bg.duosoft.email.nacidemailproducer.utils.MailConstants;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class CEmailParticipants implements Serializable {
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private String replyTo;


    public List<String> getBcc() {
        if (Objects.isNull(this.bcc)) {
            this.bcc = new ArrayList<>();
        }
        return this.bcc;
    }

    public List<String> getCc() {
        if (Objects.isNull(this.cc)) {
            this.cc = new ArrayList<>();
        }
        return this.cc;
    }

    public List<String> getTo() {
        if (Objects.isNull(this.to)) {
            this.to = new ArrayList<>();
        }
        return to;
    }

    public String joinTo() {
        return joinParticipants(this.to);
    }

    public String joinBcc() {
        return joinParticipants(this.bcc);
    }

    public String joinCc() {
        return joinParticipants(this.cc);
    }

    private String joinParticipants(List<String> participants) {
        if (CollectionUtils.isEmpty(participants)) {
            return null;
        }
        return String.join(MailConstants.EMAIL_PARTICIPANTS_DELIMITER, participants);
    }

}
