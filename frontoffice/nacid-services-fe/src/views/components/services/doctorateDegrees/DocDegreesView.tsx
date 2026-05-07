import {
  ApplicationSubtype,
  AppPageContentWrapper,
  useViewDataControl,
  ViewDocDegreesApp,
  ViewContentWrapper,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import { useSearchParams } from "react-router-dom";
import React from "react";
import { buildFetchFileUrl } from "../../../../services/coreServicesCalls";
import ApplicationCorrespondenceList from "../common/correspondence/ApplicationCorrespondenceList";
import ApplicationCertificateList from "../certificate/ApplicationCertificateList";

const DocDegreesView = ({ serviceFn }: { serviceFn: () => Promise<any> }) => {
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");
  const dossierNumber = searchParams.get("dossierNumber");
  const accessCode = searchParams.get("accessCode");

  useViewDataControl({
    viewType: ApplicationSubtype.DOC_DEGREES,
    viewId: id ? id : dossierNumber,
    serviceFn: serviceFn,
  });

  return (
    <AppPageContentWrapper>
      <Box sx={{ width: "100%" }}>
        <ViewContentWrapper viewType={ApplicationSubtype.DOC_DEGREES} viewId={id ? id : dossierNumber}>
          <ViewDocDegreesApp
            buildFetchFileUrlFn={buildFetchFileUrl}
            additionalAppInfoComponent={
              <>
                <ApplicationCertificateList applicationId={id} dossierNumber={dossierNumber} accessCode={accessCode} />
                <ApplicationCorrespondenceList applicationId={id} />
              </>
            }
          />
        </ViewContentWrapper>
      </Box>
    </AppPageContentWrapper>
  );
};

export default DocDegreesView;
