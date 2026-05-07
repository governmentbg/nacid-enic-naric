import { useTranslation } from "react-i18next";
import useAppDispatch from "./redux/base/useAppDispatch";
import {
  AsyncCallArgs,
  fillNonNullValues,
  THUNK_STATUS,
  useAsyncCall,
  useReactHookForm,
} from "@duosoftbg/nacid-components";
import useAppSelector from "./redux/base/useAppSelector";
import { useEffect } from "react";
import { saveApplicantDetails } from "../services/serviceCalls";
import { toast } from "react-toastify";
import { fillUserDetailsInForm } from "../utils/userUtils";

const useStepApplicant = (
  form,
  basePath,
  createValidationSchema,
  setStepApplicantEdited,
  setRequestIdentifier,
  completeApplicantStep,
  initialApplicantDetails,
  hasCompanyApplicant,
  hasUniversityApplicant,
  hasRepresentativeCompanyIdentifier
) => {
  const { t } = useTranslation();

  const dispatch = useAppDispatch();

  const { asyncCall } = useAsyncCall();

  const stepCompleted = form.steps[0].completed;

  const loggedUser = useAppSelector((state) => {
    return state.AppData.LoggedUser;
  });

  const receiveResult = useAppSelector((state) => {
    return state.AppData.ReceiveResult;
  });

  const { methods } = useReactHookForm<any>({
    defaultValues: form.applicantDetails,
    validationSchema: createValidationSchema,
  });

  const { reset, setValue, getValues } = methods;

  useEffect(() => {
    dispatch(setStepApplicantEdited(methods.formState.isDirty));
  }, [dispatch, methods.formState.isDirty, setStepApplicantEdited]);

  useEffect(() => {
    reset(form.applicantDetails, { keepIsSubmitted: true, keepSubmitCount: true });
  }, [reset, form.applicantDetails]);

  useEffect(() => {
    fillUserDetailsInForm(
      reset,
      initialApplicantDetails,
      stepCompleted,
      loggedUser,
      hasCompanyApplicant,
      hasUniversityApplicant,
      receiveResult.data
    );
    if (hasRepresentativeCompanyIdentifier) {
      if (
        loggedUser.status === THUNK_STATUS.FULFILLED &&
        loggedUser.data.userDetails.isRepresentative &&
        loggedUser.data.userDetails.representativeType === "COMPANY_REPRESENTATIVE" &&
        loggedUser.data.userDetails.representedCompany &&
        (!getValues("representativeCompanyIdentifier") || getValues("representativeCompanyIdentifier") === "")
      ) {
        setValue("representativeCompanyIdentifier", loggedUser.data.userDetails.representedCompany);
      }
    }
  }, [
    reset,
    setValue,
    getValues,
    stepCompleted,
    loggedUser,
    hasCompanyApplicant,
    hasUniversityApplicant,
    hasRepresentativeCompanyIdentifier,
    initialApplicantDetails,
    receiveResult,
  ]);

  const onSubmit = (values) => {
    const asyncApplicantSubmit: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: saveApplicantDetails(basePath, form.id, values),
      reactHooksForm: { methods },
      onSuccess: (response) => {
        dispatch(setRequestIdentifier(response.data.applicationId));

        const updatedResponseData = { ...initialApplicantDetails };
        fillNonNullValues(response.data, updatedResponseData);

        dispatch(completeApplicantStep(updatedResponseData));
        toast.success(t("m.save.data.success"));
      },
    };
    asyncCall(asyncApplicantSubmit);
  };

  return {
    onSubmit,
    methods,
  };
};
export default useStepApplicant;
