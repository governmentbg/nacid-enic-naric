import * as React from "react";
import { Fragment } from "react";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableRow from "@mui/material/TableRow";
import {
  AlertSpg,
  NotesLabel,
  OptionTableCell,
  RevertStatusLabel,
  SecurityGuard,
  SecurityRole,
  TableButton,
  TableCellFlag,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { useFormContext } from "react-hook-form";
import {
  AcceptTableButton,
  AppType,
  BackofficeSearchTable,
  EntryNumberLabel,
  FoAppRevertDeniedStatusTableButton,
} from "@duosoftbg/nacid-backoffice-components";
import { Typography } from "@mui/material";
import { generateUrl } from "../../../../../../utils/urls";

const headCells = [
  {
    id: "number",
    label: "l.table.head.number",
    sortable: false,
  },
  {
    id: "entryNumber",
    label: "l.table.head.entryNumber",
    sortable: false,
  },
  {
    id: "lastSubmissionDate",
    label: "l.table.head.lastSubmissionDate",
    sortable: true,
  },
  {
    id: "applicantName",
    label: "l.table.head.applicantName",
    sortable: true,
  },
  {
    id: "foStatusName",
    label: "l.table.head.status",
    sortable: true,
  },
  {
    id: "signed",
    label: "l.table.head.signed",
    sortable: false,
  },
  {
    id: "options",
    label: "",
    sortable: false,
  },
];

const FoDocrecAppsListTable = ({ total, records, blockTable, onPageOrOrderChange, group }) => {
  const { t } = useTranslation();
  const { getValues } = useFormContext();

  return (
    <Fragment>
      {total > 0 && (
        <BackofficeSearchTable
          group={group}
          headCells={headCells}
          total={total}
          blockTable={blockTable}
          onPageOrOrderChange={onPageOrOrderChange}
        >
          <TableBody>
            {records.map((row, index) => (
              <TableRow hover key={row.id}>
                <TableCell>{index + 1 + getValues().page * getValues().pageSize}</TableCell>
                <TableCell>
                  {row.entryNumber && row.entryDate && (
                    <Typography style={{ whiteSpace: "nowrap" }}>
                      <EntryNumberLabel
                        row={row}
                        url={generateUrl(AppType.DOCREC_APPLICATION, row.boApnId, "edit")}
                        roles={[SecurityRole.RudiApplicationEdit]}
                      />
                      {row?.revertedAndNotAccepted && <RevertStatusLabel />}
                      {row?.notesCount > 0 && <NotesLabel />}
                    </Typography>
                  )}
                </TableCell>
                <TableCell>{row.lastSubmissionDate}</TableCell>
                <TableCell sx={{ whiteSpace: "normal", wordBreak: "break-word" }}>{row.applicantName}</TableCell>
                <TableCell>{row.foStatusName}</TableCell>
                <TableCellFlag flagValue={row.signed} />
                <OptionTableCell>
                  <TableButton type={"view"} to={`/docrec-e-apps/${row.id}/view`} />
                  <SecurityGuard checkForRoles={[SecurityRole.FoServicesAccept]}>
                    <AcceptTableButton appType={AppType.DOCREC_APPLICATION} record={row} />
                  </SecurityGuard>
                  <FoAppRevertDeniedStatusTableButton record={row} />
                </OptionTableCell>
              </TableRow>
            ))}
          </TableBody>
        </BackofficeSearchTable>
      )}
      {!(total > 0) && (
        <AlertSpg mt={10} mb={10} severity="info">
          {t("m.empty.list")}
        </AlertSpg>
      )}
    </Fragment>
  );
};

export default FoDocrecAppsListTable;
