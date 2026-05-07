import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { FormProvider } from "react-hook-form";
import { toast } from "react-toastify";
import { ApplicationSubtype, BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import { Button, Typography } from "@mui/material";
import { createInquiryDetailsValidationSchema } from "../../../../../../yup/inquiry/inquiryValidationSchemas";
import { completeInquiryStep, setStepInquiryEdited } from "../../../../../../store/redux/slice/Forms/inquiryForm";
import InquiryDetailsFormSection from "./section/InquiryDetailsFormSection";
import useFormReset from "../../../../../../hooks/useFormReset";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialInquiryDetails } from "../../../../../../init/inquiryInitialValues";
import useStepSpecific from "../../../../../../hooks/useStepSpecific";

const InquiryStepInquiry = () => {
  const { t } = useTranslation();

  const inquiryForm = useAppSelector((state) => {
    return state.Forms.InquiryForm;
  });

  const { onSubmit, methods } = useStepSpecific(
    inquiryForm.inquiryDetails,
    inquiryForm.id,
    baseEndpointPaths.inquiry,
    createInquiryDetailsValidationSchema,
    setStepInquiryEdited,
    completeInquiryStep,
    initialInquiryDetails
  );

  useFormReset(ApplicationSubtype.INQUIRY, inquiryForm.inquiryDetails, methods.reset);

  return (
    <BoxSpg>
      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            toast.error(t("m.validation.errors.present"));
            console.log(errors);
          })}
        >
          <InquiryDetailsFormSection />
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
export default InquiryStepInquiry;
