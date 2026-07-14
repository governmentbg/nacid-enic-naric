import { APPLICATION_TABLE_COLUMNS_DEFINITION } from "../../config/applications/table/definition";

export const selectActiveApplicationsHeadCells = (tableColumns) => {
  return [
    {
      id: "number",
      label: "l.table.head.number",
      sortable: false,
    },
    {
      id: "type",
      label: "l.type",
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

export const selectActiveApplicationsColumns = (tableColumns) => {
  return [...getActiveData(tableColumns)];
};

const getActiveData = (tableColumns) => {
  const activeData = [];

  tableColumns.forEach((item) => {
    if (item.value === true) {
      activeData.push(APPLICATION_TABLE_COLUMNS_DEFINITION[item.id]);
    }
  });
  return activeData;
};
