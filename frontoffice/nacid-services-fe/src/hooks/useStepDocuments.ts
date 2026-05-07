import { useTranslation } from "react-i18next";
import useAppDispatch from "./redux/base/useAppDispatch";
import { AsyncCallArgs, useAsyncCall, useReactHookForm } from "@duosoftbg/nacid-components";
import { createDocumentsValidationSchema } from "../yup/common/document/documentValidationSchemas";
import { useEffect } from "react";
import { saveDocumentDetails } from "../services/serviceCalls";
import { toast } from "react-toastify";

const useStepDocuments = (form, basePath, completeDocumentStep, setStepDocumentsEdited) => {
  const { t } = useTranslation();

  const dispatch = useAppDispatch();

  const { asyncCall } = useAsyncCall();

  const { methods } = useReactHookForm({
    defaultValues: form.documentDetails,
    validationSchema: createDocumentsValidationSchema,
  });

  const reset = methods.reset;

  useEffect(() => {
    reset(form.documentDetails, { keepIsSubmitted: true, keepSubmitCount: true });
  }, [reset, form.documentDetails]);

  const onSubmit = (values) => {
    const asyncDocumentsSubmit: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: saveDocumentDetails(basePath, form.id, values),
      reactHooksForm: { methods },
      onSuccess: (response) => {
        dispatch(completeDocumentStep(response.data));
        dispatch(setStepDocumentsEdited(false));
        toast.success(t("m.save.data.success"));
      },
    };
    asyncCall(asyncDocumentsSubmit);
  };

  return {
    onSubmit,
    methods,
  };
};
export default useStepDocuments;
