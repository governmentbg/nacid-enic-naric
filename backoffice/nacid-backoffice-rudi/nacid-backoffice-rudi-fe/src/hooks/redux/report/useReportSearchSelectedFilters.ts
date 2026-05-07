import useAppSelector from "../base/useAppSelector";
import { useMemo } from "react";
import { convertObjectToArray } from "@duosoftbg/nacid-components";
import { REPORT_CONFIG } from "../../../config/report/reportConfig";

export const sortFilters = (group, filters) => {
  let result = [];
  if (filters) {
    REPORT_CONFIG[group].filters.forEach((item) => {
      let filter = filters.find((e) => e.id === item.id);
      if (filter) {
        result.push(filter);
      }
    });
  }
  return result;
};

const useReportSearchSelectedFilters = (group) => {
  const searchFilters = useAppSelector((state) => state.Reports.selectedFilters[group]);
  return useMemo(() => sortFilters(group, convertObjectToArray(searchFilters)), [group, searchFilters]);
};

export default useReportSearchSelectedFilters;
