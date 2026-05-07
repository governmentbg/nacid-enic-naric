import useReportTableSelectedColumns from "../../../../../hooks/redux/report/useReportTableSelectedColumns";
import {
  selectActiveReportColumns,
  selectActiveReportHeadCells,
} from "../../../../../utils/report/reportTableHeadCells";
import { getAppTypeBySubtypeCode, ReportListTableBase } from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import ReportTableColumnsDialog from "../filters/dialog/table/ReportTableColumnsDialog";
import { SecurityGuard, SecurityRole, TableButton } from "@duosoftbg/nacid-components";
import { generateUrl } from "../../../../../utils/urls";

const ReportListTable = ({ total, records, blockTable, onPageOrOrderChange, searchGroup, reportGroup }) => {
  const selectedTableColumns = useReportTableSelectedColumns(reportGroup);
  const activeCells = selectActiveReportHeadCells(selectedTableColumns);
  const activeColumns = selectActiveReportColumns(selectedTableColumns);

  return (
    <ReportListTableBase
      total={total}
      records={records}
      blockTable={blockTable}
      onPageOrOrderChange={onPageOrOrderChange}
      searchGroup={searchGroup}
      activeCells={activeCells}
      activeColumns={activeColumns}
      reportColumnsDialog={<ReportTableColumnsDialog group={reportGroup} />}
      optionCellContent={OptionCellContent}
    />
  );
};

const OptionCellContent = ({ aseCode, id }) => {
  return (
    <>
      <TableButton type={"view"} to={generateUrl(getAppTypeBySubtypeCode(aseCode), id, "view")} target={"_blank"} />
      <SecurityGuard checkForRoles={[SecurityRole.RudiApplicationEdit]}>
        <TableButton type={"edit"} to={generateUrl(getAppTypeBySubtypeCode(aseCode), id, "edit")} target={"_blank"} />
      </SecurityGuard>
    </>
  );
};
export default ReportListTable;
