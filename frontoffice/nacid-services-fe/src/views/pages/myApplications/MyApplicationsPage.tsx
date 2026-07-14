import { useTranslation } from "react-i18next";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { CardSpg, BoxSpg, BorderGreyBox, useAsyncCall, AsyncCallArgs } from "@duosoftbg/nacid-components";
import { CardContent } from "@mui/material";
import MyApplicationsFilterForm from "../../components/services/myApplications/MyApplicationsFilterForm";
import MyApplicationsList from "../../components/services/myApplications/MyApplicationsList";
import { useEffect, useState } from "react";
import { getMyApplicationsStatuses } from "../../../services/myApplicationsCalls";

const MyApplicationsPage = () => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [statuses, setStatuses] = useState([]);

  useEffect(() => {
    const getMyStatusesArgs: AsyncCallArgs = {
      promise: getMyApplicationsStatuses(),
      processResponseErrors: false,
      withGlobalBackdrop: false,
      onSuccess: (response) => {
        setStatuses(response.data);
      },
      onError: () => setStatuses([]),
    };
    asyncCall(getMyStatusesArgs);
  }, [asyncCall]);

  return (
    <PageWrapper title={t("t.page.my.applications")}>
      <CardSpg my={4} style={{ overflow: "visible" }}>
        <CardContent style={{ position: "relative" }}>
          <BoxSpg>
            <BorderGreyBox>
              <MyApplicationsFilterForm statuses={statuses} />
            </BorderGreyBox>
            <MyApplicationsList />
          </BoxSpg>
        </CardContent>
      </CardSpg>
    </PageWrapper>
  );
};
export default MyApplicationsPage;
