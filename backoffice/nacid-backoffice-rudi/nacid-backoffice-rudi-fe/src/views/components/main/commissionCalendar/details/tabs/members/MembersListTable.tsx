import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import { Checkbox, Table, TableBody, TableContainer, TableHead } from "@mui/material";
import React from "react";
import { isArrayNotEmpty, OptionTableCell, TableButton, TableCellFlag } from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import {
  openDeleteMemberModal,
  openMemberAdditionalData,
  openViewCalendarMemberModal,
} from "../../../../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";

const MembersListTable = ({ members, setChairman = null, isViewMode = false }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const onDeleteMemberClick = (id) => {
    dispatch(openDeleteMemberModal({ id: id }));
  };

  const onEditMemberAdditionalData = (member) => {
    dispatch(openMemberAdditionalData({ member: member }));
  };

  const handleOpenViewCalendarMemberModal = (id) => {
    const payload = { id };
    dispatch(openViewCalendarMemberModal(payload));
  };

  return (
    <TableContainer>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>{t("l.table.head.number")}</TableCell>
            <TableCell>{t("l.table.head.chairman")}</TableCell>
            <TableCell>{t("l.table.head.code")}</TableCell>
            <TableCell>{t("l.table.head.firstName")}</TableCell>
            <TableCell>{t("l.table.head.lastName")}</TableCell>
            <TableCell>{t("l.table.head.profGroup")}</TableCell>
            <TableCell>{t("l.table.head.notified")}</TableCell>
            <TableCell>{t("l.table.head.participated")}</TableCell>
            <TableCell>{t("l.table.head.active.person")}</TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
        {isArrayNotEmpty(members) && (
          <TableBody>
            {members.map((memberObj, index) => (
              <TableRow key={"member-row-" + memberObj.member.id}>
                <TableCell>{index + 1}</TableCell>
                <TableCell style={{ width: "5%", textAlign: "center" }}>
                  {
                    <Checkbox
                      disabled={isViewMode}
                      checked={memberObj?.chairman}
                      onChange={(event) => {
                        setChairman(memberObj.member.id, event.target.checked);
                      }}
                    />
                  }
                </TableCell>
                <TableCell>{memberObj.member.id}</TableCell>
                <TableCell>{memberObj.member.firstName}</TableCell>
                <TableCell>{memberObj.member.lastName}</TableCell>
                <TableCell>{memberObj.member.profGroup?.name}</TableCell>
                <TableCellFlag flagValue={memberObj?.notified} />
                <TableCellFlag flagValue={memberObj?.participated} />
                <TableCellFlag flagValue={memberObj.member.isActive} />
                <OptionTableCell>
                  <TableButton
                    type={"view"}
                    onClick={() => {
                      handleOpenViewCalendarMemberModal(memberObj.member.id);
                    }}
                  />
                  {!isViewMode && (
                    <>
                      <TableButton
                        type={"edit"}
                        onClick={() => {
                          onEditMemberAdditionalData({ ...memberObj });
                        }}
                      />
                      <TableButton
                        type={"delete"}
                        onClick={() => {
                          onDeleteMemberClick(memberObj.member.id);
                        }}
                      />
                    </>
                  )}
                </OptionTableCell>
              </TableRow>
            ))}
          </TableBody>
        )}
      </Table>
    </TableContainer>
  );
};

export default MembersListTable;
