import { ReportTableColumnsDefinition } from "../../../../../types/report/base/report";
import { RudiApplication } from "../../../../../utils/constants";

export const COMMON_REPORT_TABLE_COLUMNS: ReportTableColumnsDefinition = {
  entryNum: {
    id: "entryNum",
    label: "l.docflowNumber",
    sortable: true,
    getValue: (report) => {
      return report.entryNum;
    },
  },
  entryDate: {
    id: "entryDate",
    label: "l.date",
    sortable: true,
    getValue: (report) => {
      return report.entryDate;
    },
  },
  backofficeDate: {
    id: "backofficeDate",
    label: "l.table.head.backofficeDate",
    sortable: true,
    getValue: (report) => {
      return report.backofficeDate;
    },
  },
  ownerNames: {
    id: "ownerNames",
    label: "l.table.head.ownerNames",
    sortable: false,
    getValue: (report) => {
      return report.diplomaOwnerName;
    },
  },
  personalIdentifier: {
    id: "personalIdentifier",
    label: "l.table.head.personalIdentifier",
    sortable: false,
    getValue: (report) => {
      return report.diplomaOwnerCivilId;
    },
  },
  university: {
    id: "university",
    label: "l.table.head.universityName",
    sortable: false,
    getValue: (report) => {
      return report.universityName;
    },
  },
  eduLevel: {
    id: "eduLevel",
    label: "l.table.head.eduLevelName",
    sortable: false,
    getValue: (report) => {
      return report.eduLevelName;
    },
  },
  diplomaSpeciality: {
    id: "diplomaSpeciality",
    label: "l.table.head.diplomaSpeciality",
    sortable: false,
    getValue: (report) => {
      return report.specialityName;
    },
  },
  recognizedEduLevel: {
    id: "recognizedEduLevel",
    label: "l.table.head.recognizedEduLevel",
    sortable: false,
    getValue: (report) => {
      return report.recognizedEduLevelName;
    },
  },
  recognizedSpeciality: {
    id: "recognizedSpeciality",
    label: "l.table.head.recognizedSpeciality",
    sortable: false,
    getValue: (report) => {
      return report.recognizedSpecialityName;
    },
  },
  recognizedQualification: {
    id: "recognizedQualification",
    label: "l.table.head.recognizedQualification",
    sortable: false,
    getValue: (report) => {
      return report.recognizedQualification;
    },
  },
  recognizedProfGroup: {
    id: "recognizedProfGroup",
    label: "l.table.head.recognizedProfGroupName",
    sortable: false,
    getValue: (report) => {
      return report.recognizedProfGroupName;
    },
  },
  applicationStatus: {
    id: "applicationStatus",
    label: "l.table.head.applicationStatus",
    sortable: false,
    getValue: (report) => {
      return report.apnStatusName;
    },
  },
  docflowStatus: {
    id: "docflowStatus",
    label: "l.table.head.docflowStatusName",
    sortable: false,
    getValue: (report) => {
      return report.docflowStatusName;
    },
  },
  personalDocumentType: {
    id: "personalDocumentType",
    label: "l.table.head.personalDocumentType",
    sortable: false,
    getValue: (report) => {
      return report.personalDocumentTypeName;
    },
  },
  serviceType: {
    id: "serviceType",
    label: "l.table.head.serviceType",
    sortable: false,
    getValue: (report) => {
      return report.aseCode === RudiApplication.rudiSARApplication ? report.sarFlag : report.aseName;
    },
  },
};
