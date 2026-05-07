import { SEARCH_FILTERS_GROUP } from "../../../../../config/search/filters/groupsConfig";
import { useReloadWatcherWriter } from "@duosoftbg/nacid-components";
import { CommonReportSearchFiltersDetails } from "../../../../../types/report/commonReportFiltersType";
import { commonReportSearchFiltersInitialValues } from "../../../../../init/report/commonReportSearchFiltersInitialValues";
import { createCommonReportSearchFiltersValidationSchema } from "../../../../../yup/schema/report/commonReportSearchFiltersValidationSchema";
import useSearchTableControl from "../../../../../hooks/backoffice/search/useSearchTableControl";
import { rudiCommonReport } from "../../../../../axios/api/services";
import { ReloadWatcherObject, ReportList, ReportSearchBase } from "@duosoftbg/nacid-backoffice-components";
import { REPORT_GROUP } from "../../../../../config/report/reportConfig";
import ReportSearchFilters from "../../../common/report/filters/ReportSearchFilters";
import React from "react";
import ReportSearchFiltersDialog from "../../../common/report/filters/dialog/search/ReportSearchFiltersDialog";
import ReportListTable from "../../../common/report/table/ReportListTable";
import { yupResolver } from "@hookform/resolvers/yup/dist/yup";
import { useForm } from "react-hook-form";

const CommonReport = () => {
  const reportGroup = REPORT_GROUP.COMMON_REPORT;
  const searchGroup = SEARCH_FILTERS_GROUP.COMMON_REPORT;
  const { updateReloadWatcher } = useReloadWatcherWriter();

  // useForm instead of useReactHookForm, because useReactHookForm triggers multiple re-renders on the whole form when useFieldArray functions are executed
  const methods = useForm<CommonReportSearchFiltersDetails>({
    defaultValues: commonReportSearchFiltersInitialValues,
    resolver: yupResolver(createCommonReportSearchFiltersValidationSchema()),
    reValidateMode: "onBlur",
  });

  const control = useSearchTableControl({
    group: searchGroup,
    methods,
    initialValues: commonReportSearchFiltersInitialValues,
    filterData: rudiCommonReport,
    afterResetFilters: () => updateReloadWatcher(ReloadWatcherObject.Report.clear()),
    initialCall: false,
    withGlobalBackdrop: true,
  });

  return (
    <ReportSearchBase
      methods={methods}
      control={control}
      filtersComponent={<ReportSearchFilters reportGroup={reportGroup} control={control} />}
      filtersDialogComponent={<ReportSearchFiltersDialog group={reportGroup} />}
    >
      <ReportList isLoading={control.isLoading}>
        <ReportListTable
          total={control.total}
          records={control.records}
          blockTable={control.blockTable}
          onPageOrOrderChange={control.handlePageOrOrderChange}
          searchGroup={searchGroup}
          reportGroup={reportGroup}
        />
      </ReportList>
    </ReportSearchBase>
  );
};

export default CommonReport;
