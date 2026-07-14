package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateDTO implements Serializable {
    private LocalDate date;
}
