import * as React from "react";
import { Fragment } from "react";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableRow from "@mui/material/TableRow";
import { AlertSpg, OptionTableCell, TableButton } from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { useFormContext } from "react-hook-form";
import Checkbox from "@mui/material/Checkbox";
import useCheckboxIdsControl from "../../../../hooks/useCheckboxIdsControl";
import { BackofficeSearchTable, AppUrlBuilder } from "@duosoftbg/nacid-backoffice-components";

let headCells = [
  {
    id: "number",
    label: "l.table.head.number",
    sortable: false,
  },
  {
    id: "entryNum",
    label: "l.entryNum",
  },
  {
    id: "entryDate",
    label: "l.table.head.entryDate",
  },
  {
    id: "applicantName",
    label: "l.table.head.applicantName",
  },
  {
    id: "universityName",
    label: "l.table.head.foreign.universityName",
  },
  {
    id: "universityCountryName",
    label: "l.table.head.universityCountryName",
  },
  {
    id: "eduLevelName",
    label: "l.table.head.eduLevelName",
  },
  {
    id: "specialityName",
    label: "l.table.head.specialityName",
  },
  {
    id: "apnStatusName",
    label: "l.table.head.apnStatusName",
  },
  {
    id: "docflowStatusName",
    label: "l.table.head.docflowStatusName",
  },
  {
    id: "recognizedQualification",
    label: "l.table.head.recognizedQualification",
  },
  {
    id: "options",
    label: "",
    sortable: false,
  },
];

const CalendarDiplomaRecognitionListTable = ({ total, records, blockTable, onPageOrOrderChange, group }) => {
  const { handleSingleIdToggle, handleMultipleIdsToggle, isSingleAddChecked, isMultipleAddChecked } =
    useCheckboxIdsControl({
      records: records,
    });

  let headCellsUpdated = [...headCells];
  headCellsUpdated.splice(1, 0, {
    id: "globalCheckboxSelect",
    label: "",
    // @ts-ignore
    component: <Checkbox checked={isMultipleAddChecked()} onClick={handleMultipleIdsToggle} />,
  });

  const { t } = useTranslation();
  const { getValues } = useFormContext();

  return (
    <Fragment>
      {total > 0 && (
        <BackofficeSearchTable
          group={group}
          headCells={headCellsUpdated}
          total={total}
          blockTable={blockTable}
          onPageOrOrderChange={onPageOrOrderChange}
        >
          <TableBody>
            {records.map((row, index) => (
              <TableRow hover key={row.id}>
                <TableCell>{index + 1 + getValues().page * getValues().pageSize}</TableCell>
                <TableCell>
                  <Checkbox
                    checked={isSingleAddChecked(row.id)}
                    onClick={(event) => {
                      handleSingleIdToggle(event, row.id);
                    }}
                  />
                </TableCell>
                <TableCell>{row.entryNum}</TableCell>
                <TableCell>{row.entryDate}</TableCell>
                <TableCell>{row.applicantName}</TableCell>
                <TableCell>{row.universityName}</TableCell>
                <TableCell>{row.universityCountryName}</TableCell>
                <TableCell>{row.eduLevelName}</TableCell>
                <TableCell>{row.specialityName}</TableCell>
                <TableCell>{row.apnStatusName}</TableCell>
                <TableCell>{row.docflowStatusName}</TableCell>
                <TableCell>{row.recognizedQualification}</TableCell>
                <OptionTableCell>
                  <TableButton
                    type={"view"}
                    to={AppUrlBuilder.viewApplication(row?.ateCode, row?.aseCode, row.id)}
                    target={"_blank"}
                    externalLink={true}
                  />
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

export default CalendarDiplomaRecognitionListTable;
