import useAppSelector from "../base/useAppSelector";
import { useMemo } from "react";
import { convertObjectToArray } from "@duosoftbg/nacid-components";
import { APPLICATION_CONFIG } from "../../../config/applications/applicationConfig";

export const sortColumns = (group, columns) => {
  let result = [];
  if (columns) {
    APPLICATION_CONFIG[group].tableColumns.forEach((item) => {
      let column = columns.find((e) => e.id === item.id);
      if (column) {
        result.push(column);
      }
    });
  }
  return result;
};

const useAppTableSelectedColumns = (group) => {
  const tableColumns = useAppSelector((state) => state.Applications.selectedTableColumns[group]);
  return useMemo(() => sortColumns(group, convertObjectToArray(tableColumns)), [group, tableColumns]);
};

export default useAppTableSelectedColumns;
