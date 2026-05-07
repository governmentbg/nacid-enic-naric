package bg.duosoft.nacid.backoffice.core.data.util.common;

import lombok.extern.slf4j.Slf4j;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
public class CommonUtils {

    public static boolean areAllFieldsEmpty(Object o) {
        if (Objects.isNull(o)) {
            return true;
        }

        try {
            for (Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(o) != null) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    public static boolean isEmpty(StringKeyNomenclatureBase object) {
        return Objects.isNull(object) || !StringUtils.hasText(object.getId());
    }

    public static boolean isEmpty(ReferenceDataDTO object) {
        return Objects.isNull(object) || !StringUtils.hasText(object.getId());
    }

    public static String selectId(StringKeyNomenclatureBase object) {
        return isEmpty(object) ? null : object.getId();
    }

    public static String selectId(ReferenceDataDTO object) {
        return isEmpty(object) ? null : object.getId();
    }

    public static String appEntryNumLastPart(String entryNumber) {
        if (StringUtils.hasText(entryNumber)) {
            String[] entryNumArray = entryNumber.split("-");
            return entryNumArray[entryNumArray.length - 1];
        }
        return null;
    }

}
