import useAppSelector from "../base/useAppSelector";
import { REPORT_CONFIG } from "../../../config/report/reportConfig";
import { useMemo } from "react";
import { convertObjectToArray } from "@duosoftbg/nacid-components";

export const sortFilters = (group, filters) => {
  let result = [];
  if (filters) {
    REPORT_CONFIG[group].tableColumns.forEach((item) => {
      let filter = filters.find((e) => e.id === item.id);
      if (filter) {
        result.push(filter);
      }
    });
  }
  return result;
};

const useReportTableSelectedColumns = (group) => {
  const tableColumns = useAppSelector((state) => state.Reports.selectedTableColumns[group]);
  return useMemo(() => sortFilters(group, convertObjectToArray(tableColumns)), [group, tableColumns]);
};

export default useReportTableSelectedColumns;
