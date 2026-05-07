import { useTranslation } from "react-i18next";
import { useFormContext } from "react-hook-form";
import * as React from "react";
import { Fragment } from "react";
import TableBody from "@mui/material/TableBody";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import { AlertSpg, OptionTableCell, TableButton, TableCellFlag } from "@duosoftbg/nacid-components";
import useCheckboxIdsControl from "../../../../hooks/useCheckboxIdsControl";
import Checkbox from "@mui/material/Checkbox";
import { BackofficeSearchTable } from "@duosoftbg/nacid-backoffice-components";
import { openViewCalendarMemberModal } from "../../../../../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";
import useAppDispatch from "../../../../../../../../hooks/redux/base/useAppDispatch";

const headCells = [
  {
    id: "number",
    label: "l.table.head.number",
    sortable: false,
  },
  {
    id: "id",
    label: "l.table.head.code",
  },
  {
    id: "firstName",
    label: "l.table.head.firstName",
  },
  {
    id: "lastName",
    label: "l.table.head.lastName",
  },
  {
    id: "profGroup",
    label: "l.table.head.profGroup",
  },
  {
    id: "isActive",
    label: "l.table.head.active",
  },
  {
    id: "options",
    label: "",
    sortable: false,
  },
];

const CalendarMembersListTable = ({ total, records, blockTable, onPageOrOrderChange, group }) => {
  const dispatch = useAppDispatch();
  const handleOpenViewCalendarMemberModal = (id) => {
    const payload = { id };
    dispatch(openViewCalendarMemberModal(payload));
  };
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
                <TableCell>{row.id}</TableCell>
                <TableCell>{row.firstName}</TableCell>
                <TableCell>{row.lastName}</TableCell>
                <TableCell>{row.profGroup?.name}</TableCell>
                <TableCellFlag flagValue={row.isActive} />
                <OptionTableCell>
                  <TableButton
                    type={"view"}
                    onClick={() => {
                      handleOpenViewCalendarMemberModal(row.id);
                    }}
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
export default CalendarMembersListTable;
