import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import { Table, TableBody, TableContainer, TableHead } from "@mui/material";
import React, { useEffect, useState } from "react";
import {
  AlertSpg,
  AsyncCallArgs,
  CircularLoader,
  FinalDecisionTableButton,
  isArrayNotEmpty,
  OptionTableCell,
  TableButton,
  useAsyncCall,
  useReloadWatcherReader,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { getCommissionApplicationsByIdsAndCalendarId } from "../../../../../../../axios/api/services";
import { AppUrlBuilder, CertificateReceiveFrom, ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";
import useCheckboxIdsControl from "../../../hooks/useCheckboxIdsControl";
import Checkbox from "@mui/material/Checkbox";
import { CalendarApplicationsSortFields } from "../../../../../../../utils/constants";
import { ApplicationSortLabel } from "../applications/ApplicationsListTable";

const ProcessingAppListTable = ({ applicationIds, calendarId }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [applications, setApplications] = useState(null);
  const [sortColumnName, setSortColumnName] = useState(CalendarApplicationsSortFields[1].id);
  const [ascSort, setAscSort] = useState(false);
  const { reloadWatcher } = useReloadWatcherReader(ReloadWatcherObject.build("calendarApplications", "reload"));

  const { handleSingleIdToggle, handleMultipleIdsToggle, isSingleAddChecked, isMultipleAddChecked } =
    useCheckboxIdsControl({
      records: applications ? applications.filter((app) => !app.generatedFinalDoc) : null,
    });

  const callGetApplications = () => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getCommissionApplicationsByIdsAndCalendarId(applicationIds, calendarId, sortColumnName, ascSort),
      onSuccess: (response) => {
        setApplications(response);
        setLoading(false);
        setError(false);
      },
      onError: () => {
        setError(true);
        setLoading(false);
      },
    };
    asyncCall(asyncCallArgs);
  };

  useEffect(() => {
    if (isArrayNotEmpty(applicationIds)) {
      callGetApplications();
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [applicationIds, sortColumnName, ascSort, reloadWatcher]);

  if (loading) {
    return <CircularLoader />;
  }

  if (error) {
    return <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>;
  }

  const sortTableClick = (sortColumnNameParam) => {
    if (sortColumnNameParam === sortColumnName) {
      setAscSort(!ascSort);
    } else {
      setSortColumnName(sortColumnNameParam);
    }
  };

  return (
    <TableContainer>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>{t("l.table.head.number")}</TableCell>
            <TableCell style={{ width: "10px" }}>
              <Checkbox checked={isMultipleAddChecked()} onClick={handleMultipleIdsToggle} />
            </TableCell>

            {CalendarApplicationsSortFields.map((column) => (
              <ApplicationSortLabel
                key={column.id}
                sortTableClick={sortTableClick}
                sortColumnName={sortColumnName}
                ascSort={ascSort}
                columnName={column.id}
                columnLabel={column.label}
              ></ApplicationSortLabel>
            ))}
            <TableCell>{t("l.table.head.crfType")}</TableCell>
            <TableCell>{t("l.table.head.generated.document")}</TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
        {isArrayNotEmpty(applications) && (
          <TableBody>
            {applications.map((application, index) => (
              <TableRow key={"application-row-" + application.id}>
                <TableCell>{index + 1}</TableCell>

                <TableCell>
                  <Checkbox
                    disabled={application.generatedFinalDoc}
                    checked={isSingleAddChecked(application.id)}
                    onClick={(event) => {
                      handleSingleIdToggle(event, application.id);
                    }}
                  />
                </TableCell>

                <TableCell>{application.entryNum}</TableCell>
                <TableCell>{application.entryDate}</TableCell>
                <TableCell>{application.applicantName}</TableCell>
                <TableCell>{application.universityName}</TableCell>
                <TableCell>{application.universityCountryName}</TableCell>
                <TableCell>{application.recognizedEduLevelName}</TableCell>
                <TableCell>{application.recognizedSpecialityName}</TableCell>
                <TableCell>{application.apnStatusName}</TableCell>
                <TableCell style={{ minWidth: "90px" }}>
                  <Checkbox
                    checked={!!application.crfCode?.includes(CertificateReceiveFrom.PAPER)}
                    title={t("l.crf.type.paper")}
                  />
                  <Checkbox
                    checked={!!application.crfCode?.includes(CertificateReceiveFrom.ELECTRONIC)}
                    title={t("l.crf.type.electronic")}
                  />
                </TableCell>

                <TableCell style={{ minWidth: "90px" }}>
                  <Checkbox checked={application.generatedFinalDoc} title={t("l.generated")} />
                  <Checkbox checked={application.abdocsTransferred} title={t("l.abdocs.transferred")} />
                </TableCell>

                <OptionTableCell>
                  <TableButton
                    type={"edit"}
                    to={AppUrlBuilder.editApplication(application?.ateCode, application?.aseCode, application.id)}
                    target={"_blank"}
                    externalLink={true}
                  />

                  <FinalDecisionTableButton
                    to={`/commission-calendars/edit/${calendarId}/commission-calendar-process/edit/${application.id}`}
                  />
                </OptionTableCell>
              </TableRow>
            ))}
          </TableBody>
        )}
      </Table>
    </TableContainer>
  );
};

export default ProcessingAppListTable;
