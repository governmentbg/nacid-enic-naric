package bg.duosoft.nacidshared.web.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties("notifications")
public class NotificationProperties {
    private List<String> adminEmails = new ArrayList<>();
}