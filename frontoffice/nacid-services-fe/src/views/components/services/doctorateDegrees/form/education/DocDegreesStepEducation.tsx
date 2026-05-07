import { useTranslation } from "react-i18next";
import { ApplicationSubtype, ApplicationType, BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { FormProvider } from "react-hook-form";
import {
  completeEducationStep,
  setStepEducationEdited,
} from "../../../../../../store/redux/slice/Forms/docDegreesForm";
import { toast } from "react-toastify";
import UniversityFormSection from "../../../common/form/education/UniversityFormSection";
import DiplomaFormSection from "../../../common/form/education/DiplomaFormSection";
import EducationFormSection from "../../../common/form/education/EducationFormSection";
import { Button, Typography } from "@mui/material";
import { createDocEducationValidationSchema } from "../../../../../../yup/doctorateDegrees/docDegreesValidationSchemas";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialDocDegreesEducation } from "../../../../../../init/docDegreesInitialValues";
import EducationBeginningFormFields from "../../../common/form/education/parts/EducationBeginningFormFields";
import EducationPlaceFormSection from "../../../common/form/education/EducationPlaceFormSection";
import useFormReset from "../../../../../../hooks/useFormReset";
import useStepSpecific from "../../../../../../hooks/useStepSpecific";
import DocDissertationFormSection from "./section/DocDissertationFormSection";
import DocProfGroupFormFields from "./parts/DocProfGroupFormFields";
import DocStartEndFormFields from "./parts/DocStartEndFormFields";
import GraduationWayFormSection from "../../../common/form/education/GraduationWayFormSection";

const DocDegreesStepEducation = () => {
  const { t } = useTranslation();

  const docDegreesForm = useAppSelector((state) => {
    return state.Forms.DocDegreesForm;
  });

  const { onSubmit, methods } = useStepSpecific(
    docDegreesForm.educationDetails,
    docDegreesForm.id,
    baseEndpointPaths.docDegrees,
    createDocEducationValidationSchema,
    setStepEducationEdited,
    completeEducationStep,
    initialDocDegreesEducation
  );

  useFormReset(ApplicationSubtype.DOC_DEGREES, docDegreesForm.educationDetails, methods.reset);

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
            sectionLabelCode={"t.education.degree.details"}
            startEndFormFields={<DocStartEndFormFields />}
            qualificationFormFields={null}
            eduBeginningFormFields={
              <EducationBeginningFormFields
                withRecognitionCategory={true}
                profGroupFields={<DocProfGroupFormFields />}
                applicationType={ApplicationType.ACADEMIC_RECOGNITION}
                applicationSubtype={ApplicationSubtype.DOC_DEGREES}
              />
            }
          />
          <GraduationWayFormSection
            required={true}
            applicationType={ApplicationType.ACADEMIC_RECOGNITION}
            applicationSubtype={ApplicationSubtype.DOC_DEGREES}
            labelCode={"l.docGraduationWay"}
          />
          <DocDissertationFormSection />
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

export default DocDegreesStepEducation;
