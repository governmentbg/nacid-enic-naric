import {
  ApplicantNameSearchFilter,
  CoreApiServicesBase,
  DateLastSubmittedFilter,
  FoApplicationStatusFilter,
  FoAppRevertDeniedStatusDialog,
  SearchFilters,
} from "@duosoftbg/nacid-backoffice-components";
import {
  BaseInputFieldFilter,
  BorderGreyBox,
  CardSpg,
  DateFromToFilter,
  FlagSelectFilter,
  isNotEmpty,
  useReactHookForm,
} from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { Box, CardContent } from "@mui/material";
import FoDuplicateAppsList from "./FoDuplicateAppsList";
import React, { useEffect } from "react";
import { useStore } from "react-redux";
import { SEARCH_FILTERS_GROUP } from "../../../../../../config/search/filters/groupsConfig";
import { RootState } from "../../../../../../store/redux/store";
import useSearchTableControl from "../../../../../../hooks/backoffice/search/useSearchTableControl";
import { foDuplicateFilterInitialValues } from "../../../../../../init/application/electronicSubmission/foDuplicateFilterInitialValues";
import { FoDuplicateFilterDetails } from "../../../../../../types/applications/electronicSubmission/foApplicationTypes";
import { vFoBaseAppsFilterSchema } from "../../../../../../yup/schema/applications/common/schemas";

const FoDuplicateApps = () => {
  const group = SEARCH_FILTERS_GROUP.FO_DUPLICATE_APPLICATIONS;
  const store = useStore();

  const { methods, handleSubmit } = useReactHookForm<FoDuplicateFilterDetails>({
    defaultValues: foDuplicateFilterInitialValues,
    validationSchema: vFoBaseAppsFilterSchema,
  });

  useEffect(() => {
    const state = store.getState() as RootState;
    const filtersData = state.SearchData.backofficeSearchTable[group].filtersData;
    if (isNotEmpty(filtersData)) {
      methods.reset(filtersData);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const control = useSearchTableControl({
    group,
    methods,
    initialValues: foDuplicateFilterInitialValues,
    filterData: CoreApiServicesBase.foApplicationsSearch,
    callAfterResetFilters: true,
  });
  return (
    <>
      <FoAppRevertDeniedStatusDialog />
      <FormProvider {...methods}>
        <form onSubmit={handleSubmit(() => control.handleSubmitFilters())}>
          <CardSpg my={3} style={{ overflow: "visible" }}>
            <CardContent style={{ position: "relative" }}>
              <Box>
                <BorderGreyBox>
                  <SearchFilters
                    isSubmitBtnDisabled={control.isSubmitBtnDisabled}
                    isResetBtnDisabled={control.isResetBtnDisabled}
                    handleResetFilters={control.handleResetFilters}
                  >
                    <BaseInputFieldFilter sm={4} md={4} fieldName={"entryNumber"} label={"l.application.entryNumber"} />
                    <DateFromToFilter
                      sm={4}
                      md={4}
                      from={"entryDateFrom"}
                      to={"entryDateTo"}
                      label={"l.searchFilter.entryDate"}
                    />
                    <ApplicantNameSearchFilter sm={8} md={8} />
                    <BaseInputFieldFilter sm={4} md={4} fieldName={"applicantCivilId"} label={"l.personalId"} />
                    <FoApplicationStatusFilter />
                    <DateLastSubmittedFilter sm={4} md={4} />
                    <FlagSelectFilter sm={2} md={2} fieldName={"signed"} label={"l.signed"} />
                  </SearchFilters>
                </BorderGreyBox>
                {control.showList && (
                  <FoDuplicateAppsList
                    records={control.records}
                    total={control.total}
                    isLoading={control.isLoading}
                    onPageOrOrderChange={control.handlePageOrOrderChange}
                    blockTable={control.blockTable}
                    group={group}
                    filterFn={control.fetch}
                  />
                )}
              </Box>
            </CardContent>
          </CardSpg>
        </form>
      </FormProvider>
    </>
  );
};
export default FoDuplicateApps;
