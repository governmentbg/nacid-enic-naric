package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.referencedata.ReferenceDataClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AddressType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataCode;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionMemberEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionMemberDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CommissionMemberFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.CommissionMemberMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.CommissionMemberRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionMemberService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.CommissionMemberValidator;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CommissionMemberServiceImpl extends CrudServiceBaseImpl<Integer, CommissionMemberDTO> implements CommissionMemberService {

    private final CommissionMemberMapper mapper;
    private final CommissionMemberValidator validator;
    private final CommissionMemberRepository repository;
    private final ReferenceDataClient referenceDataClient;

    @Override
    protected CommissionMemberRepository getRepository() {
        return repository;
    }

    @Override
    protected CommissionMemberMapper getMapper() {
        return mapper;
    }

    @Override
    protected CommissionMemberValidator getValidator() {
        return validator;
    }

    @Override
    public List<CommissionMemberDTO> searchRecords(CommissionMemberFilterDTO filter) {
        return mapper.toDtoList(repository.searchRecords(filter));
    }

    @Override
    public int getRecordsCount(CommissionMemberFilterDTO filter) {
        return repository.getRecordsCount(filter);
    }

    @Override
    public void toggleActivation(Integer id) {
        CommissionMemberEntity entity = repository.findById(id).orElse(null);
        if (Objects.isNull(entity)) {
            throw new RuntimeException("Cannot find commission member with ID = " + id);
        }

        Integer isActive = entity.getActive();
        if (Objects.isNull(isActive)) {
            isActive = 0;
        }

        entity.setActive(isActive == 1 ? 0 : 1);
        repository.save(entity);
    }

    @Override
    public List<Integer> selectAllCommissionMemberIdsByCalendarId(Integer calendarId) {
        return repository.selectAllCommissionMemberIdsByCalendarId(calendarId);
    }

    @Override
    public List<CommissionMemberDTO> selectMembersByIds(List<Integer> ids) {
        return mapper.toDtoList(repository.selectMembersByIds(ids));
    }

    @Override
    public List<CommissionMemberDTO> selectMembersByPosition(String position) {
        return mapper.toDtoList(repository.selectMembersByPosition(position));
    }

    @Override
    protected void beforeCreateOrUpdate(CommissionMemberDTO dto) {
        dto.getAddress().setAddressType(referenceDataClient.selectById(ReferenceDataDomain.ADDRESS_TYPE.domain(), AddressType.COMMISSION_MEMBER.code()));

        if (dto.getCivilIdType() != null && Objects.isNull(dto.getCivilIdType().getId())) {
            dto.setCivilIdType(null);
            dto.setCivilId(null);
        }

        if (dto.getProfGroup() != null && Objects.isNull(dto.getProfGroup().getId())) {
            dto.setProfGroup(null);
        }

        if (dto.getCommissionPosition() != null) {
            if (Objects.isNull(dto.getCommissionPosition().getId())) {
                dto.setCommissionPosition(null);
            } else {
                dto.getCommissionPosition().setDomain(ReferenceDataDomain.COMMISSION_POSITION.domain());
            }
        }


        if (dto.getAddress().getSettlement() != null && Objects.isNull(dto.getAddress().getSettlement().getId())) {
            dto.getAddress().setSettlement(null);
        }
    }
}
