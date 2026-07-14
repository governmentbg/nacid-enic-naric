import { ApplicationTableColumnsDefinition } from "../../../../../types/applications/common/application";

export const UNIVERSAL_APPLICATION_TABLE_COLUMNS: ApplicationTableColumnsDefinition = {
  entryNum: {
    id: "entryNum",
    label: "l.entryNum",
    sortable: true,
    getValue: (application) => {
      return application.entryNum;
    },
    getStyle: (application) => {
      return null;
    },
  },
  entryDate: {
    id: "entryDate",
    label: "l.table.head.entryDate",
    sortable: true,
    getValue: (application) => {
      return application.entryDate;
    },
    getStyle: (application) => {
      return null;
    },
  },
  backofficeDate: {
    id: "backofficeDate",
    label: "l.table.head.backofficeDate",
    sortable: true,
    getValue: (application) => {
      return application.backofficeDate;
    },
    getStyle: (application) => {
      return null;
    },
  },
  applicantName: {
    id: "applicantName",
    label: "l.table.head.applicantName",
    sortable: true,
    getValue: (application) => {
      return application.applicantName;
    },
    getStyle: (application) => {
      // debugger;
      return { whiteSpace: "normal", wordBreak: "break-word" };
    },
  },
  universityName: {
    id: "universityName",
    label: "l.table.head.foreign.universityName",
    sortable: true,
    getValue: (application) => {
      return application.universityName ? application.universityName : application.manualTempUniName;
    },
    getStyle: (application) => {
      return application.universityName
        ? { whiteSpace: "normal", wordBreak: "break-word" }
        : { color: "red", whiteSpace: "normal", wordBreak: "break-word" };
    },
  },
  universityCountryName: {
    id: "universityCountryName",
    label: "l.table.head.universityCountryName",
    sortable: true,
    getValue: (application) => {
      return application.universityCountryName;
    },
    getStyle: (application) => {
      return null;
    },
  },
  eduLevelName: {
    id: "eduLevelName",
    label: "l.table.head.eduLevelName",
    sortable: true,
    getValue: (application) => {
      return application.eduLevelName;
    },
    getStyle: (application) => {
      return null;
    },
  },
  specialityName: {
    id: "specialityName",
    label: "l.table.head.specialityName",
    sortable: true,
    getValue: (application) => {
      return application.specialityName;
    },
    getStyle: (application) => {
      return null;
    },
  },
  apnStatusName: {
    id: "apnStatusName",
    label: "l.table.head.apnStatusName",
    sortable: true,
    getValue: (application) => {
      return application.apnStatusName;
    },
    getStyle: (application) => {
      return null;
    },
  },
  docflowStatusName: {
    id: "docflowStatusName",
    label: "l.table.head.docflowStatusName",
    sortable: true,
    getValue: (application) => {
      return application.docflowStatusName;
    },
    getStyle: (application) => {
      return null;
    },
  },
  sarFlag: {
    id: "sarFlag",
    label: "l.sarFlag",
    sortable: false,
    getValue: (application) => {
      return application.sarFlag;
    },
    getStyle: (application) => {
      return null;
    },
  },
  responsibleUserName: {
    id: "responsibleUserName",
    label: "l.table.head.responsibleUser",
    sortable: true,
    getValue: (application) => {
      return application?.responsibleUserData?.fullName;
    },
    getStyle: (application) => {
      return null;
    },
  },
};
