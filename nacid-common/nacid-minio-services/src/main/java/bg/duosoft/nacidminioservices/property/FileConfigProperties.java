package bg.duosoft.nacidminioservices.property;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.06.2022
 * Time: 17:09
 */
@Getter
@Component
@ConfigurationProperties("file")
public class FileConfigProperties {

    private final Map<String, FileConfig> fileGroupConfig = new HashMap<>();
}
