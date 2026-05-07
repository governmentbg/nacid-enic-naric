package bg.duosoft.nacidservicesbe.service.utils;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntityPK;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDomain;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidservicesbe.domain.entity.common.*;
import bg.duosoft.nacidservicesbe.domain.entity.lib.InquiryFullEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.InquiryKindEntity;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.11.2022
 * Time: 13:16
 */
public class ApplicationEntityUtils {

    public static void keepDBApplicationDetails(ApplicationEntity dbApplication, ApplicationEntity toSave){
        toSave.setId(dbApplication.getId());

        keepPersonDBDetails(dbApplication.getApplicant(), toSave.getApplicant());
        keepPersonDBDetails(dbApplication.getRepresentative(), toSave.getRepresentative());
        keepAddressDBDetails(dbApplication.getContactAddress(), toSave.getContactAddress());

        if(toSave.getApplicationDocumentReceiveMethods() != null) {
            List<ApplicationDocumentReceiveMethodEntity> methodsWithAddressToSave = toSave.getApplicationDocumentReceiveMethods().stream().filter(meth -> meth.getDocumentRecipientAddress() != null).collect(Collectors.toList());
            List<ApplicationDocumentReceiveMethodEntity> methodsWithAddressInDb = dbApplication.getApplicationDocumentReceiveMethods().stream().filter(meth -> meth.getDocumentRecipientAddress() != null).collect(Collectors.toList());

            if(methodsWithAddressToSave.size() == 1 && methodsWithAddressInDb.size() == 1) {
                keepAddressDBDetails(methodsWithAddressInDb.get(0).getDocumentRecipientAddress(), methodsWithAddressToSave.get(0).getDocumentRecipientAddress());
            } else if(methodsWithAddressToSave.size() > 1 || methodsWithAddressInDb.size() > 1){
                throw new RuntimeException("It is not possible to have more than one application document receive method with address");
            }
        }

        toSave.setAttachedDocs(dbApplication.getAttachedDocs());
        toSave.setStatusHistory(dbApplication.getStatusHistory());
        toSave.setApplicationTypeCode(dbApplication.getApplicationTypeCode());
        toSave.setApplicationSubtype(dbApplication.getApplicationSubtype());
        toSave.setEntryDate(dbApplication.getEntryDate());
        toSave.setEntryNumber(dbApplication.getEntryNumber());
        toSave.setUserCreated(dbApplication.getUserCreated());
        toSave.setDateCreated(dbApplication.getDateCreated());
        toSave.setAccessCode(dbApplication.getAccessCode());
        toSave.setReceipts(dbApplication.getReceipts());
        toSave.setTempNumber(dbApplication.getTempNumber());
        toSave.setServiceType(dbApplication.getServiceType());
        toSave.setPaidFlag(dbApplication.getPaidFlag());
        toSave.setSignedFlag(dbApplication.getSignedFlag());
        toSave.setMultipleApplication(dbApplication.getMultipleApplication());
        toSave.setExternalSystemId(dbApplication.getExternalSystemId());
        toSave.setNotes(dbApplication.getNotes());
    }

    public static void keepAddressDBDetails(AddressEntity dbAddress, AddressEntity toSave){
        if(dbAddress != null && dbAddress.getId() != null && toSave != null){
            toSave.setId(dbAddress.getId());
        }
    }

    public static void keepPersonDBDetails(PersonEntity dbPerson, PersonEntity toSave){
        if(dbPerson != null && dbPerson.getId() != null && toSave != null){
            toSave.setId(dbPerson.getId());
        }
    }

    public static void preSaveApplication(ApplicationEntity toSave){
        if(toSave.getDiffDiplomaNames() != null){
            toSave.getDiffDiplomaNames().setApplication(toSave);
            toSave.getDiffDiplomaNames().setApplicationId(toSave.getId());
        }
        if(toSave.getApplicationDocumentReceiveMethods() != null){
            int index = 0;
            for(ApplicationDocumentReceiveMethodEntity documentReceiveMethod: toSave.getApplicationDocumentReceiveMethods()){
                documentReceiveMethod.setApplication(toSave);
                documentReceiveMethod.setId(new ApplicationIdIndexIdEntity(index++, toSave.getId()));
            }
        }
    }

    public static void preSaveClonedApplication(ApplicationEntity application){
        if(application.getStatusHistory() != null){
            application.getStatusHistory().stream().forEach(stat -> stat.setApplication(application));
        }
    }

    public static void preSaveDocumentReceiveMethodDetails(ApplicationEntity toSaveApplication) {
        int idx = 0;
        if (Objects.nonNull(toSaveApplication) && !CollectionUtils.isEmpty(toSaveApplication.getApplicationDocumentReceiveMethods())) {
            for (ApplicationDocumentReceiveMethodEntity entity : toSaveApplication.getApplicationDocumentReceiveMethods()) {
                entity.setApplication(toSaveApplication);
                entity.setId(new ApplicationIdIndexIdEntity(idx++, toSaveApplication.getId()));
            }
        }
    }

    public static LocalDateTime changeFoAppStatus(ApplicationEntity toSave, String userInitiatingChange, FoApplicationStatus newStatus, String changeReasonMessage){
        if(toSave.getStatusHistory() == null) {
            toSave.setStatusHistory(new ArrayList<>());
        }
        AppStatusHistoryEntity hist = new AppStatusHistoryEntity();
        hist.setFoStatus(new ReferenceDataEntity());
        hist.getFoStatus().setPk(new ReferenceDataEntityPK(ReferenceDataDomain.FO_APP_STATUS.name(), newStatus.getCode()));
        hist.setDateCreated(LocalDateTime.now());
        hist.setApplication(toSave);
        hist.setUserCreated(userInitiatingChange);
        hist.setReasonMessage(changeReasonMessage);
        toSave.getStatusHistory().add(hist);
        return hist.getDateCreated();
    }

    public static String getLastSubmitStatus(ApplicationEntity applicationEntity){
        if(applicationEntity.getStatusHistory() != null){
            Optional<AppStatusHistoryEntity> histSubSig = applicationEntity.getStatusHistory().stream().filter(st -> st.getFoStatus() != null)
                    .filter(st -> st.getFoStatus().getPk().getId().equals(FoApplicationStatus.SUBMITTED.getCode()) || st.getFoStatus().getPk().getId().equals(FoApplicationStatus.SUBMITTED_WITH_SIGNATURE.getCode()))
                    .findFirst();
            if(histSubSig.isPresent()){
                return histSubSig.get().getFoStatus().getPk().getId();
            }
        }
        throw new RuntimeException("No submitted or submitted with signature status found for app "+applicationEntity.getId());
    }

    public static void addReceiptEntityToApplication(ApplicationEntity applicationEntity, ApplicationReceiptEntity receiptEntity){
        int maxIndex = 0;
        if(applicationEntity.getReceipts() != null && applicationEntity.getReceipts().size() >0){
            maxIndex = applicationEntity.getReceipts().stream().map(rec -> rec.getId().getIndex()).max(Integer::compareTo).get();
            applicationEntity.getReceipts().stream().filter(rec -> rec.getStatusCode().equals(receiptEntity.getStatusCode())).forEach(rec -> rec.setActive(0));
        }
        maxIndex +=1;
        receiptEntity.setId(new ApplicationIdIndexIdEntity(maxIndex, applicationEntity.getId()));
        if(applicationEntity.getReceipts() == null){
            applicationEntity.setReceipts(new ArrayList<>());
        }
        applicationEntity.getReceipts().add(receiptEntity);
    }
}
