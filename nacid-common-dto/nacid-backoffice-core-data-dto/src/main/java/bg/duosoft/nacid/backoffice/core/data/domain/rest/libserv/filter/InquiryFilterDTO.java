package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.filter.sort.LibservApplicationsSortUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InquiryFilterDTO extends LibservAppFilterDTO {
    private String orderBy = LibservApplicationsSortUtils.ID;
}
