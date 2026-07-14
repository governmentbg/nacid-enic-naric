import useReportSearchSelectedFilters from "../../../../../hooks/redux/report/useReportSearchSelectedFilters";
import React from "react";
import { renderReportFilter } from "../../../../../utils/report/reportFilters";
import { ReportSearchFiltersBase } from "@duosoftbg/nacid-backoffice-components";

const ReportSearchFilters = ({ reportGroup, control }) => {
  const filters = useReportSearchSelectedFilters(reportGroup);

  return (
    <ReportSearchFiltersBase
      reportGroup={reportGroup}
      control={control}
      filters={filters}
      renderFiltersFn={renderReportFilter}
    />
  );
};
export default ReportSearchFilters;
