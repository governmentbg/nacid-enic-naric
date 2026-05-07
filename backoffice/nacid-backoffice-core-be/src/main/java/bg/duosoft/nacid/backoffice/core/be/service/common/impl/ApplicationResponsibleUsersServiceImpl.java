package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.common.ApplicationResponsibleUsersRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationResponsibleUsersService;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationResponsibleUsersEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUsersDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.NacidUserAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationResponsibleUsersMapper;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ApplicationResponsibleUsersServiceImpl implements ApplicationResponsibleUsersService {

    private final KeycloakUserService keycloakUserService;
    private final ApplicationResponsibleUsersRepository applicationResponsibleUsersRepository;
    private final ApplicationResponsibleUsersMapper applicationResponsibleUsersMapper;

    @Override
    public List<ApplicationResponsibleUsersDTO> selectByApplicationId(Integer applicationId) {
        return applicationResponsibleUsersMapper.toDtoList(applicationResponsibleUsersRepository.selectByApplicationId(applicationId));
    }

    @Override
    public ApplicationResponsibleUsersDTO selectMainResponsibleUserByApplicationId(Integer applicationId) {
        ApplicationResponsibleUsersEntity applicationResponsibleUsersEntity = applicationResponsibleUsersRepository.selectMainResponsibleUserByApplicationId(applicationId);
        return applicationResponsibleUsersMapper.toDto(applicationResponsibleUsersEntity);
    }

    @Override
    @Cacheable(value = "ApplicationResponsibleUsersServiceImpl", key = "'responsible-user-autocomplete-' + #group")
    public List<NacidUserAutocompleteDTO> selectResponsibleUsersByGroup(String group) {
        Set<NacidUserDetailsDTO> groupHierarchyUsers = keycloakUserService.getUsersFromGroupHierarchy(group);
        if (!CollectionUtils.isEmpty(groupHierarchyUsers)) {
            return groupHierarchyUsers.stream().map(e-> NacidUserAutocompleteDTO.newInstance(e.getUsername(), e.getFullName(), e.getEnabled())).toList();
        }

        return new ArrayList<>();
    }
}
