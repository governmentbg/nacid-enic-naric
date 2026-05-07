package bg.duosoft.nacidfrontofficedto.services.inquiry;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 15:11
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InquiryDetailsDTO {

    private List<InquiryKind> inquiryKinds;
    private String inquiryAim;
    private String periodFrom;
    private String periodTo;
    private String previousInquiryNum;
    private ReferenceDataDTO citingSearchKind;
    private ReferenceDataDTO impactFactorSearchKind;

    public boolean containsKindCode(String code){
        if(inquiryKinds != null){
            return inquiryKinds.stream().filter(k -> k.getCode().equals(code)).count()>0;
        }
        return false;
    }
}
