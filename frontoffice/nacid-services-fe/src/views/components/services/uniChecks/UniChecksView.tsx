import { useSearchParams } from "react-router-dom";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  useViewDataControl,
  ViewUniChecksApp,
  ViewContentWrapper,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import React from "react";
import { buildFetchFileUrl } from "../../../../services/coreServicesCalls";
import ApplicationCorrespondenceList from "../common/correspondence/ApplicationCorrespondenceList";

const UniChecksView = ({ serviceFn }: { serviceFn: () => Promise<any> }) => {
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");
  const dossierNumber = searchParams.get("dossierNumber");

  useViewDataControl({
    viewType: ApplicationSubtype.UNI_CHECKS,
    viewId: id ? id : dossierNumber,
    serviceFn: serviceFn,
  });

  return (
    <AppPageContentWrapper>
      <Box sx={{ width: "100%" }}>
        <ViewContentWrapper viewType={ApplicationSubtype.UNI_CHECKS} viewId={id ? id : dossierNumber}>
          <ViewUniChecksApp
            buildFetchFileUrlFn={buildFetchFileUrl}
            additionalAppInfoComponent={<ApplicationCorrespondenceList applicationId={id} />}
          />
        </ViewContentWrapper>
      </Box>
    </AppPageContentWrapper>
  );
};
export default UniChecksView;
