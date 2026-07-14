package bg.duosoft.nacidminioservices.property;

import bg.duosoft.nacidshareddata.util.MimeTypeUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.06.2022
 * Time: 17:18
 */
@Data
public class FileConfig {

    private Integer maxSize;
    private String allowedTypes;
    public BigDecimal getMaxSizeInMb() {
        return BigDecimal.valueOf(maxSize).divide(BigDecimal.valueOf(1024 * 1024), 2, RoundingMode.HALF_UP);
    }
    public List<String> getExtensions() {
        return Arrays.stream(getAllowedTypes().split(",")).map(MimeTypeUtils::getFileExtensions).filter(Objects::nonNull).flatMap(Collection::stream).distinct().toList();
    }
}
