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
  ReloadWatcherObject,
  useAsyncCall,
  useReloadWatcherReader,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAdd } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import { getApplicationCommissionMembers } from "../../../../../../../../../../../../axios/api/services";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import ExpertsListTableRow from "./ExpertsListTableRow";

const ExpertsListTable = ({ applicationId, appType }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [experts, setExperts] = useState(null);
  const { reloadWatcher } = useReloadWatcherReader(ReloadWatcherObject.build("expertsData", "delete"));

  const urlsConfig = {
    [AppType.SAR_APPLICATION]: {
      view: `/sar-applications/edit/{applicationId}/commission-members/view/{expertId}`,
      edit: `/sar-applications/edit/{applicationId}/commission-members/edit/{expertId}`,
      add: `/sar-applications/edit/{applicationId}/commission-members/add`,
    },
    [AppType.DOCREC_APPLICATION]: {
      view: `/docrec-applications/edit/{applicationId}/commission-members/view/{expertId}`,
      edit: `/docrec-applications/edit/{applicationId}/commission-members/edit/{expertId}`,
      add: `/docrec-applications/edit/{applicationId}/commission-members/add`,
    },
    [AppType.UDIREC_APPLICATION]: {
      view: `/udirec-applications/edit/{applicationId}/commission-members/view/{expertId}`,
      edit: `/udirec-applications/edit/{applicationId}/commission-members/edit/{expertId}`,
      add: `/udirec-applications/edit/{applicationId}/commission-members/add`,
    },
  };

  const generateUrl = (appType, applicationId, expertId, url) => {
    return urlsConfig[appType][url].replace("{applicationId}", applicationId).replace("{expertId}", expertId);
  };

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getApplicationCommissionMembers(applicationId),
      onSuccess: (response) => {
        setExperts(response);
        setLoading(false);
        setError(false);
      },
      onError: () => {
        setExperts(null);
        setError(true);
        setLoading(false);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [applicationId, reloadWatcher]);

  if (loading) {
    return <CircularLoader />;
  }

  if (error) {
    return <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>;
  }

  return (
    <>
      {isArrayNotEmpty(experts) && (
        <TableContainer>
          <Table aria-label="collapsible table">
            <TableHead>
              <TableRow>
                <TableCell style={{ width: 35 }} />
                <TableCell>{t("l.table.head.number")}</TableCell>
                <TableCell>{t("l.table.head.expertName")}</TableCell>
                <TableCell></TableCell>
              </TableRow>
            </TableHead>

            <TableBody>
              {experts.map((expert, index) => (
                <ExpertsListTableRow
                  key={"expert-row-" + index}
                  expert={expert}
                  index={index}
                  appType={appType}
                  applicationId={applicationId}
                  generateUrl={generateUrl}
                ></ExpertsListTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      )}
      {isArrayEmpty(experts) && <AlertSpg severity="info">{t("m.empty.list")}</AlertSpg>}
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

export default ExpertsListTable;
