import { useStore } from "react-redux";
import { FormProvider } from "react-hook-form";
import React, { useEffect } from "react";
import {
  BorderGreyBox,
  CardSpg,
  CodeFilter,
  FirstNameFilter,
  IsActiveFilter,
  isNotEmpty,
  LastNameFilter,
  useReactHookForm,
} from "@duosoftbg/nacid-components";
import { Box, CardContent } from "@mui/material";
import { SEARCH_FILTERS_GROUP } from "../../../../../../../../config/search/filters/groupsConfig";
import { createCommissionMembersFilterValidationSchema } from "../../../../../../../../yup/schema/commissionCalendar/commissionMembersFilterValidationSchema";
import useSearchTableControl from "../../../../../../../../hooks/backoffice/search/useSearchTableControl";
import { searchCommissionMembers } from "../../../../../../../../axios/api/services";
import ProfGroupFilter from "../../../../../../common/search/filters/definition/autocomplete/ProfGroupFilter";
import CalendarMembersList from "./CalendarMembersList";
import { CommissionMembersFilterDetails, InitialValues, SearchFilters } from "@duosoftbg/nacid-backoffice-components";
import type { RootState } from "../../../../../../../../store/redux/store";

const CalendarMembers = ({ excludedMembers }) => {
  const store = useStore();
  const group = SEARCH_FILTERS_GROUP.CALENDAR_MEMBERS;
  const commissionPosition = "MEM";

  const { methods, handleSubmit } = useReactHookForm<CommissionMembersFilterDetails>({
    defaultValues: {
      ...InitialValues.filter.commissionMember.commissionMembersFilterInitialValues,
      excludedMembers: excludedMembers,
      commissionPosition: commissionPosition,
    },
    validationSchema: createCommissionMembersFilterValidationSchema,
  });

  useEffect(() => {
    const state = store.getState() as RootState;
    const filtersData = state.SearchData.backofficeSearchTable[group].filtersData;
    if (isNotEmpty(filtersData)) {
      methods.reset({ ...filtersData, excludedMembers: excludedMembers, commissionPosition: commissionPosition });
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const control = useSearchTableControl({
    group,
    methods,
    initialValues: {
      ...InitialValues.filter.commissionMember.commissionMembersFilterInitialValues,
      excludedMembers: excludedMembers,
      commissionPosition: commissionPosition,
    },
    filterData: searchCommissionMembers,
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
                  <CodeFilter />
                  <FirstNameFilter />
                  <LastNameFilter />
                  <ProfGroupFilter group={group} />
                  <IsActiveFilter />
                </SearchFilters>
              </BorderGreyBox>
              {control.showList && (
                <CalendarMembersList
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
export default CalendarMembers;
