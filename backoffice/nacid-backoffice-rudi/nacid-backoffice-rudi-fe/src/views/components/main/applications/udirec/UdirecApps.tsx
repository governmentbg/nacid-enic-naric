import React, { useEffect } from "react";
import { Box, CardContent } from "@mui/material";
import {
  ApplicationType,
  BaseInputFieldFilter,
  BorderGreyBox,
  CardSpg,
  DateFromToFilter,
  isNotEmpty,
  SecurityRole,
  useReactHookForm,
} from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { useStore } from "react-redux";
import { ApplicationFilterDetails } from "../../../../../types/applicationTypes";
import { SEARCH_FILTERS_GROUP } from "../../../../../config/search/filters/groupsConfig";
import useSearchTableControl from "../../../../../hooks/backoffice/search/useSearchTableControl";
import { searchApplications } from "../../../../../axios/api/services";
import { udirecFilterInitialValues } from "../../../../../init/application/udirecFilterInitialValues";
import UdirecAppsList from "./UdirecAppsList";
import { RudiApplication } from "../../../../../utils/constants";
import ForeignUniversityNameFilter from "../../../common/search/filters/definition/ForeignUniversityNameFilter";
import { resetExpertTab, resetStatusTab } from "../../../../../store/redux/slice/ComponentsControl/applicationsControl";
import useAppDispatch from "../../../../../hooks/redux/base/useAppDispatch";
import { APPLICATION_GROUP } from "../../../../../config/applications/applicationConfig";
import { vUdirecAppsFilterSchema } from "../../../../../yup/schema/applications/udirec/schemas";
import {
  ApplicantNameSearchFilter,
  ApplicationResponsibleUserFilter,
  ApplicationStatusByTypeFilter,
  DocflowStatusFilter,
  EntryNumFilter,
  FilingTypeByAppTypeFilter,
  SearchFilters,
} from "@duosoftbg/nacid-backoffice-components";
import { UdirecAppControlActions } from "../../../../../store/redux/slice/ComponentsControl/udirecApplicationsControl";
import type { RootState } from "../../../../../store/redux/store";
import CountryFilter from "../../../common/search/filters/definition/autocomplete/CountryFilter";

const UdirecApps = () => {
  const store = useStore();
  const group = SEARCH_FILTERS_GROUP.UDIREC_APPLICATIONS;
  const applicationGroup = APPLICATION_GROUP.UDIREC_APPLICATION;
  const dispatch = useAppDispatch();

  const { methods, handleSubmit } = useReactHookForm<ApplicationFilterDetails>({
    defaultValues: udirecFilterInitialValues,
    validationSchema: vUdirecAppsFilterSchema,
  });

  useEffect(() => {
    dispatch(UdirecAppControlActions.resetTab());
    dispatch(resetExpertTab());
    dispatch(resetStatusTab());
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
    initialValues: udirecFilterInitialValues,
    filterData: searchApplications,
    callAfterResetFilters: true,
  });

  return (
    <FormProvider {...methods}>
      <form onSubmit={handleSubmit(() => control.handleSubmitFilters())}>
        <CardSpg my={4} style={{ overflow: "visible" }}>
          <CardContent style={{ position: "relative" }}>
            <Box>
              <BorderGreyBox>
                <SearchFilters
                  isSubmitBtnDisabled={control.isSubmitBtnDisabled}
                  isResetBtnDisabled={control.isResetBtnDisabled}
                  handleResetFilters={control.handleResetFilters}
                  leftButton={{
                    title: "l.btn.newApplication",
                    link: "/udirec-applications/create",
                    roles: [SecurityRole.RudiApplicationCreate],
                  }}
                >
                  <EntryNumFilter sm={3} md={3} isExactMatch />
                  <ApplicantNameSearchFilter />
                  <BaseInputFieldFilter sm={3} md={3} fieldName={"applicantCivilId"} label={"l.personalId"} />

                  <CountryFilter sm={4} md={4} group={group} fieldName={"universityCountryCode"} label={"l.country"} />
                  <ForeignUniversityNameFilter sm={4} md={4} />
                  <FilingTypeByAppTypeFilter applicationType={ApplicationType.ACADEMIC_RECOGNITION} />

                  <DateFromToFilter
                    sm={6}
                    md={4}
                    label={"l.searchFilter.dateLastSubmitted"}
                    from={"dateFrom"}
                    to={"dateTo"}
                  />
                  <ApplicationStatusByTypeFilter
                    applicationType={RudiApplication.rudiApplicationType}
                    applicationSubType={RudiApplication.rudiUDIApplicationSybType}
                  />
                  <DateFromToFilter
                    sm={4}
                    md={4}
                    label={"l.searchFilter.backofficeDate"}
                    from={"backofficeDateFrom"}
                    to={"backofficeDateTo"}
                  />
                  <DocflowStatusFilter group={group} />
                  <ApplicationResponsibleUserFilter />
                </SearchFilters>
              </BorderGreyBox>
              {control.showList && (
                <UdirecAppsList
                  records={control.records}
                  total={control.total}
                  isLoading={control.isLoading}
                  onPageOrOrderChange={control.handlePageOrOrderChange}
                  blockTable={control.blockTable}
                  group={group}
                  filterFn={control.fetch}
                  applicationGroup={applicationGroup}
                />
              )}
            </Box>
          </CardContent>
        </CardSpg>
      </form>
    </FormProvider>
  );
};

export default UdirecApps;
