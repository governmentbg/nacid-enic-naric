import {
  AsyncCallArgs,
  CheckboxFormField,
  FormSection,
  FreeSoloAutocompleteFromViewFormField,
  GridContainer,
  GridItem,
  GridSpg,
  InputFormField,
  NomenclatureAutocompleteFormField,
  SectionArrayFormFieldControl,
  SelectFormField,
  START_DATE,
  useAsyncCall,
  YearFormField,
} from "@duosoftbg/nacid-components";
import React, { useEffect, useState } from "react";
import { useFormContext, useWatch } from "react-hook-form";
import {
  AppSubTypeCode,
  AppType,
  AppTypeCode,
  CoreApiServicesBase,
  durationUnitsThunk,
  TrainingForm,
  trainingFormsThunk,
} from "@duosoftbg/nacid-backoffice-components";
import { useSelector } from "react-redux";
import TrainingLocationFormFields from "./components/TrainingLocationFormFields";
import { trainingLocationInitialValues } from "../../../../../../../../init/trainingLocation/trainingLocationInitialValues";
import { Typography } from "@mui/material";
import { useTranslation } from "react-i18next";
import {
  getOriginalEduLevelsFreeSolo,
  getOriginalEduLevelTranslationsFreeSolo,
  getOriginalQualificationsFreeSolo,
  getQualificationsFreeSolo,
} from "../../../../../../../../axios/api/services";
import TrainingCourseSpecialities from "../../../components/TrainingCourseSpecialities";
import GraduationDocTypeField from "../../../components/GraduationDocTypeFiled";
import ProfGroupFields from "../../../components/ProfGroupFileds";
import BolognaCycleFields from "./components/BolognaCycleFields";
import NationalQualificationsFields from "./components/NationalQualificationsFields";
import EuropeanQualificationsFields from "./components/EuropeanQualificationsFields";

type BaseEducationSectionProps = {
  appType: AppType;
  titleSection?: string;
  baseUniversityIdPointer?: string;
};

