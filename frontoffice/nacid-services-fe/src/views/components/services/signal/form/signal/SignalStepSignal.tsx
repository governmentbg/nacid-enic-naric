import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { FormProvider } from "react-hook-form";
import { toast } from "react-toastify";
import { ApplicationSubtype, BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import { Button, Typography } from "@mui/material";
import SignalDetailsSection from "./section/SignalDetailsSection";
import { completeSignalStep, setStepSignalEdited } from "../../../../../../store/redux/slice/Forms/signalForm";
import { createSignalDetailsValidationSchema } from "../../../../../../yup/signal/signalValidationSchemas";
import useStepSpecific from "../../../../../../hooks/useStepSpecific";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";
import { initialSignalDetails } from "../../../../../../init/signalInitialValues";

const SignalStepSignal = () => {
  const { t } = useTranslation();

  const signalForm = useAppSelector((state) => {
    return state.Forms.SignalForm;
  });

  const { onSubmit, methods } = useStepSpecific(
    signalForm.signalDetails,
    signalForm.id,
    baseEndpointPaths.signal,
    createSignalDetailsValidationSchema,
    setStepSignalEdited,
    completeSignalStep,
    initialSignalDetails
  );

  useFormReset(ApplicationSubtype.SIGNAL, signalForm.signalDetails, methods.reset);

  return (
    <BoxSpg>
      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            toast.error(t("m.validation.errors.present"));
            console.log(errors);
          })}
        >
          <SignalDetailsSection />
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
export default SignalStepSignal;
