import {
  AlertSpg,
  AsyncCallArgs,
  BoxSpg,
  CircularLoader,
  DividerSpg,
  FormDirtyStateControlActions,
  GridContainer,
  GridItem,
  isArrayEmpty,
  isArrayNotEmpty,
  useAsyncCall,
} from "@duosoftbg/nacid-components";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import React from "react";
import { saveCalendarApplications } from "../../../../../../../axios/api/services";
import ApplicationsListTable from "./ApplicationsListTable";
import { Button, Typography } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAdd } from "@fortawesome/free-solid-svg-icons";
import AddApplicationsDialog from "./dialog/AddApplicationsDialog";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import {
  openAddApplicationDialog,
  openDeleteApplicationModal,
} from "../../../../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";
import DeleteApplicationDialog from "./dialog/DeleteApplicationDialog";
import { toast } from "react-toastify";
import useApplicationIdsControl from "../../../hooks/useApplicationIdsControl";

const Applications = () => {
  const calendarId = useParams().calendarId;
  const { applicationIds, setApplicationIds, error, loading } = useApplicationIdsControl({
    calendarId: calendarId,
  });
  const { asyncCall } = useAsyncCall();

  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const handleOpenAddApplicationDialog = (excludedApplications) => {
    const payload = { excludedApplications };
    dispatch(openAddApplicationDialog(payload));
  };

  const onSaveApplications = () => {
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: saveCalendarApplications(calendarId, applicationIds),
      onSuccess: (response) => {
        toast.success(t("m.create.success"));
      },
    };
    asyncCall(asyncCreation);
    dispatch(FormDirtyStateControlActions.setDirty({ dirty: false }));
  };

  const onDeleteApplicationClick = (id) => {
    dispatch(openDeleteApplicationModal({ id: id }));
  };

  if (loading) {
    return (
      <BoxSpg>
        <BoxSpg my={5} textAlign={"center"}>
          {t("t.applications")}
        </BoxSpg>
        <BoxSpg>
          <CircularLoader />
        </BoxSpg>
      </BoxSpg>
    );
  }
  if (error) {
    return (
      <BoxSpg>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </BoxSpg>
    );
  }
  return (
    <>
      <AddApplicationsDialog setApplicationIds={setApplicationIds}></AddApplicationsDialog>
      <DeleteApplicationDialog
        applicationIds={applicationIds}
        setApplicationIds={setApplicationIds}
      ></DeleteApplicationDialog>
      <BoxSpg>
        <BoxSpg my={5} textAlign={"center"}>
          {t("t.applications")}
        </BoxSpg>
        <BoxSpg>
          {isArrayNotEmpty(applicationIds) && (
            <ApplicationsListTable deleteFunc={onDeleteApplicationClick} applicationIds={applicationIds} />
          )}
          {isArrayEmpty(applicationIds) && <AlertSpg severity="info">{t("m.empty.list")}</AlertSpg>}
          <GridContainer spacing={3}>
            <GridItem sm={12} md={12}>
              <Typography>
                <Button
                  onClick={() => {
                    handleOpenAddApplicationDialog(applicationIds);
                  }}
                  startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faAdd} />}
                  variant={"contained"}
                >
                  {t("l.btn.add")}
                </Button>
              </Typography>
            </GridItem>
          </GridContainer>
          <>
            <DividerSpg my={4} />
            <GridContainer spacing={3}>
              <GridItem sm={12} md={12}>
                <Typography align={"right"}>
                  <Button onClick={onSaveApplications} variant={"contained"}>
                    {t("l.btn.save")}
                  </Button>
                </Typography>
              </GridItem>
            </GridContainer>
          </>
        </BoxSpg>
      </BoxSpg>
    </>
  );
};

export default Applications;
