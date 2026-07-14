import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import {
  completeEducationStep,
  setStepEducationEdited,
} from "../../../../../../store/redux/slice/Forms/heRecognitionForm";
import { BoxSpg, DividerSpg, ApplicationType, ApplicationSubtype } from "@duosoftbg/nacid-components";
import { Button, Typography } from "@mui/material";
import { FormProvider } from "react-hook-form";
import RecognitionAimFormSection from "./section/RecognitionAimFormSection";
import { toast } from "react-toastify";
import { createHEEducationValidationSchema } from "../../../../../../yup/higherEducation/heEducationValidationSchemas";
import UniversityFormSection from "../../../common/form/education/UniversityFormSection";
import DiplomaFormSection from "../../../common/form/education/DiplomaFormSection";
import EducationFormSection from "../../../common/form/education/EducationFormSection";
import PreviousUniversityDiplomaFormSection from "../../../common/form/education/PreviousUniversityDiplomaFormSection";
import SpecialityFormSubsection from "../../../common/form/education/SpecialityFormSubsection";
import EducationBeginningFormFields from "../../../common/form/education/parts/EducationBeginningFormFields";
import { initialHERecognitionEducation } from "../../../../../../init/heRecognitionInitialValues";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import EducationPlaceFormSection from "../../../common/form/education/EducationPlaceFormSection";
import useFormReset from "../../../../../../hooks/useFormReset";
import useStepSpecific from "../../../../../../hooks/useStepSpecific";
import { useTranslation } from "react-i18next";
import StartEndFormFields from "../../../common/form/education/parts/StartEndFormFields";
import React from "react";
import GraduationWayFormSection from "../../../common/form/education/GraduationWayFormSection";
import QualificationFormFields from "../../../common/form/education/parts/QualificationFormFields";

const HERecognitionStepEducation = () => {
  const { t } = useTranslation();

  const heRecognitionForm = useAppSelector((state) => {
    return state.Forms.HERecognitionForm;
  });

  const { onSubmit, methods } = useStepSpecific(
    heRecognitionForm.educationDetails,
    heRecognitionForm.id,
    baseEndpointPaths.heRecognition,
    createHEEducationValidationSchema,
    setStepEducationEdited,
    completeEducationStep,
    initialHERecognitionEducation
  );

  useFormReset(ApplicationSubtype.HE_RECOGNITION, heRecognitionForm.educationDetails, methods.reset);

  return (
    <BoxSpg>
      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            console.log(errors);
            toast.error(t("m.validation.errors.present"));
          })}
        >
          <UniversityFormSection />
          <DiplomaFormSection />
          <EducationPlaceFormSection />
          <EducationFormSection
            sectionLabelCode={"t.education.details"}
            startEndFormFields={<StartEndFormFields required={true} />}
            specialityFormSubsection={<SpecialityFormSubsection />}
            qualificationFormFields={<QualificationFormFields />}
            eduBeginningFormFields={
              <EducationBeginningFormFields
                withRecognitionCategory={false}
                profGroupFields={null}
                applicationType={ApplicationType.ACADEMIC_RECOGNITION}
                applicationSubtype={ApplicationSubtype.HE_RECOGNITION}
              />
            }
          />
          <GraduationWayFormSection
            applicationType={ApplicationType.ACADEMIC_RECOGNITION}
            applicationSubtype={ApplicationSubtype.HE_RECOGNITION}
          />
          <PreviousUniversityDiplomaFormSection
            applicationType={ApplicationType.ACADEMIC_RECOGNITION}
            applicationSubtype={ApplicationSubtype.HE_RECOGNITION}
          />
          <RecognitionAimFormSection />

          <DividerSpg my={4} />
          <BoxSpg>
            <Typography align={"left"}>
              <Button type={"submit"} variant={"contained"}>
                {t("l.btn.saveData")}
              </Button>
            </Typography>
          </BoxSpg>
        </form>
      </FormProvider>
    </BoxSpg>
  );
};
export default HERecognitionStepEducation;
