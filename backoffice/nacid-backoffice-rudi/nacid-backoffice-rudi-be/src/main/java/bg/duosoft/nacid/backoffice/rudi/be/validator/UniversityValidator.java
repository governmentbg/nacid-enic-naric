package bg.duosoft.nacid.backoffice.rudi.be.validator;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.FacultyDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.validation.address.AddressFields;
import bg.duosoft.nacid.backoffice.core.data.validation.address.AddressValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class UniversityValidator implements Validator<UniversityDTO> {

    private final AddressValidator addressValidator;

    @Override
    public List<ValidationError> validate(UniversityDTO obj, Object... objects) {

        Boolean isCreate = (Boolean) objects[0];

        List<AddressFields> requiredFields = new ArrayList<>();
        requiredFields.add(AddressFields.COUNTRY);
        requiredFields.add(AddressFields.SETTLEMENT);
        requiredFields.add(AddressFields.CITY);
        if (!isCreate) {
            requiredFields.add(AddressFields.ADDRESS);
            requiredFields.add(AddressFields.PHONE);
        }

        List<ValidationError> errors = new ArrayList<>(addressValidator.validateRequiredFields(obj.getAddress(), requiredFields));

        rejectIfTrue(errors, !StringUtils.hasText(obj.getBgName()), "bgName", "validation.field.required");
        if (StringUtils.hasText(obj.getBgName())) {
            rejectIfTrue(errors, obj.getBgName().length() > 255, "bgName", "validation.charCount.invalid.255");
        }

        if (!isCreate) {
            rejectIfTrue(errors, !StringUtils.hasText(obj.getOrgName()), "orgName", "validation.field.required");
        }
        if (StringUtils.hasText(obj.getOrgName())) {
            rejectIfTrue(errors, obj.getOrgName().length() > 255, "orgName", "validation.charCount.invalid.255");
        }

        if (StringUtils.hasText(obj.getWebSite())) {
            rejectIfTrue(errors, obj.getWebSite().length() > 255, "webSite", "validation.charCount.invalid.255");
        }

        rejectIfEmptyBoolean(errors, obj.getIsActive(), "isActive", "validation.field.required");

        AddressDTO address = obj.getAddress();
        rejectIfTrue(errors, Objects.isNull(address), "address", "validation.field.required");

        if (!CollectionUtils.isEmpty(obj.getFaculties())) {
            for (FacultyDTO facultyDTO : obj.getFaculties()) {
                rejectIfEmptyBoolean(errors, facultyDTO.getIsActive(), "faculty.isActive", "validation.field.required");
                rejectIfTrue(errors, !StringUtils.hasText(facultyDTO.getName()), "faculty.name", "validation.field.required");
//                rejectIfTrue(errors, !StringUtils.hasText(facultyDTO.getOriginalName()), "faculty.originalName", "validation.field.required");
                if (StringUtils.hasText(facultyDTO.getName())) {
                    rejectIfTrue(errors, facultyDTO.getName().length() > 255, "faculty.name", "validation.charCount.invalid.255");
                }
                if (StringUtils.hasText(facultyDTO.getOriginalName())) {
                    rejectIfTrue(errors, facultyDTO.getOriginalName().length() > 255, "faculty.originalName", "validation.charCount.invalid.255");
                }
            }
        }

        return errors;
    }
}
