import {
  AlertSpg,
  AsyncCallArgs,
  CircularLoader,
  GridContainer,
  isArrayEmpty,
  isArrayNotEmpty,
  isNotEmpty,
  LabeledDataItem,
  useAsyncCall,
  ViewSection,
} from "@duosoftbg/nacid-components";
import * as React from "react";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import useMembersControl from "../../hooks/useMembersControl";
import MembersListTable from "../tabs/members/MembersListTable";
import ViewMemberDialog from "../dialog/ViewMemberDialog";
import { useEffect, useState } from "react";
import { getSecretary } from "../../../../../../axios/api/services";
import { CoreApiServicesBase } from "@duosoftbg/nacid-backoffice-components";
import styled from "styled-components";

const SecretaryWrapper = styled.div`
  padding-left: 4px;
  margin-top: 10px;
`;

const MembersViewSection = () => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const calendarId = useParams().calendarId;
  const { members, error, loading } = useMembersControl({
    calendarId: calendarId,
  });
  const [secretaryFullName, setSecretaryFullName] = useState(null);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getSecretary(calendarId),
      processResponseErrors: false,
      onSuccess: (response) => {
        if (isNotEmpty(response)) {
          fillSecretaryFullName(response);
        }
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [calendarId]);

  const fillSecretaryFullName = (userName) => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: CoreApiServicesBase.getResponsibleUserFormattedName(userName),
      processResponseErrors: false,
      onSuccess: (response) => {
        setSecretaryFullName(response);
      },
    };
    asyncCall(asyncCallArgs);
  };

  if (loading) {
    return (
      <ViewSection label={"t.commission.members"}>
        <div style={{ marginTop: "10px" }}>
          <CircularLoader></CircularLoader>
        </div>
      </ViewSection>
    );
  }
  if (error) {
    return (
      <ViewSection label={"t.commission.members"}>
        <GridContainer spacing={3} mt={2}>
          <AlertSpg style={{ width: "100%" }} severity="error">
            {t("m.error.serverFetchingError")}
          </AlertSpg>
        </GridContainer>
      </ViewSection>
    );
  }
  return (
    <>
      <ViewMemberDialog></ViewMemberDialog>
      <ViewSection label={"t.commission.members"}>
        <GridContainer spacing={3} mt={2}>
          {isArrayNotEmpty(members) && <MembersListTable members={members} isViewMode={true}></MembersListTable>}
          {isArrayEmpty(members) && (
            <AlertSpg style={{ width: "100%" }} severity="info">
              {t("m.empty.list")}
            </AlertSpg>
          )}
        </GridContainer>
      </ViewSection>
      <SecretaryWrapper>
        {secretaryFullName && <LabeledDataItem labelCode={"l.secretary.dots"} data={secretaryFullName} />}
      </SecretaryWrapper>
    </>
  );
};

export default MembersViewSection;
