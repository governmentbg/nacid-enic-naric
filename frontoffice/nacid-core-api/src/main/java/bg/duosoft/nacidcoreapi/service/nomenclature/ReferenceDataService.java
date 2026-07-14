package bg.duosoft.nacidcoreapi.service.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDomainDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ReferenceDataFilterDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 13:37
 */
public interface ReferenceDataService {

    List<ReferenceDataDTO> selectAll(String domain, boolean onlyActive);
    ReferenceDataDTO selectById(String domain, String id);
    ReferenceDataDTO save(ReferenceDataDTO dto);
    ReferenceDataDTO update(ReferenceDataDTO dto);
    void deleteAll(String domain);
    void delete(String domain, String id);
    List<ReferenceDataDTO> selectFoReferenceData(ReferenceDataFilterDTO filter);
    int selectFoReferenceDataCount(ReferenceDataFilterDTO filter);
    List<ReferenceDataDTO> selectByDomain(String domain);
    List<ReferenceDataDomainDTO> getFoReferenceDataDomains();
    boolean isDomainFoOnly(String domain);
}
