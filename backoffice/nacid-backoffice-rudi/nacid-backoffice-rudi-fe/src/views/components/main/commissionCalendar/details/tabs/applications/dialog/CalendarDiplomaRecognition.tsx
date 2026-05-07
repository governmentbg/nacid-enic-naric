import React, { useEffect } from "react";
import { Box, CardContent } from "@mui/material";
import { BorderGreyBox, CardSpg, isNotEmpty, useReactHookForm } from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { useStore } from "react-redux";
import { SEARCH_FILTERS_GROUP } from "../../../../../../../../config/search/filters/groupsConfig";
import { ApplicationFilterDetails } from "../../../../../../../../types/applicationTypes";
import { udirecFilterInitialValues } from "../../../../../../../../init/application/udirecFilterInitialValues";
import useSearchTableControl from "../../../../../../../../hooks/backoffice/search/useSearchTableControl";
import { searchApplications } from "../../../../../../../../axios/api/services";
import ApplicationStatusFilter from "../../../../../../common/search/filters/definition/autocomplete/ApplicationStatusFilter";
import CalendarDiplomaRecognitionList from "./CalendarDiplomaRecognitionList";
import { CommissionCalendarConst } from "../../../../../../../../utils/constants";
import { vUdirecAppsFilterSchema } from "../../../../../../../../yup/schema/applications/udirec/schemas";
import { DocflowStatusFilter, EntryNumFilter, SearchFilters } from "@duosoftbg/nacid-backoffice-components";
import type { RootState } from "../../../../../../../../store/redux/store";

const CalendarDiplomaRecognition = ({ excludedApplications }) => {
  const store = useStore();
  const group = SEARCH_FILTERS_GROUP.CALENDAR_DIPLOMA_RECOGNITIONS;

  const { methods, handleSubmit } = useReactHookForm<ApplicationFilterDetails>({
    defaultValues: {
      ...udirecFilterInitialValues,
      excludedApplications: excludedApplications,
      docflowStatusCode: CommissionCalendarConst.calendarAppsDefaultDocflowStatus,
    },
    validationSchema: vUdirecAppsFilterSchema,
  });

  useEffect(() => {
    const state = store.getState() as RootState;
    const filtersData = state.SearchData.backofficeSearchTable[group].filtersData;
    if (isNotEmpty(filtersData)) {
      methods.reset({
        ...filtersData,
        excludedApplications: excludedApplications,
        docflowStatusCode: CommissionCalendarConst.calendarAppsDefaultDocflowStatus,
      });
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const control = useSearchTableControl({
    group,
    methods,
    initialValues: {
      ...udirecFilterInitialValues,
      excludedApplications: excludedApplications,
      docflowStatusCode: CommissionCalendarConst.calendarAppsDefaultDocflowStatus,
    },
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
                >
                  <EntryNumFilter sm={4} md={4} />
                  <ApplicationStatusFilter group={group} />
                  <DocflowStatusFilter group={group} />
                </SearchFilters>
              </BorderGreyBox>
              {control.showList && (
                <CalendarDiplomaRecognitionList
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
  );
};

export default CalendarDiplomaRecognition;
