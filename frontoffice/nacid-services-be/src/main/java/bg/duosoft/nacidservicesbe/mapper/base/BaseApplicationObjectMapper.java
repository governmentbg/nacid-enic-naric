package bg.duosoft.nacidservicesbe.mapper.base;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ApplicationSubtypeEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidservicesbe.domain.entity.base.FullApplicationEntityBase;
import bg.duosoft.nacidservicesbe.mapper.common.document.ApplicationReceiptMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.AttachedDocumentMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.06.2023
 * Time: 18:08
 */
public abstract class BaseApplicationObjectMapper<E extends FullApplicationEntityBase, D extends CommonApplicationDTO> extends BaseObjectMapper<E, D> {

    @Autowired
    private ApplicationTypeMapper applicationTypeMapper;

    @Autowired
    private ApplicationSubtypeMapper applicationSubtypeMapper;

    @Autowired
    private AttachedDocumentMapper attachedDocumentMapper;


    protected void afterToApplicationEntity(E target, D source){
        target.getApplication().setApplicationSubtype(new ApplicationSubtypeEntity());
        target.getApplication().getApplicationSubtype().setId(applicationSubtypeMapper.toEntity(source.getApplicationSubtype()));
        target.getApplication().getApplicationSubtype().setName(source.getApplicationSubtypeName());
        target.getApplication().setApplicationTypeCode(applicationTypeMapper.toEntity(source.getApplicationType()));

        target.getApplication().setTempNumber(source.getTempNumber());
        target.getApplication().setAttachedDocs(attachedDocumentMapper.toEntityList(source.getDocumentDetails().getAttachments()));
    }

}
