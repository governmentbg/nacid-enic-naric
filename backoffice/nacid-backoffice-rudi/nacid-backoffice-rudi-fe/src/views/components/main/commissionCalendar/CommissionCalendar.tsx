import React, { useEffect } from "react";
import { Box, CardContent } from "@mui/material";
import { BorderGreyBox, CardSpg, isNotEmpty, useReactHookForm } from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { useStore } from "react-redux";
import { commissionCalendarFilterInitialValues } from "../../../../init/commissionCalendar/commissionCalendarFilterInitialValues";
import { CommissionCalendarFilterDetails } from "../../../../types/commissionCalendar/commissionCalendarTypes";
import { createCommissionCalendarFilterValidationSchema } from "../../../../yup/schema/commissionCalendar/commissionCalendarFilterValidationSchema";
import CommissionCalendarList from "./CommissionCalendarList";
import useSearchTableControl from "../../../../hooks/backoffice/search/useSearchTableControl";
import { searchCommissionCalendars } from "../../../../axios/api/services";
import { SEARCH_FILTERS_GROUP } from "../../../../config/search/filters/groupsConfig";
import SessionNumFilter from "../../common/search/filters/definition/SessionNumFilter";
import SessionTimeFilter from "../../common/search/filters/definition/SessionTimeFilter";
import CommissionCalendarStatusesFilter from "../../common/search/filters/definition/autocomplete/CommissionCalendarStatusesFilter";
import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import { resetTab } from "../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";
import { SearchFilters } from "@duosoftbg/nacid-backoffice-components";
import type { RootState } from "../../../../store/redux/store";

const CommissionCalendar = () => {
  const store = useStore();
  const group = SEARCH_FILTERS_GROUP.COMMISSION_CALENDAR;
  const dispatch = useAppDispatch();

  const { methods, handleSubmit } = useReactHookForm<CommissionCalendarFilterDetails>({
    defaultValues: commissionCalendarFilterInitialValues,
    validationSchema: createCommissionCalendarFilterValidationSchema,
  });

  useEffect(() => {
    dispatch(resetTab());
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
    initialValues: commissionCalendarFilterInitialValues,
    filterData: searchCommissionCalendars,
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
                  <SessionNumFilter />
                  <SessionTimeFilter />
                  <CommissionCalendarStatusesFilter group={group} />
                </SearchFilters>
              </BorderGreyBox>
              {control.showList && (
                <CommissionCalendarList
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

export default CommissionCalendar;
