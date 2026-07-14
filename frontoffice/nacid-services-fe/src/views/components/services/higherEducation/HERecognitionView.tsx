import { useSearchParams } from "react-router-dom";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  useViewDataControl,
  ViewHeRecognitionApp,
  ViewContentWrapper,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import React from "react";
import { buildFetchFileUrl } from "../../../../services/coreServicesCalls";
import ApplicationCorrespondenceList from "../common/correspondence/ApplicationCorrespondenceList";
import ApplicationCertificateList from "../certificate/ApplicationCertificateList";

const HERecognitionView = ({ serviceFn }: { serviceFn: () => Promise<any> }) => {
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");
  const dossierNumber = searchParams.get("dossierNumber");
  const accessCode = searchParams.get("accessCode");

  useViewDataControl({
    viewType: ApplicationSubtype.HE_RECOGNITION,
    viewId: id ? id : dossierNumber,
    serviceFn: serviceFn,
  });

  return (
    <AppPageContentWrapper>
      <Box sx={{ width: "100%" }}>
        <ViewContentWrapper viewType={ApplicationSubtype.HE_RECOGNITION} viewId={id ? id : dossierNumber}>
          <ViewHeRecognitionApp
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

export default HERecognitionView;
