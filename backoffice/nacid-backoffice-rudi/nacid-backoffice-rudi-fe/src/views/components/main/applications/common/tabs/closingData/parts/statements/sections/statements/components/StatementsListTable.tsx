import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import { Button, Table, TableBody, TableContainer, TableHead } from "@mui/material";
import React, { useEffect, useState } from "react";
import {
  AlertSpg,
  AsyncCallArgs,
  CircularLoader,
  GridContainer,
  GridItem,
  isArrayEmpty,
  isArrayNotEmpty,
  OptionTableCell,
  ReferenceDataCode,
  TableButton,
  useAsyncCall,
  useReloadWatcherReader,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAdd } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import useAppDispatch from "../../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import { getApplicationCommissionMemberStatements } from "../../../../../../../../../../../../axios/api/services";
import { openDeleteStatementModal } from "../../../../../../../../../../../../store/redux/slice/ComponentsControl/applicationsControl";
import {
  AbdocsTransferDocButton,
  AbdocsViewDocButton,
  AppType,
  AttachmentTableCellContent,
  AttachmentType,
  ReloadWatcherObject,
  useAbdocsTransferRenderConfig,
} from "@duosoftbg/nacid-backoffice-components";

const urlsConfig = {
  [AppType.SAR_APPLICATION]: {
    view: `/sar-applications/edit/{applicationId}/commission-member-statements/view/{statementId}`,
    edit: `/sar-applications/edit/{applicationId}/commission-member-statements/edit/{statementId}`,
    add: `/sar-applications/edit/{applicationId}/commission-member-statements/add`,
  },
  [AppType.DOCREC_APPLICATION]: {
    view: `/docrec-applications/edit/{applicationId}/commission-member-statements/view/{statementId}`,
    edit: `/docrec-applications/edit/{applicationId}/commission-member-statements/edit/{statementId}`,
    add: `/docrec-applications/edit/{applicationId}/commission-member-statements/add`,
  },
  [AppType.UDIREC_APPLICATION]: {
    view: `/udirec-applications/edit/{applicationId}/commission-member-statements/view/{statementId}`,
    edit: `/udirec-applications/edit/{applicationId}/commission-member-statements/edit/{statementId}`,
    add: `/udirec-applications/edit/{applicationId}/commission-member-statements/add`,
  },
};

const generateUrl = (appType, applicationId, statementId, url) => {
  return urlsConfig[appType][url].replace("{applicationId}", applicationId).replace("{statementId}", statementId);
};

const StatementsListTable = ({ applicationId, appType }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [statements, setStatements] = useState(null);
  const { reloadWatcher } = useReloadWatcherReader(ReloadWatcherObject.build("statementsData", "delete"));
  const { reloadWatcher: transferWatcher } = useReloadWatcherReader(ReloadWatcherObject.AbdocsTransferConfig.reload());

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getApplicationCommissionMemberStatements(applicationId),
      onSuccess: (response) => {
        setStatements(response);
        setLoading(false);
        setError(false);
      },
      onError: () => {
        setStatements(null);
        setError(true);
        setLoading(false);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [applicationId, reloadWatcher, transferWatcher]);

  if (loading) {
    return <CircularLoader />;
  }

  if (error) {
    return <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>;
  }

  return (
    <>
      {isArrayNotEmpty(statements) && (
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>{t("l.table.head.number")}</TableCell>
                <TableCell>{t("l.table.head.expertName")}</TableCell>
                <TableCell>{t("l.table.head.documentType")}</TableCell>
                <TableCell>{t("l.table.internal.files")}</TableCell>
                <TableCell>{t("l.table.public.files")}</TableCell>
                <TableCell></TableCell>
              </TableRow>
            </TableHead>

            <TableBody>
              {statements.map((statement, index) => (
                <StatementsListTableRow
                  key={`statement-row-${statement.id}-${statement.attachedDoc.docflowId}`}
                  statement={statement}
                  index={index}
                  applicationId={applicationId}
                  appType={appType}
                />
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      )}
      {isArrayEmpty(statements) && <AlertSpg severity="info">{t("m.empty.list")}</AlertSpg>}
      <GridContainer spacing={3} mt={0}>
        <GridItem sm={12} md={12}>
          <Link to={generateUrl(appType, applicationId, "", "add")}>
            <Button
              startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faAdd} />}
              size={"medium"}
              type={"button"}
              variant="contained"
              color="primary"
            >
              {t("l.btn.add")}
            </Button>
          </Link>
        </GridItem>
      </GridContainer>
    </>
  );
};

const StatementsListTableRow = ({ statement, index, applicationId, appType }) => {
  const dispatch = useAppDispatch();
  const { hasAbdocsTransfer, hasAbdocsLink, abdocsDoc } = useAbdocsTransferRenderConfig(statement.attachedDoc);

  const handleOpenDeleteModal = (id) => {
    const payload = { id };
    dispatch(openDeleteStatementModal(payload));
  };

  return (
    <TableRow>
      <TableCell>{index + 1}</TableCell>
      <TableCell>
        {statement.commissionMember.firstName} {statement.commissionMember.middleName}{" "}
        {statement.commissionMember.lastName}
      </TableCell>
      <TableCell> {statement?.attachedDoc?.documentType?.name}</TableCell>
      <TableCell>
        <AttachmentTableCellContent
          visibilityCode={ReferenceDataCode.ATTACHMENT_VISIBILITY_INTERNAL}
          hasAbdocsLink={hasAbdocsLink}
          attachmentData={statement.attachedDoc}
          abdocsDoc={abdocsDoc}
        />
      </TableCell>
      <TableCell>
        <AttachmentTableCellContent
          visibilityCode={ReferenceDataCode.ATTACHMENT_VISIBILITY_PUBLIC}
          hasAbdocsLink={hasAbdocsLink}
          attachmentData={statement.attachedDoc}
          abdocsDoc={abdocsDoc}
        />
      </TableCell>
      <OptionTableCell>
        {!hasAbdocsLink && (
          <>
            <TableButton type={"view"} to={generateUrl(appType, applicationId, statement.id, "view")} />
            <TableButton type={"edit"} to={generateUrl(appType, applicationId, statement.id, "edit")} />
            <TableButton type={"delete"} onClick={() => handleOpenDeleteModal(statement.id)} />
          </>
        )}
        {hasAbdocsTransfer && (
          <AbdocsTransferDocButton
            applicationId={applicationId}
            attachmentData={statement.attachedDoc}
            attachmentType={AttachmentType.RUDI_COMMISSION_MEMBER_STATEMENT_ATTACHMENT}
          />
        )}
        {hasAbdocsLink && <AbdocsViewDocButton attachmentData={statement.attachedDoc} abdocsDoc={abdocsDoc} />}
      </OptionTableCell>
    </TableRow>
  );
};

export default StatementsListTable;