const EducationSection = ({
  titleSection = "t.base.education.details",
  baseUniversityIdPointer = "baseUniversityId",
  appType,
}: BaseEducationSectionProps) => {
  const { t } = useTranslation();
  const { getValues } = useFormContext();
  const tempDataKey = `${appType}-${getValues("applicationId")}`;

  return (
    <>
      <FormSection label={titleSection}>
        <GridSpg container spacing={1}>
          <GridSpg item xs={12}>
            <GridContainer spacing={4} mt={0}>
              <GridItem sm={12} md={12}>
                <SectionArrayFormFieldControl
                  field={"trainingLocations"}
                  renderFormFields={(index, key) => {
                    return <TrainingLocationFormFields index={index} baseField={"trainingLocations"} key={key} />;
                  }}
                  initialValues={trainingLocationInitialValues}
                  addBtnLabelCode={"l.btn.training.locations.add"}
                  removeBtnLabelCode={"l.btn.training.locations.remove"}
                  formLabelCode={"l.trainingLocations"}
                  titlePosition={"global"}
                  removeBtnPosition={"right"}
                  withDivider={false}
                />
              </GridItem>
              <GridItem sm={12} md={12}>
                <Typography variant={"h6"} color={"primary"}>
                  {t("l.original.edu.level")}
                </Typography>
              </GridItem>
              {appType !== AppType.UDIREC_APPLICATION && (
                <>
                  <RecognitionCategoryField
                    applicationSubTypeCode={
                      appType === AppType.SAR_APPLICATION ? AppSubTypeCode.SAR : AppSubTypeCode.DOCREC
                    }
                  />
                  <GridItem sm={6} md={6} />
                </>
              )}
              <GridItem sm={6} md={6}>
                <FreeSoloAutocompleteFromViewFormField
                  fieldName={"originalEduLevelTranslated"}
                  autocompleteFn={getOriginalEduLevelTranslationsFreeSolo}
                  labelCode={"l.originalEduLevelTranslated"}
                  inputMinSearchLength={1}
                />
              </GridItem>
              <GridItem sm={6} md={6}>
                <FreeSoloAutocompleteFromViewFormField
                  fieldName={"originalEduLevelName"}
                  autocompleteFn={getOriginalEduLevelsFreeSolo}
                  labelCode={"l.originalEduLevelName"}
                  inputMinSearchLength={1}
                />
              </GridItem>
              {appType !== AppType.DOCREC_APPLICATION && <TrainingCourseSpecialities sectionTitle={"l.specialities"} />}
              <GridItem sm={12} md={12}>
                <Typography variant={"h6"} color={"primary"}>
                  {t("l.professional.qualification")}
                </Typography>
              </GridItem>
              <GridItem sm={6} md={6}>
                <FreeSoloAutocompleteFromViewFormField
                  fieldName={"qualification"}
                  autocompleteFn={getQualificationsFreeSolo}
                  labelCode={"l.professional.qualification.translation"}
                  inputMinSearchLength={1}
                />
              </GridItem>
              <GridItem sm={6} md={6}>
                <FreeSoloAutocompleteFromViewFormField
                  fieldName={"originalQualification"}
                  autocompleteFn={getOriginalQualificationsFreeSolo}
                  labelCode={"l.professional.qualification.name"}
                  inputMinSearchLength={1}
                />
              </GridItem>
              <ProfGroupFields />
              <GridItem sm={12} md={12}>
                <Typography variant={"h6"} color={"primary"}>
                  {t("t.additional.data")}
                </Typography>
              </GridItem>
              <BolognaCycleFields />
              <NationalQualificationsFields />
              <EuropeanQualificationsFields />
              <GridItem sm={12} md={12}>
                <Typography variant={"h6"} color={"primary"}>
                  {t("l.education.data")}
                </Typography>
              </GridItem>
              <TrainingStartEndFields />
              <GridItem sm={3} md={3}>
                <InputFormField fieldName={"trainingDuration"} labelCode={"l.trainingDuration"} />
              </GridItem>
              <DurationUnitFields />
              <GridItem sm={3} md={3}>
                <InputFormField fieldName={"credits"} labelCode={"l.credits"} />
              </GridItem>
              <GridItem sm={3} md={3}>
                <InputFormField fieldName={"ectsCredits"} labelCode={"l.ectsCredits"} />
              </GridItem>
              <GridItem sm={3} md={3}>
                <InputFormField fieldName={"creditHours"} labelCode={"l.creditHours"} />
              </GridItem>
              <TrainingFormFields />
              {appType !== AppType.DOCREC_APPLICATION && (
                <>
                  <GraduationDocTypeField tempDataKey={tempDataKey} baseUniversityIdPointer={baseUniversityIdPointer} />
                </>
              )}
              <GridItem sm={12} md={12}>
                <Typography variant={"h6"} color={"primary"}>
                  {t("l.graduationWay")}
                </Typography>
              </GridItem>
              {appType !== AppType.DOCREC_APPLICATION && (
                <>
                  <GridItem sm={3} md={3}>
                    <CheckboxFormField fieldName={"graduationWayThesis"} labelCode={"l.graduationWayThesis"} />
                  </GridItem>
                  <GridItem sm={3} md={3}>
                    <CheckboxFormField fieldName={"graduationWayExam"} labelCode={"l.graduationWayExam"} />
                  </GridItem>
                  <GridItem sm={3} md={3}>
                    <CheckboxFormField
                      fieldName={"graduationWayThesisAndExam"}
                      labelCode={"l.graduationWayThesisAndExam"}
                    />
                  </GridItem>
                </>
              )}
              {appType === AppType.DOCREC_APPLICATION && (
                <GridItem sm={3} md={3}>
                  <CheckboxFormField
                    fieldName={"graduationWayDissertation"}
                    labelCode={"l.graduationWayDissertation"}
                  />
                </GridItem>
              )}
              <OtherGraduationWayFields />
            </GridContainer>
          </GridSpg>
        </GridSpg>
      </FormSection>
    </>
  );
};

