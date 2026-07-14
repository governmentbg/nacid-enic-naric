package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.inquiry;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.LibservAppDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.LibservObject;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InquiryAppDTO implements Serializable, LibservObject {

    private LibservAppDTO libservApp;
    private List<ImpactFactorDTO> impactFactors;
    private List<PublicationPeriodDTO> publicationPeriods;
    private ReferenceDataDTO kind;
    private String inquiryAim;
    private Integer periodFrom;
    private Integer periodTo;
    private String previousInquiry;
    private String inquiryNotes;
    private ReferenceDataDTO citingSearchType;
    private ReferenceDataDTO impactFactorSearchType;

    public InquiryAppDTO(LibservAppDTO libservApp) {
        this.libservApp = libservApp;
    }
}
