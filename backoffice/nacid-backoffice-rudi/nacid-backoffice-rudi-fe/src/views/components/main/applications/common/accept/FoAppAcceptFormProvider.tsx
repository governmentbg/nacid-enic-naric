import {
  AsyncCallArgs,
  BlockFormBackdrop,
  BoxSpg,
  ConfirmSubmitDialog,
  DividerSpg,
  SubmitFormButton,
  useReactHookForm,
  useScroll,
  useScrollOnValidationError,
  WithChildren,
} from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { useTranslation } from "react-i18next";
import { toast } from "react-toastify";
import React, { useState } from "react";
import { acceptFoApplication } from "../../../../../../axios/api/services";
import { useNavigate, useParams } from "react-router-dom";
import { AppType, NavigationUtils } from "@duosoftbg/nacid-backoffice-components";
import { vDocrecFoAppsAcceptSchema } from "../../../../../../yup/schema/applications/docrec/schemas";
import { vSarFoAppsAcceptSchema } from "../../../../../../yup/schema/applications/sar/schemas";
import { vUdirecFoAppsAcceptSchema } from "../../../../../../yup/schema/applications/udirec/schemas";

const selectValidationSchema = (appType: AppType) => {
  switch (appType) {
    case AppType.SAR_APPLICATION: {
      return vSarFoAppsAcceptSchema;
    }
    case AppType.UDIREC_APPLICATION: {
      return vUdirecFoAppsAcceptSchema;
    }
    case AppType.DOCREC_APPLICATION: {
      return vDocrecFoAppsAcceptSchema;
    }
  }
  return null;
};

type SarAcceptFormProviderProps = WithChildren<{
  appType: AppType;
  initialData: any;
  activeTab: number;
}>;

const FoAppAcceptFormProvider = ({ activeTab, appType, initialData, children }: SarAcceptFormProviderProps) => {
  const navigate = useNavigate();
  const { id } = useParams();
  const { t } = useTranslation();
  const { scrollToTop } = useScroll();
  const [confirmModalState, setConfirmModalState] = useState({ open: false, submitFn: null });

  const { methods, handleSubmit } = useReactHookForm({
    defaultValues: initialData,
    validationSchema: selectValidationSchema(appType),
  });

  useScrollOnValidationError({ methods });

  const onSubmit = (formData) => {
    const asyncCallArgs: AsyncCallArgs = {
      withGlobalBackdrop: true,
      reactHooksForm: { methods },
      commonErrorMessage: "m.acceptApplication.error",
      promise: acceptFoApplication(appType, id, formData),
      onSuccess: (response) => {
        toast.success(t("m.save.data.success"));
        scrollToTop(true);
        navigate(NavigationUtils.editApplication(appType, response?.id));
      },
    };
    setConfirmModalState({ open: true, submitFn: asyncCallArgs });
  };

  return (
    <FormProvider {...methods}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <BlockFormBackdrop />
        {children}
        {activeTab === 1 && (
          <>
            <DividerSpg my={4} />
            <BoxSpg>
              <SubmitFormButton withLoader withLoadingText label={"l.btn.save.data"} color="primary" />
            </BoxSpg>
          </>
        )}
        <ConfirmSubmitDialog modalState={confirmModalState} setModalState={setConfirmModalState} />
      </form>
    </FormProvider>
  );
};
export default FoAppAcceptFormProvider;
