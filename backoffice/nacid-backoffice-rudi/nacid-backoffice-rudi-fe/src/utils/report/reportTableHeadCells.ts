import { REPORT_TABLE_COLUMNS_DEFINITION } from "../../config/report/table/definition";

export const selectActiveReportHeadCells = (tableColumns) => {
  return [
    {
      id: "number",
      label: "l.table.head.number",
      sortable: false,
    },
    ...getActiveData(tableColumns),
    {
      id: "options",
      label: "",
      sortable: false,
    },
  ];
};

export const selectActiveReportColumns = (tableColumns) => {
  return [...getActiveData(tableColumns)];
};

const getActiveData = (tableColumns) => {
  const activeData = [];

  tableColumns.forEach((item) => {
    if (item.value === true) {
      activeData.push(REPORT_TABLE_COLUMNS_DEFINITION[item.id]);
    }
  });
  return activeData;
};
