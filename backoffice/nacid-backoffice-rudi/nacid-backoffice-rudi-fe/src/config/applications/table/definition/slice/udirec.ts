import { ApplicationTableColumnsDefinition } from "../../../../../types/applications/common/application";

export const UDIREC_APPLICATION_TABLE_COLUMNS: ApplicationTableColumnsDefinition = {
  recognizedQualification: {
    id: "recognizedQualification",
    label: "l.table.head.recognizedQualification",
    sortable: true,
    getValue: (application) => {
      return application.recognizedQualification;
    },
    getStyle: (application) => {
      return null;
    },
  },
};
