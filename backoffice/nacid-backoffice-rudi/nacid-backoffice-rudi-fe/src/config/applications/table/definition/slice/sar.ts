import { ApplicationTableColumnsDefinition } from "../../../../../types/applications/common/application";

export const SAR_APPLICATION_TABLE_COLUMNS: ApplicationTableColumnsDefinition = {
  diplomaOwnerName: {
    id: "diplomaOwnerName",
    label: "l.table.head.diplomaOwnerName",
    sortable: true,
    getValue: (application) => {
      return application.diplomaOwnerName;
    },
    getStyle: (application) => {
      return null;
    },
  },
};
