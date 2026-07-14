package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.common.address.AdminAddressClient;
import bg.duosoft.nacid.backoffice.core.client.client.common.person.AdminPersonClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacidbackofficeshareddata.service.ApplicationDataBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ApplicationDataBaseServiceImpl implements ApplicationDataBaseService {

    //Set required = false, because nacid-backoffice-core-be doesn't need nacid-backoffice-core-api config
    @Autowired(required = false)
    private AdminPersonClient adminPersonClient;
    @Autowired(required = false)
    private AdminAddressClient adminAddressClient;

    @Override
    public void fillFullPersonAndAddressData(ApplicationDTO application) {
        PersonDTO applicant = application.getApplicant();
        if (Objects.nonNull(applicant) && Objects.nonNull(applicant.getId())) {
            PersonDTO result = adminPersonClient.selectById(applicant.getId());
            if (Objects.nonNull(result)) {
                application.setApplicant(result);
            }
        }

        PersonDTO representative = application.getRepresentative();
        if (Objects.nonNull(representative) && Objects.nonNull(representative.getId())) {
            PersonDTO result = adminPersonClient.selectById(representative.getId());
            if (Objects.nonNull(result)) {
                application.setRepresentative(result);
            }
        }

        AddressDTO contactAddress = application.getContactAddress();
        if (Objects.nonNull(contactAddress)) {
            Integer id = contactAddress.getId();
            if (Objects.nonNull(id)) {
                AddressDTO result = adminAddressClient.selectById(id);
                if (Objects.nonNull(result)) {
                    application.setContactAddress(result);
                }
            }
        }

        List<ApplicationDocumentReceiveMethodDTO> documentReceiveMethods = application.getDocumentReceiveMethods();
        if (!CollectionUtils.isEmpty(documentReceiveMethods)) {
            ApplicationDocumentReceiveMethodDTO applicationDocumentReceiveMethodDTO = documentReceiveMethods.stream().filter(r -> Objects.nonNull(r.getDocumentRecipientAddress()) && Objects.nonNull(r.getDocumentRecipientAddress().getId())).findFirst().orElse(null);
            if (Objects.nonNull(applicationDocumentReceiveMethodDTO) && Objects.nonNull(applicationDocumentReceiveMethodDTO.getDocumentRecipientAddress()) && Objects.nonNull(applicationDocumentReceiveMethodDTO.getDocumentRecipientAddress().getId())) {
                AddressDTO result = adminAddressClient.selectById(applicationDocumentReceiveMethodDTO.getDocumentRecipientAddress().getId());
                if (Objects.nonNull(result)) {
                    applicationDocumentReceiveMethodDTO.setDocumentRecipientAddress(result);
                }
            }
        }

        List<ApplicationDocumentReceiveOptionDTO> documentReceiveOptions = application.getDocumentReceiveOptions();
        if (!CollectionUtils.isEmpty(documentReceiveOptions)) {
            ApplicationDocumentReceiveOptionDTO applicationDocumentReceiveOptionDTO = documentReceiveOptions.stream().filter(r -> Objects.nonNull(r.getDocumentRecipientAddress()) && Objects.nonNull(r.getDocumentRecipientAddress().getId())).findFirst().orElse(null);
            if (Objects.nonNull(applicationDocumentReceiveOptionDTO) && Objects.nonNull(applicationDocumentReceiveOptionDTO.getDocumentRecipientAddress()) && Objects.nonNull(applicationDocumentReceiveOptionDTO.getDocumentRecipientAddress().getId())) {
                AddressDTO result = adminAddressClient.selectById(applicationDocumentReceiveOptionDTO.getDocumentRecipientAddress().getId());
                if (Objects.nonNull(result)) {
                    applicationDocumentReceiveOptionDTO.setDocumentRecipientAddress(result);
                }
            }
        }
    }
}
