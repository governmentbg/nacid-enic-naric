import {
  ApplicationSubtype,
  ApplicationType,
  BoxSpg,
  DateFormField,
  GridContainer,
  GridItem,
  InputFormField,
  SelectFormField,
} from "@duosoftbg/nacid-components";
import { Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import EducationEntrySpecialityFormFields from "./EducationEntrySpecialityFormFields";
import { useTranslation } from "react-i18next";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { degreeRankThunk } from "../../../../../../../store/redux/slice/AppData/degreeRank";
import { graduationDocTypeThunk } from "../../../../../../../store/redux/slice/AppData/graduationDocType";
import { useWatch } from "react-hook-form";
import { cfgEduLevelThunk } from "../../../../../../../store/redux/slice/AppData/cfgEduLevel";
import EducationEntryQualificationFormFields from "./EducationEntryQualificationFormFields";
import NewEducationInstitutionAutocompleteField from "./NewEducationInstitutionAutocompleteField";
import OldEducationInstitutionAutocompleteField from "./OldEducationInstitutionAutocompleteField";

const EducationEntryFormFields = ({
  hasEduLevel = false,
  hasRank = false,
  hasSpecialityId = false,
  title,
  field,
  specialityAutocompleteFn,
  qualificationAutocompleteFn,
}) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const [graduationDocTypes, setGraduationDocTypes] = useState([]);

  const kind = useWatch({ name: "education.kind" });

  const thunkStateDegreeRank = useAppSelector((state) => {
    return state.AppData.DegreeRank;
  });

  const cfgEduLevelThunkState = useAppSelector((state) => {
    return state.AppData.CfgEduLevel;
  });

  const thunkStateGraduationDocType = useAppSelector((state) => {
    return state.AppData.GraduationDocType;
  });

  useEffect(() => {
    dispatch(degreeRankThunk());
    dispatch(graduationDocTypeThunk());
    dispatch(cfgEduLevelThunk());
  }, [dispatch]);

  useEffect(() => {
    if (kind && thunkStateGraduationDocType.data.length > 0) {
      setGraduationDocTypes(thunkStateGraduationDocType.data.filter((grad) => grad.educationTypes.includes(kind)));
    }
  }, [kind, thunkStateGraduationDocType]);

  return (
    <BoxSpg>
      <BoxSpg mt={4} mb={2}>
        <Typography variant={"h4"} color={"primary"}>
          {t(title)}
        </Typography>
      </BoxSpg>
      <GridContainer>
        <GridItem>
          <NewEducationInstitutionAutocompleteField field={field} />
        </GridItem>
        <GridItem>
          <OldEducationInstitutionAutocompleteField field={field} />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem>
          <EducationEntryQualificationFormFields
            field={field}
            qualificationAutocompleteFn={qualificationAutocompleteFn}
          />
        </GridItem>
        {hasEduLevel && !hasRank && (
          <GridItem>
            <SelectFormField
              fieldName={`education.${field}.eduLevel.id`}
              labelCode={"l.regprof.education.eduLevel"}
              selectOptions={cfgEduLevelThunkState.data
                .filter(
                  (cfg) =>
                    cfg.applicationType === ApplicationType.REGULATED_PROFESSIONS &&
                    (!cfg.applicationSubtype || cfg.applicationSubtype === ApplicationSubtype.REGULATED_PROFESSIONS)
                )
                .map((cfg) => {
                  return { value: cfg.eduLevel.id, text: cfg.eduLevel.name, active: cfg.eduLevel.isActive };
                })}
              addEmptyOption={true}
            />
          </GridItem>
        )}
        {hasRank && !hasEduLevel && (
          <GridItem>
            <SelectFormField
              fieldName={`education.${field}.qualificationRank.id`}
              labelCode={"l.regprof.education.qualificationRank"}
              selectOptions={thunkStateDegreeRank.data.map((option) => {
                return { value: option.id, text: option.name, active: option.isActive };
              })}
              addEmptyOption={true}
            />
          </GridItem>
        )}
      </GridContainer>
      <GridContainer>
        <EducationEntrySpecialityFormFields
          field={field}
          specialityAutocompleteFn={specialityAutocompleteFn}
          hasSpecialityId={hasSpecialityId}
        />
      </GridContainer>
      <GridContainer>
        <GridItem>
          <SelectFormField
            required={true}
            fieldName={`education.${field}.documentKind.id`}
            labelCode={"l.regprof.education.documentKind"}
            selectOptions={graduationDocTypes.map((option) => {
              return { value: option.id, text: option.name, active: option.isActive };
            })}
            addEmptyOption={true}
          />
        </GridItem>
        <GridItem>
          <InputFormField
            fieldName={`education.${field}.documentSeries`}
            labelCode={"l.regprof.education.documentSeries"}
          />
        </GridItem>
        <GridItem>
          <InputFormField
            fieldName={`education.${field}.documentNumber`}
            labelCode={"l.regprof.education.documentNumber"}
          />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem>
          <InputFormField
            fieldName={`education.${field}.documentRegistrationNumber`}
            labelCode={"l.regprof.education.documentRegistrationNumber"}
          />
        </GridItem>
        <GridItem>
          <DateFormField fieldName={`education.${field}.documentDate`} labelCode={"l.regprof.education.documentDate"} />
        </GridItem>
      </GridContainer>
    </BoxSpg>
  );
};
export default EducationEntryFormFields;
