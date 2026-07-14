import {
  AsyncCallArgs,
  BoxSpg,
  DividerSpg,
  SubmitFormButton,
  BlockFormBackdrop,
  WithChildren,
  useReactHookForm,
  useScroll,
  useReloadWatcherWriter,
  ConfirmSubmitDialog,
  ValidationErrors,
  useFormDirtyStateSetter,
  FormDirtyStateControlActions,
  useScrollOnValidationError,
} from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { useTranslation } from "react-i18next";
import { toast } from "react-toastify";
import React, { useState } from "react";
import { AppType, ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";
import { updateAppMainData } from "../../../../../../../axios/api/services";
import { vDocrecMainDataSchema } from "../../../../../../../yup/schema/applications/docrec/schemas";
import { vSarMainDataSchema } from "../../../../../../../yup/schema/applications/sar/schemas";
import { vUdirecMainDataSchema } from "../../../../../../../yup/schema/applications/udirec/schemas";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";

type MainDataFormProviderProps = WithChildren<{
  initialData: any;
  id: string;
  appType: AppType;
}>;

const selectValidationSchema = (appType: AppType) => {
  switch (appType) {
    case AppType.SAR_APPLICATION: {
      return vSarMainDataSchema;
    }
    case AppType.UDIREC_APPLICATION: {
      return vUdirecMainDataSchema;
    }
    case AppType.DOCREC_APPLICATION: {
      return vDocrecMainDataSchema;
    }
  }

  return null;
};

const MainDataFormProvider = ({ appType, initialData, id, children }: MainDataFormProviderProps) => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();
  const { scrollToTop } = useScroll();
  const [confirmModalState, setConfirmModalState] = useState({ open: false, submitFn: null });
  const { updateReloadWatcher } = useReloadWatcherWriter();

  const { methods, handleSubmit } = useReactHookForm({
    defaultValues: initialData,
    validationSchema: selectValidationSchema(appType),
  });

  useFormDirtyStateSetter({ methods });
  useScrollOnValidationError({ methods });

  const onSubmit = (formData) => {
    const asyncCallArgs: AsyncCallArgs = {
      withGlobalBackdrop: true,
      reactHooksForm: { methods },
      promise: updateAppMainData(id, formData),
      onSuccess: () => {
        dispatch(FormDirtyStateControlActions.setDirty({ dirty: false }));
        toast.success(t("m.save.data.success"));
        scrollToTop(true);
        updateReloadWatcher(ReloadWatcherObject.ResponsibleUser.add());
        updateReloadWatcher(ReloadWatcherObject.Application.id(id));
      },
    };
    setConfirmModalState({ open: true, submitFn: asyncCallArgs });
  };

  return (
    <FormProvider {...methods}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <BoxSpg>
          <SubmitFormButton withLoader withLoadingText label={"l.btn.save.data"} color="primary" />
        </BoxSpg>
        <ValidationErrors />
        <BlockFormBackdrop />
        {children}
        <DividerSpg my={4} />
        <BoxSpg>
          <SubmitFormButton withLoader withLoadingText label={"l.btn.save.data"} color="primary" />
        </BoxSpg>
        <ConfirmSubmitDialog modalState={confirmModalState} setModalState={setConfirmModalState} />
      </form>
    </FormProvider>
  );
};

export default MainDataFormProvider;
