import React, { useEffect } from "react";
import {
  InputFormField,
  SelectFormField,
  GridItem,
  GridContainer,
  FormSection,
  YearFormField,
  AlertSpg,
} from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { cfgEduLevelThunk } from "../../../../../../store/redux/slice/AppData/cfgEduLevel";
import { useTranslation } from "react-i18next";
import UniversityAutocompleteFormField from "./parts/UniversityAutocompleteFormField";
import SpecialityAutocompleteFormField from "./parts/SpecialityAutocompleteFormField";
import { getSpecialitiesAutocomplete } from "../../../../../../services/autocompleteCalls";

const PreviousUniversityDiplomaFormSection = ({ applicationType, applicationSubtype }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const cfgEduLevelThunkState = useAppSelector((state) => {
    return state.AppData.CfgEduLevel;
  });

  useEffect(() => {
    dispatch(cfgEduLevelThunk());
  }, [dispatch]);

  return (
    <FormSection label={"t.previousUniversityDiploma"}>
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={12} md={12}>
          <AlertSpg variant={"standard"} severity={"info"}>
            {t("m.previousUniversityDiploma.info")}
          </AlertSpg>
        </GridItem>
      </GridContainer>
      <GridContainer spacing={4} mt={0}>
        <GridItem>
          <UniversityAutocompleteFormField
            nameField={"previousUniversityDiploma.universityName"}
            nameIdField={"previousUniversityDiploma.universityNameId"}
            labelCode={"l.previousUniversityDiploma.universityName"}
          />
        </GridItem>
        <GridItem>
          <SelectFormField
            fieldName={"previousUniversityDiploma.gainedLevel.id"}
            labelCode={"l.previousUniversityDiploma.gainedLevel"}
            addEmptyOption={true}
            selectOptions={cfgEduLevelThunkState.data
              .filter((cfg) => cfg.applicationType === applicationType && cfg.applicationSubtype === applicationSubtype)
              .map((cfg) => {
                return { value: cfg.eduLevel.id, text: cfg.eduLevel.name, active: cfg.eduLevel.isActive };
              })}
          />
        </GridItem>
        <GridItem>
          <SpecialityAutocompleteFormField
            specialityField={"previousUniversityDiploma.speciality"}
            specialityIdField={null}
            labelCode={"l.previousUniversityDiploma.speciality"}
            autocompleteFn={getSpecialitiesAutocomplete}
          />
        </GridItem>
      </GridContainer>
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={4} md={4}>
          <YearFormField
            fieldName={"previousUniversityDiploma.graduationYear"}
            labelCode={"l.previousUniversityDiploma.graduationYear"}
          />
        </GridItem>
        <GridItem sm={8} md={8}>
          <InputFormField
            fieldName={"previousUniversityDiploma.notes"}
            labelCode={"l.previousUniversityDiploma.notes"}
          />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default PreviousUniversityDiplomaFormSection;
