import { useTranslation } from "react-i18next";
import { TableBody, TableCell, TableRow, Typography } from "@mui/material";

import React, { useState } from "react";
import {
  NacidTable,
  AlertSpg,
  RelativeBox,
  TableButton,
  OptionTableCell,
  SimpleConfirmDialog,
  useAsyncCall,
  AsyncCallArgs,
} from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import {
  changeOrderBy,
  changePage,
  changePageSize,
  updateFilter,
} from "../../../../store/redux/slice/Forms/myApplicationsFilterForm";
import { FoApplicationStatus } from "../../../../types/common/applicationTypes";
import {
  createAppViewUrl,
  createAppEditUrl,
  createAppSignUrl,
  getApplicationBaseUrl,
} from "../../../../utils/applicationUrlUtils";
import { Replay } from "@mui/icons-material";
import { deleteApplicationForId } from "../../../../services/serviceCalls";
import { toast } from "react-toastify";

const MyApplicationsTable = () => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const { asyncCall } = useAsyncCall();
  const initialDeleteState = {
    open: false,
    deleteId: null,
    deleteTempNum: null,
    deleteSubtype: null,
  };
  const [deleteDetails, setDeleteDetails] = useState(initialDeleteState);

  const myApps = useAppSelector((state) => {
    return state.MyApplications;
  });

  const myAppsFilterForm = useAppSelector((state) => {
    return state.Forms.MyApplicationsFilterForm;
  });

  const buildApplicationTheme = (record) => {
    if (record.inquiryKind) {
      return ": " + t("l.inquiry.kind." + record.inquiryKind);
    }
    if (record.officialNoteKind) {
      return ": " + t("l.officialNote.kind." + record.officialNoteKind);
    }
    if (record.nacidSearch && !record.foreignSearch) {
      return ": " + t("l.biblioReference.nacidSearch");
    }
    if (record.foreignSearch && !record.nacidSearch) {
      return ": " + t("l.biblioReference.foreignSearch");
    }
    return "";
  };

  const deleteApplication = (id, appSubtype) => {
    const deleteArgs: AsyncCallArgs = {
      promise: deleteApplicationForId(getApplicationBaseUrl(appSubtype), id),
      processResponseErrors: false,
      withGlobalBackdrop: true,
      onSuccess: () => {
        toast.success(t("m.delete.app.success"), { autoClose: 5000 });
        setDeleteDetails(initialDeleteState);
        dispatch(updateFilter({ ...myAppsFilterForm }));
      },
      onError: () => {
        toast.error(t("m.delete.app.error"), { autoClose: 5000 });
        setDeleteDetails(initialDeleteState);
      },
    };
    asyncCall(deleteArgs);
  };

  const headCells = [
    { id: "lastSubmissionDate", label: t("h.application.lastSubmissionDate"), sortable: true },
    { id: "tempNumber", label: t("h.application.tempNumber"), sortable: true },
    { id: "applicationSubtype", label: t("h.application.applicationSubtype"), sortable: false },
    { id: "entryNumber", label: t("h.application.entryNumberAndDate"), sortable: true },
    { id: "applicantName", label: t("h.application.applicantName"), sortable: false },
    { id: "lastStatusName", label: t("h.application.lastStatusName"), sortable: false },
    { id: "signed", label: t("h.application.signed"), sortable: false },
    { id: "paid", label: t("h.application.paid"), sortable: false },
    { id: "options", label: t("h.options"), sortable: false },
  ];

  if (myApps.list.data.totalElements === 0) {
    return <AlertSpg severity={"info"}>{t("m.empty.list")}</AlertSpg>;
  }

  return (
    <RelativeBox>
      <NacidTable
        total={myApps.list.data.totalElements}
        page={myAppsFilterForm.page}
        pageSize={myAppsFilterForm.pageSize}
        orderBy={myAppsFilterForm.orderBy}
        order={myAppsFilterForm.order}
        onSortClick={(e, prop) => {
          dispatch(changeOrderBy(prop));
        }}
        headCells={headCells}
        onPageChange={(e, page) => {
          dispatch(changePage(page));
        }}
        onRowsPerPageChange={(e) => {
          dispatch(changePageSize(e.target.value));
        }}
      >
        <TableBody>
          {myApps.list.data.content.map((record, index) => (
            <TableRow key={record.id}>
              <TableCell>{record.lastSubmissionDate}</TableCell>
              <TableCell style={{ color: record.revertedAndDraftOrFinalized ? "blue" : "" }}>
                <Typography
                  style={{ whiteSpace: "nowrap" }}
                  title={record.revertedAndDraftOrFinalized ? t("l.reverted.app") : null}
                >
                  {record.tempNumber}
                  {record.revertedAndDraftOrFinalized && <Replay />}
                </Typography>
              </TableCell>
              <TableCell>{`${t("l.application.subtype." + record.applicationSubtype)}${buildApplicationTheme(
                record
              )}`}</TableCell>
              <TableCell>{record.entryNumber && `${record.entryNumber}/${record.entryDate}`}</TableCell>
              <TableCell>{record.applicantName}</TableCell>
              <TableCell>{record.lastStatusName}</TableCell>
              <TableCell>{record.signed ? t("l.yes") : t("l.no")}</TableCell>
              <TableCell>{record.paid ? t("l.yes") : t("l.no")}</TableCell>
              <OptionTableCell>
                <TableButton
                  type={"view"}
                  title={t("l.btn.view")}
                  to={createAppViewUrl(record.id, record.applicationSubtype)}
                />
                {record.foStatus === FoApplicationStatus.DRAFT.valueOf() ? (
                  <TableButton
                    type={"edit"}
                    title={t("l.btn.edit")}
                    to={createAppEditUrl(record.id, record.applicationSubtype)}
                  />
                ) : null}
                {record.foStatus === FoApplicationStatus.FINALIZED.valueOf() ? (
                  <TableButton type={"signature"} title={t("l.btn.sign")} to={createAppSignUrl(record.id)} />
                ) : null}
                {record.foStatus === FoApplicationStatus.DRAFT.valueOf() && record.reverted === false ? (
                  <TableButton
                    type={"delete"}
                    title={t("l.btn.delete")}
                    onClick={() => {
                      setDeleteDetails({
                        open: true,
                        deleteId: record.id,
                        deleteTempNum: record.tempNumber,
                        deleteSubtype: record.applicationSubtype,
                      });
                    }}
                  />
                ) : null}
              </OptionTableCell>
            </TableRow>
          ))}
        </TableBody>
      </NacidTable>
      <SimpleConfirmDialog
        dialogTitleText={deleteDetails.deleteTempNum}
        alertType={"error"}
        open={deleteDetails.open}
        setOpen={(open) => {
          if (!open) {
            setDeleteDetails(initialDeleteState);
          }
        }}
        alertText={"m.delete.app.confirm"}
        onConfirm={() => deleteApplication(deleteDetails.deleteId, deleteDetails.deleteSubtype)}
      />
    </RelativeBox>
  );
};
export default MyApplicationsTable;
