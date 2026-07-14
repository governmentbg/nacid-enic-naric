package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 14.07.2022
 * Time: 16:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReferenceDataDTO {
    private String domain;
    private String domainName;
    private String id;
    private String name;
    private Integer index;
    private Boolean isActive;

    public ReferenceDataDTO(String domain, String id) {
        this.domain = domain;
        this.id = id;
    }

    protected ReferenceDataDTO(ReferenceDataDTO referenceData) {
        this.domain = referenceData.getDomain();
        this.domainName = referenceData.getDomainName();
        this.id = referenceData.getId();
        this.name = referenceData.getName();
        this.index = referenceData.getIndex();
        this.isActive = referenceData.getIsActive();
    }
}
