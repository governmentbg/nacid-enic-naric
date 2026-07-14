import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import { useEffect, useState } from "react";
import useReportSearchSelectedFilters from "../../../../../../../hooks/redux/report/useReportSearchSelectedFilters";
import { ReportSelectedFiltersActions } from "../../../../../../../store/redux/slice/Reports/selectedFilters";
import { ReportConfigName, ReportSearchFiltersDialogContent } from "@duosoftbg/nacid-backoffice-components";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../config/report/reportConfig";

const ReportSearchFiltersDialog = ({ group, title = "t.modal.reportFilters" }) => {
  const dispatch = useAppDispatch();
  const [filters, setFilters] = useState([]);
  let searchFilters = useReportSearchSelectedFilters(group);

  useEffect(() => {
    setFilters(searchFilters);
  }, [searchFilters]);

  const handleChange = (event) => {
    dispatch(
      ReportSelectedFiltersActions.updateFilterValue({
        group: group,
        name: event.target.name,
        value: event.target.checked,
      }),
    );
  };

  return (
    <ReportSearchFiltersDialogContent
      group={group}
      title={title}
      filters={filters}
      handleChange={handleChange}
      configName={ReportConfigName.FILTERS}
      reportFiltersDefinition={REPORT_FILTERS_DEFINITION}
      reportConfig={REPORT_CONFIG}
    />
  );
};
export default ReportSearchFiltersDialog;
