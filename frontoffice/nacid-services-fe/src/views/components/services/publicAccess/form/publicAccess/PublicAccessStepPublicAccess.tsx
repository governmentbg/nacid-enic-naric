import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import useStepSpecific from "../../../../../../hooks/useStepSpecific";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import {
  completePublicAccessStep,
  setStepePublicAccessEdited,
} from "../../../../../../store/redux/slice/Forms/publicAccessForm";
import { initialSuggestionDetails } from "../../../../../../init/suggestionInitialValues";
import useFormReset from "../../../../../../hooks/useFormReset";
import { ApplicationSubtype, BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { toast } from "react-toastify";
import { Button, Typography } from "@mui/material";
import { createPublicAccessDetailsValidationSchema } from "../../../../../../yup/publicAccess/publicAccessValidationSchemas";
import PublicAccessDetailsSection from "./section/PublicAccessDetailsSection";

const PublicAccessStepPublicAccess = () => {
  const { t } = useTranslation();

  const publicAcessForm = useAppSelector((state) => {
    return state.Forms.PublicAccessForm;
  });

  const { onSubmit, methods } = useStepSpecific(
    publicAcessForm.publicAccessDetails,
    publicAcessForm.id,
    baseEndpointPaths.publicAccess,
    createPublicAccessDetailsValidationSchema,
    setStepePublicAccessEdited,
    completePublicAccessStep,
    initialSuggestionDetails
  );

  useFormReset(ApplicationSubtype.PUBLIC_ACCESS, publicAcessForm.publicAccessDetails, methods.reset);

  return (
    <BoxSpg>
      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            toast.error(t("m.validation.errors.present"));
            console.log(errors);
          })}
        >
          <PublicAccessDetailsSection />
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
export default PublicAccessStepPublicAccess;
