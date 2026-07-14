import * as React from "react";
import { Fragment } from "react";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableRow from "@mui/material/TableRow";
import { AlertSpg, OptionTableCell, SecurityGuard, SecurityRole, TableButton } from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { useFormContext } from "react-hook-form";
import useAppTableSelectedColumns from "../../../../../hooks/redux/application/useAppTableSelectedColumns";
import {
  selectActiveApplicationsColumns,
  selectActiveApplicationsHeadCells,
} from "../../../../../utils/application/applicationsTableHeadCells";
import AppListTableColumnsDialog from "../common/components/AppListTableColumnsDialog";
import { BackofficeSearchTable } from "@duosoftbg/nacid-backoffice-components";
import { Computer, HistoryEdu } from "@mui/icons-material";

const DocrecAppsListTable = ({ total, records, blockTable, onPageOrOrderChange, group, applicationGroup }) => {
  const { t } = useTranslation();
  const { getValues } = useFormContext();
  const selectedTableColumns = useAppTableSelectedColumns(applicationGroup);
  const activeCells = selectActiveApplicationsHeadCells(selectedTableColumns);
  const activeColumns = selectActiveApplicationsColumns(selectedTableColumns);

  return (
    <Fragment>
      {total > 0 && (
        <>
          <AppListTableColumnsDialog group={applicationGroup} title={"t.modal.selectTableColumns"} />
          <BackofficeSearchTable
            group={group}
            headCells={activeCells}
            total={total}
            blockTable={blockTable}
            onPageOrOrderChange={onPageOrOrderChange}
          >
            <TableBody>
              {records.map((row, index) => (
                <TableRow hover key={row.id}>
                  <TableCell>{index + 1 + getValues().page * getValues().pageSize}</TableCell>
                  <TableCell style={{ textAlign: "center", width: "35px" }}>
                    {row.efilingId && (
                      <span title={t("l.eFiled")}>
                        <Computer />
                      </span>
                    )}
                    {!row.efilingId && (
                      <span title={t("l.deskFiled")}>
                        <HistoryEdu />
                      </span>
                    )}
                  </TableCell>
                  <>
                    {activeColumns.map((cell) => (
                      <TableCell key={cell.id} style={cell.getStyle(row)}>
                        {cell.getValue(row)}
                      </TableCell>
                    ))}
                  </>
                  <OptionTableCell>
                    <TableButton type={"view"} to={`/docrec-applications/view/${row.id}`} />
                    <SecurityGuard checkForRoles={[SecurityRole.RudiApplicationEdit]}>
                      <TableButton type={"edit"} to={`/docrec-applications/edit/${row.id}`} />
                    </SecurityGuard>
                  </OptionTableCell>
                </TableRow>
              ))}
            </TableBody>
          </BackofficeSearchTable>
        </>
      )}
      {!(total > 0) && (
        <AlertSpg mt={10} mb={10} severity="info">
          {t("m.empty.list")}
        </AlertSpg>
      )}
    </Fragment>
  );
};

export default DocrecAppsListTable;
