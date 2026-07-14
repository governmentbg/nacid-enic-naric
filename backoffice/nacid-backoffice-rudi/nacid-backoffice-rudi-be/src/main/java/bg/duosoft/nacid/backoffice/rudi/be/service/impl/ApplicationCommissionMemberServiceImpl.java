package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationCommissionMemberEntity;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.ApplicationCommissionMemberMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.ApplicationCommissionMemberRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationCommissionMemberService;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationCommissionMemberServiceImpl implements ApplicationCommissionMemberService {
    private final ApplicationCommissionMemberRepository applicationCommissionMemberRepository;
    private final ApplicationCommissionMemberMapper applicationCommissionMemberMapper;
    @Override
    public List<ApplicationCommissionMemberDTO> selectByApplicationId(Integer applicationId) {
        return applicationCommissionMemberMapper.toDtoList(applicationCommissionMemberRepository.selectByApplicationId(applicationId));
    }

    @Override
    public ApplicationCommissionMemberDTO selectById(Integer id) {
        ApplicationCommissionMemberEntity applicationCommissionMemberEntity = applicationCommissionMemberRepository.findById(id).orElse(null);
        ApplicationCommissionMemberDTO applicationCommissionMemberDTO = applicationCommissionMemberMapper.toDto(applicationCommissionMemberEntity);
        if (Objects.isNull(applicationCommissionMemberDTO)){
            throw new ResourceNotFoundException();
        }
        return applicationCommissionMemberDTO;
    }

    @Override
    public void delete(Integer id) {
        applicationCommissionMemberRepository.deleteById(id);
    }


}
