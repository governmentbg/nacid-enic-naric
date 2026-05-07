import { ApplicationGroup } from "../../types/applications/common/application";
import { APPLICATION_TABLE_COLUMNS_DEFINITION } from "./table/definition";

export const APPLICATION_GROUP: ApplicationGroup = {
  SAR_APPLICATION: "sar_application",
  UDIREC_APPLICATION: "udirec_application",
  DOCREC_APPLICATION: "docrec_application",
};

export const APPLICATION_CONFIG = {
  [APPLICATION_GROUP.SAR_APPLICATION]: {
    tableColumns: [
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.entryNum.id, active: true },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.entryDate.id, active: true },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.backofficeDate.id, active: true },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.applicantName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.diplomaOwnerName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.universityName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.universityCountryName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.eduLevelName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.specialityName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.apnStatusName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.docflowStatusName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.sarFlag.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.responsibleUserName.id, active: false },
    ],
  },
  [APPLICATION_GROUP.UDIREC_APPLICATION]: {
    tableColumns: [
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.entryNum.id, active: true },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.entryDate.id, active: true },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.backofficeDate.id, active: true },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.applicantName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.universityName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.universityCountryName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.eduLevelName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.specialityName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.apnStatusName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.docflowStatusName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.responsibleUserName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.recognizedQualification.id, active: false },
    ],
  },
  [APPLICATION_GROUP.DOCREC_APPLICATION]: {
    tableColumns: [
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.entryNum.id, active: true },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.entryDate.id, active: true },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.backofficeDate.id, active: true },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.applicantName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.universityName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.universityCountryName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.eduLevelName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.specialityName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.apnStatusName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.docflowStatusName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.responsibleUserName.id, active: false },
      { id: APPLICATION_TABLE_COLUMNS_DEFINITION.recognizedProfGroupName.id, active: false },
    ],
  },
};
