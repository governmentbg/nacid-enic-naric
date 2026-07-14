import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { FormProvider } from "react-hook-form";
import { completeEducationStep, setStepEducationEdited } from "../../../../../../store/redux/slice/Forms/uniChecksForm";
import { toast } from "react-toastify";
import { ApplicationSubtype, ApplicationType, BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import { Button, Typography } from "@mui/material";
import ApplicationDetailsFormSection from "./section/ApplicationDetailsFormSection";
import UniversityFormSection from "../../../common/form/education/UniversityFormSection";
import DiplomaFormSection from "../../../common/form/education/DiplomaFormSection";
import EducationFormSection from "../../../common/form/education/EducationFormSection";
import ApplicantIncomingNumberFormSection from "./section/ApplicantIncomingNumberFormSection";
import DiplomaHolderFormSection from "./section/DiplomaHolderFormSection";
import EducationBeginningFormFields from "../../../common/form/education/parts/EducationBeginningFormFields";
import { createUniChecksEducationValidationSchema } from "../../../../../../yup/uniChecks/uniChecksEducationDetailsValidationSchema";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialUniChecksEducationDetails } from "../../../../../../init/uniChecksInitialValues";
import EducationPlaceFormSection from "../../../common/form/education/EducationPlaceFormSection";
import useFormReset from "../../../../../../hooks/useFormReset";
import useStepSpecific from "../../../../../../hooks/useStepSpecific";
import UniChecksFeesSection from "./section/UniChecksFeesSection";
import StartEndFormFields from "../../../common/form/education/parts/StartEndFormFields";
import React from "react";
import GraduationWayFormSection from "../../../common/form/education/GraduationWayFormSection";
import QualificationFormFields from "../../../common/form/education/parts/QualificationFormFields";
import UniChecksSpecialityFormSubsection from "./section/UniChecksSpecialityFormSubsection";

const UniChecksStepEducation = () => {
  const { t } = useTranslation();

  const uniChecksForm = useAppSelector((state) => {
    return state.Forms.UniChecksForm;
  });

  const { onSubmit, methods } = useStepSpecific(
    uniChecksForm.educationDetails,
    uniChecksForm.id,
    baseEndpointPaths.uniChecks,
    createUniChecksEducationValidationSchema,
    setStepEducationEdited,
    completeEducationStep,
    initialUniChecksEducationDetails
  );

  useFormReset(ApplicationSubtype.UNI_CHECKS, uniChecksForm.educationDetails, methods.reset);

  return (
    <BoxSpg>
      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            console.log(errors);
            toast.error(t("m.validation.errors.present"));
          })}
        >
          <UniChecksFeesSection />
          <ApplicationDetailsFormSection />
          <ApplicantIncomingNumberFormSection />
          <DiplomaHolderFormSection />
          <UniversityFormSection />
          <DiplomaFormSection />
          <EducationPlaceFormSection />
          <EducationFormSection
            sectionLabelCode={"t.education.degree.details"}
            startEndFormFields={<StartEndFormFields required={true} />}
            specialityFormSubsection={<UniChecksSpecialityFormSubsection />}
            qualificationFormFields={<QualificationFormFields />}
            eduBeginningFormFields={
              <EducationBeginningFormFields
                withRecognitionCategory={true}
                profGroupFields={null}
                applicationType={ApplicationType.ACADEMIC_RECOGNITION}
                applicationSubtype={ApplicationSubtype.UNI_CHECKS}
              />
            }
          />
          <GraduationWayFormSection
            applicationType={ApplicationType.ACADEMIC_RECOGNITION}
            applicationSubtype={ApplicationSubtype.UNI_CHECKS}
          />
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
export default UniChecksStepEducation;
