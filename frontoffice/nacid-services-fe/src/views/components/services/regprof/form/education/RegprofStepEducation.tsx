import { useTranslation } from "react-i18next";
import { ApplicationSubtype, BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { FormProvider } from "react-hook-form";
import { completeEducationStep, setStepEducationEdited } from "../../../../../../store/redux/slice/Forms/regprofForm";
import { toast } from "react-toastify";
import { Button, Typography } from "@mui/material";
import CountryFormSection from "./section/CountryFormSection";
import RegprofEducationFormSection from "./section/RegprofEducationFormSection";
import RegprofExperienceFormSection from "./section/RegprofExperienceFormSection";
import ProfessionalQualificationFormSection from "./section/ProfessionalQualificationFormSection";
import { createRegprofEducationDetailsValidationSchema } from "../../../../../../yup/regprof/regprofEducationDetailsValidationSchema";
import { initialRegprofEducationDetails } from "../../../../../../init/regprofInitialValues";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import RegprofApplicationDetailsFormSection from "./section/RegprofApplicationDetailsFormSection";
import useFormReset from "../../../../../../hooks/useFormReset";
import useStepSpecific from "../../../../../../hooks/useStepSpecific";
import RegprofFeesSection from "./section/RegprofFeesSection";

const RegprofStepEducation = () => {
  const { t } = useTranslation();

  const regprofForm = useAppSelector((state) => {
    return state.Forms.RegprofForm;
  });

  const { onSubmit, methods } = useStepSpecific(
    regprofForm.educationDetails,
    regprofForm.id,
    baseEndpointPaths.regprof,
    createRegprofEducationDetailsValidationSchema,
    setStepEducationEdited,
    completeEducationStep,
    initialRegprofEducationDetails
  );

  useFormReset(ApplicationSubtype.REGULATED_PROFESSIONS, regprofForm.educationDetails, methods.reset);

  return (
    <BoxSpg>
      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            console.log(errors);
            toast.error(t("m.validation.errors.present"));
          })}
        >
          <RegprofFeesSection />
          <RegprofApplicationDetailsFormSection />
          <CountryFormSection />
          <RegprofEducationFormSection />
          <RegprofExperienceFormSection />
          <ProfessionalQualificationFormSection />

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
export default RegprofStepEducation;
