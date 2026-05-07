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
import React, { useEffect, useState } from "react";
import { getSecretary, saveCalendarMembers } from "../../../../../../../axios/api/services";
import { Button, Typography } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAdd, faFileDownload } from "@fortawesome/free-solid-svg-icons";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import MembersListTable from "./MembersListTable";
import AddMemberDialog from "./dialog/AddMemberDialog";
import DeleteMemberDialog from "./dialog/DeleteMemberDialog";
import { openAddMemberDialog } from "../../../../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";
import { toast } from "react-toastify";
import EditMemberAdditionalDataDialog from "./dialog/EditMemberAdditionalDataDialog";
import useMembersControl from "../../../hooks/useMembersControl";
import ViewMemberDialog from "../../dialog/ViewMemberDialog";
import SecretarySection from "./sections/SecretarySection";
import { CalendarTemplates } from "../../../../../../../utils/constants";
import { ReportType } from "@duosoftbg/nacid-backoffice-components";
import useCalendarGenerateFile from "../../../hooks/useCalendarGenerateFile";

const Members = () => {
  const calendarId = useParams().calendarId;
  const { asyncCall } = useAsyncCall();
  const { members, setMembers, error, loading } = useMembersControl({
    calendarId: calendarId,
  });
  const { generateFile } = useCalendarGenerateFile({
    calendarId: calendarId,
  });
  const [secretary, setSecretary] = useState("");
  const [errors, setErrors] = useState(null);
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getSecretary(calendarId),
      processResponseErrors: false,
      onSuccess: (response) => {
        setSecretary(response);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [calendarId]);

  const handleOpenAddMemberDialog = () => {
    const excludedMembers = members.map((obj) => obj.member.id);
    const payload = { excludedMembers };
    dispatch(openAddMemberDialog(payload));
  };

  const saveMemberAdditionalData = (memberAddDataChanges) => {
    const membersAfterEditAdditional = [...members];
    const memberIndex = membersAfterEditAdditional.findIndex(
      (memberObj) => memberObj.member.id === memberAddDataChanges.member.id,
    );
    membersAfterEditAdditional[memberIndex] = memberAddDataChanges;
    setMembers(membersAfterEditAdditional);
    dispatch(FormDirtyStateControlActions.setDirty({ dirty: true }));
  };

  const changeSecretary = (responsibleUser) => {
    setSecretary(responsibleUser);
    dispatch(FormDirtyStateControlActions.setDirty({ dirty: true }));
  };

  const setChairman = (memberId, checked) => {
    const membersCopy = [...members];
    membersCopy.forEach((memberObj) => {
      if (memberObj.member.id === memberId) {
        memberObj.chairman = checked;
      } else {
        memberObj.chairman = false;
      }
    });
    setMembers(membersCopy);
    dispatch(FormDirtyStateControlActions.setDirty({ dirty: true }));
  };

  const onSaveMembers = () => {
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: saveCalendarMembers(calendarId, members, secretary),
      onSuccess: (response) => {
        toast.success(t("m.create.success"));
        setErrors(null);
        dispatch(FormDirtyStateControlActions.setDirty({ dirty: false }));
      },
      onError: (error) => {
        setErrors(error.errors);
      },
    };
    asyncCall(asyncCreation);
  };

  if (loading) {
    return (
      <BoxSpg>
        <BoxSpg my={5} textAlign={"center"}>
          {t("t.commission.members")}
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
      <ViewMemberDialog></ViewMemberDialog>
      <AddMemberDialog setMembers={setMembers}></AddMemberDialog>
      <DeleteMemberDialog setMembers={setMembers} members={members}></DeleteMemberDialog>
      <EditMemberAdditionalDataDialog
        saveMemberAdditionalData={saveMemberAdditionalData}
      ></EditMemberAdditionalDataDialog>
      <BoxSpg>
        <BoxSpg my={5} textAlign={"center"}>
          {t("t.commission.members")}
        </BoxSpg>
        <BoxSpg>
          {isArrayNotEmpty(members) && (
            <Button
              style={{ marginBottom: "20px" }}
              startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faFileDownload} />}
              size={"medium"}
              type={"button"}
              variant="contained"
              color="primary"
              onClick={() => {
                generateFile(
                  CalendarTemplates.MEMBERS_TEMPLATE,
                  ReportType.DOCX.key,
                  "commission_participation_list.docx",
                );
              }}
            >
              {t("l.btn.generate.calendar.members.file")}
            </Button>
          )}
          {isArrayNotEmpty(errors) && <ErrorMessages errors={errors} />}
          {isArrayNotEmpty(members) && <MembersListTable members={members} setChairman={setChairman} />}
          {isArrayEmpty(members) && <AlertSpg severity="info">{t("m.empty.list")}</AlertSpg>}
          <GridContainer spacing={3}>
            <GridItem sm={12} md={12}>
              <Typography>
                <Button
                  onClick={handleOpenAddMemberDialog}
                  startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faAdd} />}
                  variant={"contained"}
                >
                  {t("l.btn.add")}
                </Button>
              </Typography>
            </GridItem>
          </GridContainer>

          <GridContainer spacing={3}>
            <GridItem sm={12} md={12}>
              <SecretarySection secretary={secretary} changeSecretary={changeSecretary}></SecretarySection>
            </GridItem>
          </GridContainer>

          <>
            <DividerSpg my={4} />
            <GridContainer spacing={3}>
              <GridItem sm={12} md={12}>
                <Typography align={"right"}>
                  <Button onClick={onSaveMembers} variant={"contained"}>
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

const ErrorMessages = ({ errors }) => {
  const { t } = useTranslation();

  return (
    <>
      {errors.map((error) => (
        <AlertSpg key={error.pointer} style={{ marginBottom: "10px" }} severity="error">
          {t(error.message)}
        </AlertSpg>
      ))}
    </>
  );
};

export default Members;
