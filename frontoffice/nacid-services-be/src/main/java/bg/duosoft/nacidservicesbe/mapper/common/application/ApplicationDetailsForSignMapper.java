package bg.duosoft.nacidservicesbe.mapper.common.application;

import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationDetailsForSignDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationReceiptEntity;
import bg.duosoft.nacidservicesbe.mapper.common.document.ApplicationReceiptMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.02.2023
 * Time: 16:58
 */
@Mapper(componentModel = "spring", uses = {ApplicationSubtypeMapper.class})
public abstract class ApplicationDetailsForSignMapper {

    @Autowired
    private ApplicationReceiptMapper applicationReceiptMapper;

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "applicationSubtype", source = "applicationSubtype.id")
    public abstract ApplicationDetailsForSignDTO toDto(ApplicationEntity applicationEntity);

    @AfterMapping
    public void afterToDto(@MappingTarget ApplicationDetailsForSignDTO target, ApplicationEntity source){
        if(source.getReceipts() != null){
            Optional<ApplicationReceiptEntity> receiptOpt = source.getReceipts().stream().filter(r -> r.getActive() == 1 && r.getStatusCode().equals(FoApplicationStatus.FINALIZED.getCode())).findFirst();
            if(receiptOpt.isEmpty()){
                throw new RuntimeException("Can not map application that does not have active finalized receipt");
            }
            target.setReceipt(applicationReceiptMapper.toDto(receiptOpt.get()).getFile());
        }
    }
}