const TrainingFormFields = () => {
  const { getValues, setValue } = useFormContext();

  const trainingFormsThunkState = useSelector((state) => {
    return state["ThunkData"].trainingForms;
  });

  const trainingFormId = useWatch({ name: "trainingForm.id" });

  useEffect(() => {
    if (trainingFormId !== TrainingForm.OTHER) {
      setValue("trainingFormNotes", "");
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [trainingFormId]);

  return (
    <>
      <GridItem sm={3} md={3}>
        <NomenclatureAutocompleteFormField
          required={false}
          initialValue={getValues("trainingForm.id")}
          fieldName={"trainingForm.id"}
          labelCode={"l.trainingForm"}
          thunkFn={trainingFormsThunk}
          thunkState={trainingFormsThunkState}
        />
      </GridItem>
      {trainingFormId === TrainingForm.OTHER && (
        <GridItem sm={12} md={12}>
          <InputFormField fieldName={"trainingFormNotes"} labelCode={"l.trainingFormNotes"} required={true} />
        </GridItem>
      )}
    </>
  );
};

const RecognitionCategoryField = ({ applicationSubTypeCode }) => {
  const { asyncCall } = useAsyncCall();
  const [recognitionCategoriesOptions, setRecognitionCategoriesOptions] = useState([]);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: CoreApiServicesBase.selectCfgRecognitionCategories(AppTypeCode.RUDI, applicationSubTypeCode),
      onSuccess: (response) => {
        setRecognitionCategoriesOptions(response);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line
  }, [applicationSubTypeCode]);

  return (
    <>
      <GridItem sm={6} md={6}>
        <SelectFormField
          required={false}
          isDisabled={false}
          fieldName={"recognitionCategory.id"}
          labelCode={"l.recognitionCategory"}
          addEmptyOption={true}
          selectOptions={recognitionCategoriesOptions}
        />
      </GridItem>
    </>
  );
};

const DurationUnitFields = () => {
  const { getValues } = useFormContext();

  const durationUnitsThunkState = useSelector((state) => {
    return state["ThunkData"].durationUnits;
  });

  return (
    <GridItem sm={3} md={3}>
      <NomenclatureAutocompleteFormField
        required={false}
        initialValue={getValues("durationUnit.id")}
        fieldName={"durationUnit.id"}
        labelCode={"l.durationUnit"}
        thunkFn={durationUnitsThunk}
        thunkState={durationUnitsThunkState}
      />
    </GridItem>
  );
};

const OtherGraduationWayFields = () => {
  const { setValue } = useFormContext();

  const graduationWayOther = useWatch({ name: "graduationWayOther" });

  useEffect(() => {
    if (!graduationWayOther) {
      setValue("graduationWayNotes", "");
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [graduationWayOther]);

  return (
    <>
      <GridItem sm={3} md={3}>
        <CheckboxFormField fieldName={"graduationWayOther"} labelCode={"l.graduationWayOther"} />
      </GridItem>
      {graduationWayOther && (
        <GridItem sm={12} md={12}>
          <InputFormField fieldName={"graduationWayNotes"} labelCode={"l.graduationWayNotes"} />
        </GridItem>
      )}
    </>
  );
};

const TrainingStartEndFields = () => {
  const trainingStart = useWatch({ name: "trainingStart" });
  const trainingEnd = useWatch({ name: "trainingEnd" });
  const diplomaDate = useWatch({ name: "diplomaDate" });

  return (
    <>
      <GridItem sm={3} md={3}>
        <YearFormField
          fieldName={"trainingStart"}
          labelCode={"l.trainingStart"}
          maxDate={trainingEnd ? new Date(trainingEnd, 0, 1) : new Date()}
        />
      </GridItem>
      <GridItem sm={3} md={3}>
        <YearFormField
          fieldName={"trainingEnd"}
          labelCode={"l.trainingEnd"}
          minDate={trainingStart ? new Date(trainingStart, 0, 1) : START_DATE}
          maxDate={diplomaDate ? new Date(diplomaDate.substr(diplomaDate.length - 4), 0, 1) : new Date()}
        />
      </GridItem>
    </>
  );
};

export default EducationSection;
