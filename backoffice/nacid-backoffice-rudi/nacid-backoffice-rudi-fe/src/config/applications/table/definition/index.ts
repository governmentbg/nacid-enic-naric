import { ApplicationTableColumnsDefinition } from "../../../../types/applications/common/application";
import { UDIREC_APPLICATION_TABLE_COLUMNS } from "./slice/udirec";
import { DOCREC_APPLICATION_TABLE_COLUMNS } from "./slice/docrec";
import { UNIVERSAL_APPLICATION_TABLE_COLUMNS } from "./slice/universal";
import { SAR_APPLICATION_TABLE_COLUMNS } from "./slice/sar";

export const APPLICATION_TABLE_COLUMNS_DEFINITION: ApplicationTableColumnsDefinition = {
  ...UDIREC_APPLICATION_TABLE_COLUMNS,
  ...SAR_APPLICATION_TABLE_COLUMNS,
  ...DOCREC_APPLICATION_TABLE_COLUMNS,
  ...UNIVERSAL_APPLICATION_TABLE_COLUMNS,
};
