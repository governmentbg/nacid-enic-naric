package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.docrec;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.PersonalDocumentType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.accept.DocrecAcceptDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.DocumentReceiveMethodUtils;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.accept.RudiAcceptanceBaseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;


@Slf4j
@Component
@RequiredArgsConstructor
public class DocrecAcceptanceMapper {

    private final RudiAcceptanceBaseMapper baseMapper;

    public RudiApplicationDTO overrideData(DocrecAcceptDTO acceptDTO, RudiApplicationDTO rudiApplication) {
        if (Objects.isNull(rudiApplication)) {
            throw new RuntimeException("[E-APPS ACCEPTANCE] Cannot accept application, because main object is empty!");
        }

        baseMapper.processBaseMapping(acceptDTO, rudiApplication);

        rudiApplication.getApplication().setPersonalDocumentType(new ReferenceDataDTO(ReferenceDataDomain.PERSONAL_DOCUMENT_TYPE.domain(), PersonalDocumentType.ELECTRONIC_IDENTITY.code()));
        rudiApplication.getApplication().setDocumentReceiveMethods(DocumentReceiveMethodUtils.convertToApplicationDocumentReceiveMethod(acceptDTO.getDocumentReceiveMethod()));
        return rudiApplication;
    }


}
