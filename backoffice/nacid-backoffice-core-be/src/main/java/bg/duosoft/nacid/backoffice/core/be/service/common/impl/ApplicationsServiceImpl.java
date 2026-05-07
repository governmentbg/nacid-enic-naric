package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.core.be.repository.common.ApplicationRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.AbdocsCoreService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationResponsibleUsersService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.NacidUserAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AddressType;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationTableViewMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.VApplicationsByPersonMapper;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsNumbersUtils;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationsServiceImpl implements ApplicationsService {

    private final KeycloakUserService keycloakUserService;
    private final ApplicationRepository applicationRepository;
    private final ApplicationTableViewMapper applicationTableViewMapper;
    private final VApplicationsByPersonMapper vApplicationsByPersonMapper;
    private final ApplicationMapper applicationMapper;
    private final ApplicationResponsibleUsersService applicationResponsibleUsersService;
    private final AbdocsCoreService abdocsCoreService;



    @Override
    public ApplicationDTO getApplicationById(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }

        ApplicationEntity entity = applicationRepository.findById(id).orElse(null);
        return applicationMapper.toDto(entity);
    }

    @Override
    public List<NacidUserAutocompleteDTO> getCreatedUsersAutocomplete(String applicationType) {
        List<String> userCreatedApplicationsList = applicationRepository.getAllUserCreatedByApplicationType(applicationType);
        return selectUsersAutocompleteData(userCreatedApplicationsList);
    }

    @Override
    public List<NacidUserAutocompleteDTO> getResponsibleUsersAutocomplete(String applicationType) {
        List<String> applicationResponsibleUsersList = applicationRepository.getAllResponsibleUsersByApplicationType(applicationType);
        return selectUsersAutocompleteData(applicationResponsibleUsersList);
    }

    @Override
    public Pair<String, String> getAppTypeAndSubtypeById(Integer id) {
        Object[] objects = applicationRepository.selectAppTypeAndSubtypeById(id);
        if (Objects.isNull(objects) || objects.length != 1) {
            return null;
        }

        Object[] resultArray = (Object[]) objects[0];
        return Pair.of((String) resultArray[0], (String) resultArray[1]);
    }

    @Override
    public LocalDateTime getDateCreated(Integer id) {
        return applicationRepository.getDateCreated(id);
    }

    @Override
    public List<ApplicationTableViewDTO> getApplicationsByAddressIdAndType(String addressType, Integer addressId) {
        switch (AddressType.selectByCode(addressType)) {
            case CONTACT -> {
                return applicationTableViewMapper.toDtoList(applicationRepository.getApplicationsByContactAddressId(addressId));
            }
            case DOCUMENT -> {
                return applicationTableViewMapper.toDtoList(applicationRepository.getApplicationsByDocRecipientAddressId(addressId));
            }
            default -> {
                return new ArrayList<>();
            }
        }
    }

    @Override
    public Integer getApplicationsCountByAddressIdAndType(String addressType, Integer addressId) {
        switch (AddressType.selectByCode(addressType)) {
            case CONTACT -> {
                return applicationRepository.getApplicationsCountByContactAddressId(addressId);
            }
            case DOCUMENT -> {
                return applicationRepository.getApplicationsCountByDocRecipientAddressId(addressId);
            }
            default -> {
                return null;
            }
        }
    }

    private List<NacidUserAutocompleteDTO> selectUsersAutocompleteData(List<String> usernameList) {
        List<NacidUserDetailsDTO> keycloakUsersList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(usernameList)) {
            for (String username : usernameList) {
                NacidUserDetailsDTO user = keycloakUserService.getUserByUsername(username);
                if (Objects.nonNull(user)) {
                    keycloakUsersList.add(user);
                }
            }
        }

        return keycloakUsersList.stream().map(userDetails -> NacidUserAutocompleteDTO.newInstance(userDetails.getUsername(), userDetails.getFullName(), userDetails.getEnabled())).collect(Collectors.toList());
    }

    @Override
    public List<AppPersonDataDTO> getApplicationsByPersonId(Integer personId) {
        List<ApplicationRepository.AppPersonProjection> result = applicationRepository.getApplicationsByPersonId(personId);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }

        return result.stream()
                .map(o -> AppPersonDataDTO.builder()
                        .applicationId(o.getApplicationId())
                        .entryNum(o.getEntryNum())
                        .dateCreated(o.getDateCreated())
                        .statusName(o.getStatusName())
                        .appType(o.getAppType())
                        .appSubType(o.getAppSubType())
                        .docflowStatusName(o.getDocflowStatusName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Integer getApplicationsCountByPersonId(Integer personId) {
        return applicationRepository.getApplicationsCountByPersonId(personId);
    }

    @Override
    public String selectStatusCodeById(Integer applicationId) {
        return applicationRepository.selectStatusCodeById(applicationId);
    }

    @Override
    public Integer selectEfilingIdByApplicationId(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return applicationRepository.selectEfilingIdByApplicationId(id);
    }

    @Override
    public Integer selectApplicationIdByEfilingId(Integer efilingId) {
        if (Objects.isNull(efilingId)) {
            return null;
        }
        return applicationRepository.selectApplicationIdByEfilingId(efilingId);
    }

    @Override
    public String getStatusCodeByEntryDetails(String entryNumber, LocalDate entryDate) {
        Optional<String> optStatus = applicationRepository.selectStatusCodeByEntryDetails(entryNumber, entryDate);
        if (optStatus.isPresent()) {
            return optStatus.get();
        }
        return null;
    }

    @Override
    public Integer getApplicationIdByEntryDetails(String entryNumber, LocalDate entryDate) {
        Optional<Integer> apnId = applicationRepository.selectApnIdByEntryDetails(entryNumber, entryDate);
        if (apnId.isPresent()) {
            return apnId.get();
        }
        return null;
    }

    @Override
    public ApplicationBaseDataDTO getApplicationBaseData(String entryNumber, LocalDate entryDate) {
        return applicationRepository.getApplicationBaseData(entryNumber, entryDate);
    }

    @Override
    public ApplicationBaseDataDTO getApplicationBaseDataByAbdocsId(Integer abdocsId) {
        Doc doc = abdocsCoreService.selectAbdocsDoc(abdocsId);
        if (Objects.isNull(doc)) {
            return null;
        }

        ApplicationBaseDataDTO applicationBaseData;
        if (abdocsId.equals(doc.getRootDocId())) {
            Pair<String, LocalDate> entryNumberAndDatePair = AbdocsNumbersUtils.extractEntryNumberAndDate(doc.getRegUri());
            applicationBaseData = getApplicationBaseData(entryNumberAndDatePair.getFirst(), entryNumberAndDatePair.getSecond());
        } else {
            Doc parentDoc = abdocsCoreService.selectAbdocsDoc(doc.getRootDocId());
            Pair<String, LocalDate> parentEntryNumberAndDatePair = AbdocsNumbersUtils.extractEntryNumberAndDate(parentDoc.getRegUri());
            applicationBaseData = getApplicationBaseData(parentEntryNumberAndDatePair.getFirst(), parentEntryNumberAndDatePair.getSecond());
        }

        return applicationBaseData;
    }

    @Override
    public String getApplicationResponsibleUserByBackofficeNumber(String backofficeNumber) {
        if (!StringUtils.hasText(backofficeNumber)) {
            throw new RuntimeException("Empty backofficeNumber!");
        }
        Pair<String, LocalDate> pairResult = AbdocsNumbersUtils.extractEntryNumberAndDate(backofficeNumber);
        Integer applicationId = getApplicationIdByEntryDetails(pairResult.getFirst(), pairResult.getSecond());
        if (Objects.isNull(applicationId)) {
            return null;
        }
        ApplicationResponsibleUsersDTO applicationResponsibleUsersDTO = applicationResponsibleUsersService.selectMainResponsibleUserByApplicationId(applicationId);
        if (Objects.isNull(applicationResponsibleUsersDTO)) {
            return null;
        }
        return applicationResponsibleUsersDTO.getResponsibleUser();
    }

    @Transactional
    public void updateApplicationPaidFlag(String entryNum, LocalDate entryDate, Integer paidFlag) {
        applicationRepository.updateApplicationPaidFlag(entryNum, entryDate, paidFlag);
    }
}
