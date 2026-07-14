import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import {
  completeBibliographicStep,
  setStepBibliographicEdited,
} from "../../../../../../store/redux/slice/Forms/docDeliveryForm";
import { toast } from "react-toastify";
import { ApplicationSubtype, BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import DocBibliographicDetailsFormSection from "./section/DocBibliographicDetailsFormSection";
import { Button, Typography } from "@mui/material";
import { createBibliographicDetailsValidationSchema } from "../../../../../../yup/documentDelivery/docDeliveryValidationSchemas";
import useStepSpecific from "../../../../../../hooks/useStepSpecific";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";
import { initialBibliographicDetails } from "../../../../../../init/docDeliveryInitialValues";

const DocDeliveryStepBibliographic = () => {
  const { t } = useTranslation();

  const docDeliveryForm = useAppSelector((state) => {
    return state.Forms.DocDeliveryForm;
  });

  const { onSubmit, methods } = useStepSpecific(
    docDeliveryForm.bibliographicDetails,
    docDeliveryForm.id,
    baseEndpointPaths.documentDelivery,
    createBibliographicDetailsValidationSchema,
    setStepBibliographicEdited,
    completeBibliographicStep,
    initialBibliographicDetails
  );

  useFormReset(ApplicationSubtype.DOCUMENT_SERVICE, docDeliveryForm.bibliographicDetails, methods.reset);

  return (
    <BoxSpg>
      <DocBibliographicDetailsFormSection methods={methods} />
      <DividerSpg my={4} />
      <FormProvider {...methods}>
        <form onSubmit={methods.handleSubmit(onSubmit, (errors) => toast.error(t("m.validation.errors.present")))}>
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
export default DocDeliveryStepBibliographic;
