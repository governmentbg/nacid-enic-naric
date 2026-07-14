import {
  BlockFormBackdrop,
  BoxSpg,
  deepCopy,
  MediumGreyButton,
  SubmitFormButton,
  useAsyncCall,
  useReactHookForm,
  WithChildren,
} from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { useTranslation } from "react-i18next";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClose, faSearch } from "@fortawesome/free-solid-svg-icons";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import { UniversityControlActions } from "../../../../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";
import { createSearchUniversityModalValidationSchema } from "../../../../../../../../../../../yup/schema/university/searchUniversityModalSchema";
import { searchUniversities } from "../../../../../../../../../../../axios/api/services";
import React, { useEffect } from "react";
import { useStore } from "react-redux";
import type { RootState } from "../../../../../../../../../../../store/redux/store";

type SearchUniversityModalFormProviderProps = WithChildren<{
  initialData?: any;
}>;

const defaultValues = {
  bgName: null,
  orgName: null,
};

const SearchUniversityDialogFormProvider = ({
  initialData = defaultValues,
  children,
}: SearchUniversityModalFormProviderProps) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const dispatch = useAppDispatch();
  const store = useStore();

  const { methods, handleSubmit } = useReactHookForm({
    defaultValues: initialData,
    validationSchema: createSearchUniversityModalValidationSchema,
  });

  useEffect(() => {
    const state = store.getState() as RootState;
    const storeInitialValues = state.ComponentsControl.universityControl.modals.search.searchFormValues;
    if (storeInitialValues) {
      let copy = deepCopy(storeInitialValues);
      methods.reset({ ...initialData, ...copy });
      onSubmit(copy);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const onSubmit = (formData) => {
    dispatch(UniversityControlActions.updateSearchUniversityModalFormValues({ searchFormValues: formData }));

    asyncCall({
      promise: searchUniversities(formData),
      processResponseErrors: false,
      reactHooksForm: { methods },
      onSuccess: (response) => {
        dispatch(UniversityControlActions.updateSearchUniversityModalRecords({ records: response, status: "success" }));
      },
      onError: (error) => {
        dispatch(UniversityControlActions.updateSearchUniversityModalRecords({ records: [], status: "error" }));
      },
    });
  };

  const handleReset = () => {
    methods.reset(initialData);
    dispatch(UniversityControlActions.updateSearchUniversityModalRecords({ records: [], status: "initial" }));
    dispatch(UniversityControlActions.updateSearchUniversityModalFormValues({ searchFormValues: null }));
  };

  return (
    <FormProvider {...methods}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <BlockFormBackdrop />
        {children}
        <BoxSpg mt={3} textAlign={"right"}>
          <SubmitFormButton
            startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faSearch} />}
            withLoader
            withLoadingText
            loadingText={"l.btn.searching"}
            label={"l.btn.search"}
            size={"small"}
            style={{ minWidth: 100 }}
            color="primary"
          />
          <MediumGreyButton
            startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faClose} />}
            size={"small"}
            onClick={handleReset}
            style={{ marginLeft: "15px", minWidth: 100 }}
            variant="contained"
          >
            {t("l.btn.clear")}
          </MediumGreyButton>
        </BoxSpg>
      </form>
    </FormProvider>
  );
};

export default SearchUniversityDialogFormProvider;
