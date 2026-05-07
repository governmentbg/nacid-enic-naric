package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.email.nacidemailproducer.service.MailSenderService;
import bg.duosoft.nacidbackofficepublicservicesclient.client.BOAdminApplicationCertificateClient;
import bg.duosoft.nacidbackofficepublicservicesclient.client.BOAdminDmsOnlyAcceptClient;
import bg.duosoft.nacidbackofficepublicservicesclient.client.BOAdminDmsReadClient;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCertificateDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.DmsApplicationInsertRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.DmsDocDetailsDTO;
import bg.duosoft.nacidservicesbe.service.BoPublicServicesAdminService;
import bg.duosoft.nacidshared.web.property.NotificationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 16:15
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BoPublicServicesAdminServiceImpl implements BoPublicServicesAdminService {

    private final BOAdminDmsOnlyAcceptClient boAdminDmsOnlyAcceptClient;
    private final BOAdminApplicationCertificateClient boAdminApplicationCertificateClient;
    private final BOAdminDmsReadClient boAdminDmsReadClient;

    private final MailSenderService mailSenderService;
    private final NotificationProperties notificationProperties;

    @Override
    public void insertApplicationToDms(DmsApplicationInsertRequestDTO insertRequest){
        try {
            boAdminDmsOnlyAcceptClient.acceptLibservInDmsOnly(insertRequest);
        } catch (Exception e){
            log.error("Failed to accept application by inserting it directly into DMS", e);
            List<String> adminEmails = notificationProperties.getAdminEmails();
            if (!CollectionUtils.isEmpty(adminEmails)) {
                mailSenderService.sendSimpleMail(String.join(",", adminEmails), "FO - insertApplicationToDms - exception",
                        String.format("Cannot insert application (id: %s) to DMS. Exception: %s. Check if APIs are working", insertRequest.getId(), e.getMessage()));
            }
        }
    }

    @Override
    public ApplicationCertificateDTO getCertificateForApplication(Integer applicationId) {
        return boAdminApplicationCertificateClient.getCertificateForFoApplication(applicationId);
    }

    @Override
    public DmsDocDetailsDTO getDmsDocDetailsForBoAttachedDocId(Integer boAttachedDocId) {
        return boAdminDmsReadClient.getDmsDocForAppAttachedDocId(boAttachedDocId);
    }

    @Override
    public ResponseEntity<byte[]> downloadDmsFile(Integer docId, Integer fileId) {
        return boAdminDmsReadClient.getDmsFileContent(docId, fileId);
    }
}
