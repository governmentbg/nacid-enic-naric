package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.imi_correspondence;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
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
public class ImiCorrespondenceReportSectionDTO {
    private List<StringIdDTO> imiCorrespondences;
    private List<String> imiCorrespondenceNames;
}
