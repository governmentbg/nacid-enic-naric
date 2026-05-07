package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.diploma_owner;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.common.NaturalPersonReportDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiplomaOwnerSectionDTO extends NaturalPersonReportDTO {
    private List<StringIdDTO> countries;
}
