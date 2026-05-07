import { ApplicationTableColumnsDefinition } from "../../../../../types/applications/common/application";

export const DOCREC_APPLICATION_TABLE_COLUMNS: ApplicationTableColumnsDefinition = {
  recognizedProfGroupName: {
    id: "recognizedProfGroupName",
    label: "l.table.head.recognizedProfGroupName",
    sortable: true,
    getValue: (application) => {
      return application.recognizedProfGroupName;
    },
    getStyle: (application) => {
      return null;
    },
  },
};
