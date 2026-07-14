import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { FormProvider } from "react-hook-form";
import { toast } from "react-toastify";
import { ApplicationSubtype, BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import { Button, Typography } from "@mui/material";
import SuggestionDetailsSection from "./section/SuggestionDetailsSection";
import { createSuggestionDetailsValidationSchema } from "../../../../../../yup/suggestion/suggestionValidationSchemas";
import {
  completeSuggestionStep,
  setStepSuggestionEdited,
} from "../../../../../../store/redux/slice/Forms/suggestionForm";
import useStepSpecific from "../../../../../../hooks/useStepSpecific";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";
import { initialSuggestionDetails } from "../../../../../../init/suggestionInitialValues";

const SuggestionStepSuggestion = () => {
  const { t } = useTranslation();

  const suggestionForm = useAppSelector((state) => {
    return state.Forms.SuggestionForm;
  });

  const { onSubmit, methods } = useStepSpecific(
    suggestionForm.suggestionDetails,
    suggestionForm.id,
    baseEndpointPaths.suggestion,
    createSuggestionDetailsValidationSchema,
    setStepSuggestionEdited,
    completeSuggestionStep,
    initialSuggestionDetails
  );

  useFormReset(ApplicationSubtype.SUGGESTION, suggestionForm.suggestionDetails, methods.reset);

  return (
    <BoxSpg>
      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            toast.error(t("m.validation.errors.present"));
            console.log(errors);
          })}
        >
          <SuggestionDetailsSection />
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
export default SuggestionStepSuggestion;
