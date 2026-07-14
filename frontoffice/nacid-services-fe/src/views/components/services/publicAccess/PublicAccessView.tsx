import { useSearchParams } from "react-router-dom";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  useViewDataControl,
  ViewContentWrapper,
  ViewPublicAccessApp,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import React from "react";
import { buildFetchFileUrl } from "../../../../services/coreServicesCalls";

const PublicAccessView = ({ serviceFn }: { serviceFn: () => Promise<any> }) => {
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");
  const dossierNumber = searchParams.get("dossierNumber");

  useViewDataControl({
    viewType: ApplicationSubtype.PUBLIC_ACCESS,
    viewId: id ? id : dossierNumber,
    serviceFn: serviceFn,
  });

  return (
    <AppPageContentWrapper>
      <Box sx={{ width: "100%" }}>
        <ViewContentWrapper viewType={ApplicationSubtype.PUBLIC_ACCESS} viewId={id ? id : dossierNumber}>
          <ViewPublicAccessApp buildFetchFileUrlFn={buildFetchFileUrl} />
        </ViewContentWrapper>
      </Box>
    </AppPageContentWrapper>
  );
};
export default PublicAccessView;
