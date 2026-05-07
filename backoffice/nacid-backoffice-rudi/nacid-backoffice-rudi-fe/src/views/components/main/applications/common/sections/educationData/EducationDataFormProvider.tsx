import {
  AsyncCallArgs,
  BlockFormBackdrop,
  BoxSpg,
  ConfirmSubmitDialog,
  DividerSpg,
  SubmitFormButton,
  useFormDirtyStateSetter,
  useReactHookForm,
  useReloadWatcherWriter,
  useScroll,
  useScrollOnValidationError,
  ValidationErrors,
  WithChildren,
} from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { useTranslation } from "react-i18next";
import { toast } from "react-toastify";
import React, { useState } from "react";
import { updateAppEduData } from "../../../../../../../axios/api/services";
import { editEducationDataValidationSchema } from "../../../../../../../yup/schema/common/validationEducationData";
import { ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";

type EducationDataFormProviderProps = WithChildren<{
  initialData: any;
  id: string;
}>;

const EducationDataFormProvider = ({ initialData, id, children }: EducationDataFormProviderProps) => {
  const { t } = useTranslation();
  const { scrollToTop } = useScroll();
  const { updateReloadWatcher } = useReloadWatcherWriter();
  const [confirmModalState, setConfirmModalState] = useState({ open: false, submitFn: null });

  const { methods, handleSubmit } = useReactHookForm({
    defaultValues: initialData,
    validationSchema: editEducationDataValidationSchema,
  });

  useFormDirtyStateSetter({ methods });
  useScrollOnValidationError({ methods });

  const onSubmit = (formData) => {
    const asyncCallArgs: AsyncCallArgs = {
      withGlobalBackdrop: true,
      reactHooksForm: { methods },
      promise: updateAppEduData(id, formData),
      onSuccess: (response) => {
        methods.reset(response);
        toast.success(t("m.save.data.success"));
        scrollToTop(true);
        updateReloadWatcher(ReloadWatcherObject.build("check-unfilled-unis", "check-unfilled-unis"));
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

export default EducationDataFormProvider;
