import { useSearchParams } from "react-router-dom";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  useViewDataControl,
  ViewContentWrapper,
  ViewSuggestionApp,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import { buildFetchFileUrl } from "../../../../services/coreServicesCalls";
import React from "react";

const SuggestionView = ({ serviceFn }: { serviceFn: () => Promise<any> }) => {
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");
  const dossierNumber = searchParams.get("dossierNumber");

  useViewDataControl({
    viewType: ApplicationSubtype.SUGGESTION,
    viewId: id ? id : dossierNumber,
    serviceFn: serviceFn,
  });

  return (
    <AppPageContentWrapper>
      <Box sx={{ width: "100%" }}>
        <ViewContentWrapper viewType={ApplicationSubtype.SUGGESTION} viewId={id ? id : dossierNumber}>
          <ViewSuggestionApp buildFetchFileUrlFn={buildFetchFileUrl} />
        </ViewContentWrapper>
      </Box>
    </AppPageContentWrapper>
  );
};
export default SuggestionView;
