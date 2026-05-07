import { useSearchParams } from "react-router-dom";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  useViewDataControl,
  ViewContentWrapper,
  ViewDocDeliveryApp,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import { buildFetchFileUrl } from "../../../../services/coreServicesCalls";
import React from "react";
import ApplicationCorrespondenceList from "../common/correspondence/ApplicationCorrespondenceList";

const DocDeliveryView = ({ serviceFn }: { serviceFn: () => Promise<any> }) => {
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");
  const dossierNumber = searchParams.get("dossierNumber");

  useViewDataControl({
    viewType: ApplicationSubtype.DOCUMENT_SERVICE,
    viewId: id ? id : dossierNumber,
    serviceFn: serviceFn,
  });

  return (
    <AppPageContentWrapper>
      <Box sx={{ width: "100%" }}>
        <ViewContentWrapper viewType={ApplicationSubtype.DOCUMENT_SERVICE} viewId={id ? id : dossierNumber}>
          <ViewDocDeliveryApp
            buildFetchFileUrlFn={buildFetchFileUrl}
            additionalAppInfoComponent={<ApplicationCorrespondenceList applicationId={id} />}
          />
        </ViewContentWrapper>
      </Box>
    </AppPageContentWrapper>
  );
};
export default DocDeliveryView;
