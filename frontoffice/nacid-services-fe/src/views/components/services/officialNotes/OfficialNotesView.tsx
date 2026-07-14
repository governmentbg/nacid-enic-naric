import { useSearchParams } from "react-router-dom";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  useViewDataControl,
  ViewContentWrapper,
  ViewOfficialNotesApp,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import { buildFetchFileUrl } from "../../../../services/coreServicesCalls";
import React from "react";
import { createAppViewUrlWithServices } from "../../../../utils/applicationUrlUtils";
import ApplicationCorrespondenceList from "../common/correspondence/ApplicationCorrespondenceList";

const OfficialNotesView = ({ serviceFn }: { serviceFn: () => Promise<any> }) => {
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");
  const dossierNumber = searchParams.get("dossierNumber");

  useViewDataControl({
    viewType: ApplicationSubtype.OFFICIAL_NOTE,
    viewId: id ? id : dossierNumber,
    serviceFn: serviceFn,
  });

  return (
    <AppPageContentWrapper>
      <Box sx={{ width: "100%" }}>
        <ViewContentWrapper viewType={ApplicationSubtype.OFFICIAL_NOTE} viewId={id ? id : dossierNumber}>
          <ViewOfficialNotesApp
            buildFetchFileUrlFn={buildFetchFileUrl}
            buildViewApplicationUrlFn={createAppViewUrlWithServices}
            additionalAppInfoComponent={<ApplicationCorrespondenceList applicationId={id} />}
          />
        </ViewContentWrapper>
      </Box>
    </AppPageContentWrapper>
  );
};
export default OfficialNotesView;
