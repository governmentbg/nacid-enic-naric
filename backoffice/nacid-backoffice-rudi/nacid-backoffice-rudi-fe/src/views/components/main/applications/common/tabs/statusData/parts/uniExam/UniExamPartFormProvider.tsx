import {
  BlockFormBackdrop,
  WithChildren,
  useReactHookForm,
  AsyncCallArgs,
  SubmitFormButton,
  BoxSpg,
  DividerSpg,
  useReloadWatcherWriter,
  ValidationErrors,
  useScroll,
  ConfirmSubmitDialog,
  useFormDirtyStateSetter,
  useScrollOnValidationError,
} from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { useTranslation } from "react-i18next";
import React, { useState } from "react";
import { updateApplicationUniExaminationData } from "../../../../../../../../../axios/api/services";
import { toast } from "react-toastify";
import { UniExaminationFormType } from "../../../../../../../../../types/applications/common/status/uniExaminationTypes";
import { uniExaminationFormInitialValues } from "../../../../../../../../../init/application/uniExamination/uniExaminationInitialValues";
import { ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";
import { vUniExaminationSchema } from "../../../../../../../../../yup/schema/applications/common/schemas";

type UniExaminationDataFormProviderProps = WithChildren<{
  initialData: any;
  applicationId: string;
}>;

const UniExamPartFormProvider = ({ initialData, applicationId, children }: UniExaminationDataFormProviderProps) => {
  const { t } = useTranslation();
  const { updateReloadWatcher } = useReloadWatcherWriter();
  const [confirmModalState, setConfirmModalState] = useState({ open: false, submitFn: null });
  const { scrollToElementById, scrollToTop } = useScroll();

  const initialDataRevised = initialData.id
    ? initialData
    : {
        ...uniExaminationFormInitialValues,
        university: initialData.university,
      };

  const { methods, handleSubmit } = useReactHookForm<UniExaminationFormType>({
    defaultValues: initialDataRevised,
    validationSchema: vUniExaminationSchema,
  });

  useFormDirtyStateSetter({ methods });

  const selectScrollElementId = () => {
    const universityId = initialDataRevised.university.id;
    let firstUniversity = document.getElementsByClassName(`first-university-${universityId}`);
    if (firstUniversity && firstUniversity.length > 0) {
      return undefined;
    } else {
      return `exam-form-${universityId}`;
    }
  };

  useScrollOnValidationError({ methods, scrollToElement: selectScrollElementId() });

  const scrollOnSubmit = (universityId) => {
    let firstUniversity = document.getElementsByClassName(`first-university-${universityId}`);
    if (firstUniversity && firstUniversity.length > 0) {
      scrollToTop(true);
    } else {
      scrollToElementById({ id: `exam-form-${universityId}` });
    }
  };

  const onSubmit = (formData) => {
    const asyncCallArgs: AsyncCallArgs = {
      withGlobalBackdrop: true,
      reactHooksForm: { methods },
      promise: updateApplicationUniExaminationData(applicationId, formData),
      onSuccess: (response) => {
        methods.reset(response);
        updateReloadWatcher(ReloadWatcherObject.UniExamination.change(applicationId, response.university.id));

        if (response?.isStatusUpdated === true) {
          updateReloadWatcher(ReloadWatcherObject.Application.id(applicationId));
        }
        updateReloadWatcher(ReloadWatcherObject.AbdocsTransferConfig.reload());
        toast.success(t("m.save.data.success"));
        scrollOnSubmit(response.university.id);
      },
    };
    setConfirmModalState({ open: true, submitFn: asyncCallArgs });
  };

  return (
    <FormProvider {...methods}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <BlockFormBackdrop />
        <ValidationErrors />
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

export default UniExamPartFormProvider;
