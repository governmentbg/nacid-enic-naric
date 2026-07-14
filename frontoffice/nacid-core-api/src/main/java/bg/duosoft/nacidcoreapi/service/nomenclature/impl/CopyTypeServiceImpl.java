package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.CfgCopyTypeRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.CopyTypeService;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 10.09.2025
 * Time: 15:18
 */
@RequiredArgsConstructor
@Service
public class CopyTypeServiceImpl implements CopyTypeService {
    private final CfgCopyTypeRepository cfgCopyTypeRepository;
    private final ReferenceDataMapper referenceDataMapper;
    @Override
    public List<ReferenceDataDTO> getAll(ApplicationType applicationType, ApplicationSubtype applicationSubtype, boolean onlyActive) {
        return referenceDataMapper.toDtoList(cfgCopyTypeRepository.getAllByDomainApplicationTypeSubtypeAndActiveOrderByIndexAscNameAsc(applicationType.getCode(), applicationSubtype == null ? null : applicationSubtype.getCode(), onlyActive));
    }
}
