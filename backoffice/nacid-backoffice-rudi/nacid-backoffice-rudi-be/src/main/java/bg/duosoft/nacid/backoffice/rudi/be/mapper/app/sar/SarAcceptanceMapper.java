package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.sar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.accept.SarAcceptDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.DocumentReceiveMethodUtils;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.accept.RudiAcceptanceBaseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Slf4j
@Component
@RequiredArgsConstructor
public class SarAcceptanceMapper {

    private final RudiAcceptanceBaseMapper baseMapper;

    public RudiApplicationDTO overrideData(SarAcceptDTO acceptDTO, RudiApplicationDTO rudiApplication) {
        if (Objects.isNull(rudiApplication)) {
            throw new RuntimeException("[E-APPS ACCEPTANCE] Cannot accept application, because main object is empty!");
        }

        baseMapper.processBaseMapping(acceptDTO, rudiApplication);
        setDiplomaOwner(acceptDTO, rudiApplication);

        return rudiApplication;
    }

    public void setDiplomaOwner(SarAcceptDTO acceptDTO, RudiApplicationDTO rudiApplication) {
        TrainingCourseDTO trainingCourse = rudiApplication.getTrainingCourse();
        if (Objects.isNull(trainingCourse)) {
            rudiApplication.setTrainingCourse(new TrainingCourseDTO());
        }

        Integer diplomaOwnerId = acceptDTO.getDiplomaOwnerId();
        if (Objects.isNull(diplomaOwnerId)) {
            rudiApplication.getTrainingCourse().setDiplomaOwner(null);
            return;
        }
        rudiApplication.getApplication().setDocumentReceiveMethods(DocumentReceiveMethodUtils.convertToApplicationDocumentReceiveMethod(acceptDTO.getDocumentReceiveMethod()));
        rudiApplication.getTrainingCourse().setDiplomaOwner(baseMapper.selectPersonById(diplomaOwnerId));
    }


}
