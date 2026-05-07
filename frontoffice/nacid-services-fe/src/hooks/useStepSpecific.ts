import { useTranslation } from "react-i18next";
import useAppDispatch from "./redux/base/useAppDispatch";
import { AsyncCallArgs, fillNonNullValues, useAsyncCall, useReactHookForm } from "@duosoftbg/nacid-components";
import { useEffect } from "react";
import { saveSpecificDetails } from "../services/serviceCalls";
import { toast } from "react-toastify";

const useStepSpecific = (
  specificDetails,
  id,
  basePath,
  createSpecificDetailsSchema,
  setStepSpecificEdited,
  completeStepSpecific,
  initialSpecificDetails
) => {
  const { t } = useTranslation();

  const dispatch = useAppDispatch();

  const { asyncCall } = useAsyncCall();

  const { methods } = useReactHookForm({
    defaultValues: specificDetails,
    validationSchema: createSpecificDetailsSchema,
  });

  const reset = methods.reset;

  useEffect(() => {
    dispatch(setStepSpecificEdited(methods.formState.isDirty));
  }, [dispatch, methods.formState.isDirty, setStepSpecificEdited]);

  useEffect(() => {
    reset(specificDetails, { keepIsSubmitted: true, keepSubmitCount: true });
  }, [reset, specificDetails]);

  const onSubmit = (values) => {
    const asyncEducationSubmit: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: saveSpecificDetails(basePath, id, values),
      reactHooksForm: { methods },
      onSuccess: (response) => {
        const updatedResponseData = { ...initialSpecificDetails };
        fillNonNullValues(response.data, updatedResponseData);

        dispatch(completeStepSpecific(updatedResponseData));
        toast.success(t("m.save.data.success"));
      },
    };
    asyncCall(asyncEducationSubmit);
  };

  return {
    onSubmit,
    methods,
  };
};
export default useStepSpecific;
