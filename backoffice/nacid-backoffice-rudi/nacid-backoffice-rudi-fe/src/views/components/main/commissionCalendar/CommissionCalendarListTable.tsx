import * as React from "react";
import { Fragment } from "react";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableRow from "@mui/material/TableRow";
import {
  AlertSpg,
  OptionTableCell,
  SectionMenuItem,
  SecurityGuard,
  SecurityRole,
  TableButton,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { useFormContext } from "react-hook-form";
import { Button } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAdd, faPlus } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import { openDeleteCalendarModal } from "../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";
import { BackofficeSearchTable } from "@duosoftbg/nacid-backoffice-components";
import { CommissionCalendarConst } from "../../../../utils/constants";

const headCells = [
  {
    id: "number",
    label: "l.table.head.number",
    sortable: false,
  },
  {
    id: "sessionNum",
    label: "l.table.head.sessionNum",
  },
  {
    id: "sessionTime",
    label: "l.table.head.sessionTime",
  },
  {
    id: "calendarSessionStatusName",
    label: "l.table.head.sessionStatus",
  },
  {
    id: "options",
    label: "",
    sortable: false,
  },
];

const CommissionCalendarListTable = ({ total, records, blockTable, onPageOrOrderChange, group }) => {
  const { t } = useTranslation();
  const { getValues } = useFormContext();
  const dispatch = useAppDispatch();

  const handleOpenDeleteModal = (id) => {
    const payload = { id };
    dispatch(openDeleteCalendarModal(payload));
  };

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
                <TableCell>{row.sessionNum}</TableCell>
                <TableCell>{row.sessionTime}</TableCell>
                <TableCell>{row.statusName}</TableCell>
                <OptionTableCell>
                  <TableButton type={"view"} to={`/commission-calendars/view/${row.id}`} />
                  <SecurityGuard checkForRoles={[SecurityRole.CommissionCalendarEdit]}>
                    <TableButton type={"edit"} to={`/commission-calendars/edit/${row.id}`} />
                    {row.statusCode !== CommissionCalendarConst.statusFinished && (
                      <TableButton type={"delete"} onClick={() => handleOpenDeleteModal(row.id)} />
                    )}
                  </SecurityGuard>
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
      <SecurityGuard checkForRoles={[SecurityRole.CommissionCalendarEdit]}>
        <Link to={"/commission-calendars/create"}>
          <Button
            startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faAdd} />}
            size={"medium"}
            type={"button"}
            variant="contained"
            color="primary"
          >
            {t("l.btn.create")}
          </Button>
        </Link>
      </SecurityGuard>
    </Fragment>
  );
};

export default CommissionCalendarListTable;
