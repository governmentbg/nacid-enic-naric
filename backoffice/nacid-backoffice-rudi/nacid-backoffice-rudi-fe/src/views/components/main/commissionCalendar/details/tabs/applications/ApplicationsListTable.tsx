import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import { Table, TableBody, TableContainer, TableHead, TableSortLabel } from "@mui/material";
import React, { useEffect, useState } from "react";
import {
  AlertSpg,
  ASC_ORDER,
  AsyncCallArgs,
  CircularLoader,
  DESC_ORDER,
  isArrayNotEmpty,
  OptionTableCell,
  TableButton,
  useAsyncCall,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { getCommissionApplications } from "../../../../../../../axios/api/services";
import { AppUrlBuilder, CertificateReceiveFrom } from "@duosoftbg/nacid-backoffice-components";
import Checkbox from "@mui/material/Checkbox";
import { CalendarApplicationsSortFields } from "../../../../../../../utils/constants";
import { KeyboardArrowDown, KeyboardArrowDownRounded } from "@mui/icons-material";

export const ApplicationSortLabel = ({ sortTableClick, sortColumnName, ascSort, columnName, columnLabel }) => {
  const { t } = useTranslation();
  return (
    <TableCell>
      <TableSortLabel
        active={sortColumnName === columnName}
        direction={ascSort ? ASC_ORDER : DESC_ORDER}
        onClick={(event) => {
          sortTableClick(columnName);
        }}
        IconComponent={ascSort ? KeyboardArrowDown : KeyboardArrowDownRounded}
      >
        {t(columnLabel)}
      </TableSortLabel>
    </TableCell>
  );
};

const ApplicationsListTable = ({ applicationIds, deleteFunc = null }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [applications, setApplications] = useState(null);
  const [sortColumnName, setSortColumnName] = useState(CalendarApplicationsSortFields[1].id);
  const [ascSort, setAscSort] = useState(false);

  const callGetApplications = () => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getCommissionApplications(applicationIds, sortColumnName, ascSort),
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
  }, [applicationIds, sortColumnName, ascSort]);

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
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
        {isArrayNotEmpty(applications) && (
          <TableBody>
            {applications.map((application, index) => (
              <TableRow key={"application-row-" + application.id}>
                <TableCell>{index + 1}</TableCell>
                <TableCell>{application.entryNum}</TableCell>
                <TableCell>{application.entryDate}</TableCell>
                <TableCell>{application.applicantName}</TableCell>
                <TableCell>{application.universityName}</TableCell>
                <TableCell>{application.universityCountryName}</TableCell>
                <TableCell>{application.recognizedEduLevelName}</TableCell>
                <TableCell>{application.recognizedSpecialityName}</TableCell>
                <TableCell>{application.apnStatusName}</TableCell>
                <TableCell style={{ minWidth: "80px" }}>
                  <Checkbox
                    checked={!!application.crfCode?.includes(CertificateReceiveFrom.PAPER)}
                    title={t("l.crf.type.paper")}
                  />
                  <Checkbox
                    checked={!!application.crfCode?.includes(CertificateReceiveFrom.ELECTRONIC)}
                    title={t("l.crf.type.electronic")}
                  />
                </TableCell>
                <OptionTableCell>
                  <TableButton
                    type={"edit"}
                    to={AppUrlBuilder.editApplication(application?.ateCode, application?.aseCode, application.id)}
                    target={"_blank"}
                    externalLink={true}
                  />

                  {deleteFunc && (
                    <TableButton
                      type={"delete"}
                      onClick={() => {
                        deleteFunc(application.id);
                      }}
                    />
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

export default ApplicationsListTable;
