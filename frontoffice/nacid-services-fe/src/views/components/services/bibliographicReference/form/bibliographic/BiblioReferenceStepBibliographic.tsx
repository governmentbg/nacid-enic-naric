import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { FormProvider } from "react-hook-form";
import {
  completeBibliographicStep,
  setStepBibliographicEdited,
} from "../../../../../../store/redux/slice/Forms/biblioReferenceForm";
import { toast } from "react-toastify";
import { ApplicationSubtype, BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import { Button, Typography } from "@mui/material";
import BibliographicReferenceDetailsFormSection from "./section/BibliographicReferenceDetailsFormSection";
import { createBiblioReferenceDetailsValidationSchema } from "../../../../../../yup/bibliographicReference/biblioReferenceValidationSchemas";
import useStepSpecific from "../../../../../../hooks/useStepSpecific";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialBibliographicReferenceDetails } from "../../../../../../init/biblioReferenceInitialValues";
import useFormReset from "../../../../../../hooks/useFormReset";

const BiblioReferenceStepBibliographic = () => {
  const { t } = useTranslation();

  const biblioReferenceForm = useAppSelector((state) => {
    return state.Forms.BiblioReferenceForm;
  });

  const { onSubmit, methods } = useStepSpecific(
    biblioReferenceForm.bibliographicReferenceDetails,
    biblioReferenceForm.id,
    baseEndpointPaths.bibliographicReference,
    createBiblioReferenceDetailsValidationSchema,
    setStepBibliographicEdited,
    completeBibliographicStep,
    initialBibliographicReferenceDetails
  );

  useFormReset(ApplicationSubtype.BIBLIO_REFERENCE, biblioReferenceForm.bibliographicReferenceDetails, methods.reset);

  return (
    <BoxSpg>
      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            toast.error(t("m.validation.errors.present"));
            console.log(errors);
          })}
        >
          <BibliographicReferenceDetailsFormSection />
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
export default BiblioReferenceStepBibliographic;
