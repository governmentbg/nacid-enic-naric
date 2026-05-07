package bg.duosoft.nacid.backoffice.core.data.util.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataDomainEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntityPK;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class ReferenceDataUtils {

    public static void setDefaultDomain(ReferenceDataEntity entity, ReferenceDataDomain domainEnum) {
        if (Objects.nonNull(entity)) {
            entity.getPk().setDomain(domainEnum.domain());

            ReferenceDataDomainEntity referenceDataDomain = entity.getReferenceDataDomain();
            if (Objects.isNull(referenceDataDomain)) {
                entity.setReferenceDataDomain(new ReferenceDataDomainEntity());
            }
            entity.getReferenceDataDomain().setDomain(domainEnum.domain());
        }
    }

    public static boolean hasRefDataId(ReferenceDataEntity refData) {
        if (Objects.isNull(refData)) {
            return false;
        }

        ReferenceDataEntityPK pk = refData.getPk();
        if (Objects.isNull(pk)) {
            return false;
        }

        return StringUtils.hasText(pk.getId());
    }

    public static boolean isEmptyRefDataId(ReferenceDataEntity refData) {
        return !hasRefDataId(refData);
    }


    public static void setDefaultDomain(ReferenceDataDTO dto, ReferenceDataDomain domainEnum) {
        if (Objects.nonNull(dto)) {
            dto.setDomain(domainEnum.domain());
        }
    }

    public static boolean hasRefDataId(ReferenceDataDTO refData) {
        if (Objects.isNull(refData)) {
            return false;
        }

        return StringUtils.hasText(refData.getId());
    }

    public static boolean isEmptyRefDataId(ReferenceDataDTO refData) {
        return !hasRefDataId(refData);
    }

}
