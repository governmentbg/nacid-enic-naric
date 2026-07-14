import {
  AsyncCallArgs,
  BlockFormBackdrop,
  BoxSpg,
  Country,
  isNotEmpty,
  SubmitFormButton,
  TempFormDataActions,
  useAsyncCall,
  useReactHookForm,
  useReloadWatcherWriter,
  WithChildren,
} from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import { UniversityControlActions } from "../../../../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";
import {
  CoreApiServicesBase,
  createUniversityValidationSchema,
  InitialValues,
  ReloadWatcherObject,
  UniversityEditContent,
} from "@duosoftbg/nacid-backoffice-components";
import React, { useEffect } from "react";

type UniversityFormProviderProps = WithChildren<{
  universityId?: any;
  universityTDK: string;
  universityIdPointer: string;
  initialData: { country: Country; bgName: string; orgName: string };
}>;

const UniversityFormProvider = ({
  universityId,
  universityTDK,
  universityIdPointer,
  initialData = { country: { id: "", name: "" }, bgName: "", orgName: "" },
}: UniversityFormProviderProps) => {
  const { asyncCall } = useAsyncCall();
  const dispatch = useAppDispatch();
  const { updateReloadWatcher } = useReloadWatcherWriter();

  const { methods, handleSubmit } = useReactHookForm({
    defaultValues: InitialValues.forms.university.universityInitialValues,
    validationSchema: createUniversityValidationSchema,
  });

  useEffect(() => {
    if (isNotEmpty(initialData?.country?.id)) {
      // @ts-ignore
      methods.setValue("address.country.id", initialData?.country?.id);
    }
    if (isNotEmpty(initialData?.bgName)) {
      // @ts-ignore
      methods.setValue("bgName", initialData?.bgName);
    }
    if (isNotEmpty(initialData?.orgName)) {
      // @ts-ignore
      methods.setValue("orgName", initialData?.orgName);
    }
    // eslint-disable-next-line
  }, [initialData]);

  const onSubmit = (formData) => {
    const asyncCallArgs: AsyncCallArgs = {
      withGlobalBackdrop: true,
      reactHooksForm: {
        methods: methods,
      },
      promise: formData?.id
        ? CoreApiServicesBase.updateUniversity(formData)
        : CoreApiServicesBase.createUniversity(formData),
      onSuccess: (response) => {
        // if (!formData?.id) {
        dispatch(
          TempFormDataActions.setTempData({
            key: universityTDK,
            pointer: universityIdPointer,
            data: response.id,
          }),
        );
        updateReloadWatcher(ReloadWatcherObject.build(universityTDK, universityIdPointer));
        // }
        dispatch(UniversityControlActions.closeEditUniversityModal({}));
        dispatch(UniversityControlActions.closeCreateUniversityModal({}));
        dispatch(UniversityControlActions.closeSearchUniversityModal({}));
      },
      onError: (error) => {
        //TODO
      },
    };
    asyncCall(asyncCallArgs);
  };

  return (
    <FormProvider {...methods}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <BlockFormBackdrop />
        <UniversityEditContent id={universityId} methods={methods} loaderType={"skeleton"} />
        <BoxSpg mt={3} textAlign={"right"}>
          <SubmitFormButton
            withLoader
            withLoadingText
            label={"l.btn.save"}
            size={"small"}
            style={{ minWidth: 100 }}
            color="primary"
          />
        </BoxSpg>
      </form>
    </FormProvider>
  );
};

export default UniversityFormProvider;
