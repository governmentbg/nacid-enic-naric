import { useTranslation } from "react-i18next";
import React from "react";
import { AccordionItemBox, AccordionSummaryStld } from "@duosoftbg/nacid-components";
import { Accordion, Typography } from "@mui/material";
import { ExpandMore } from "@mui/icons-material";
import ViewDiplomaTypeSection from "./diplomaType/ViewDiplomaTypeSection";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import ViewBaseUniversitySection from "./university/ViewBaseUniversitySection";
import ViewSchoolSection from "./school/ViewSchoolSection";
import ViewRecognitionPurposeSection from "./recognitionPurpose/ViewRecognitionPurposeSection";
import ViewPreviousDiplomaSection from "./previousDiploma/ViewPreviousDiplomaSection";
import ViewDissertationSection from "./dissertation/ViewDissertationSection";
import ViewEducationSection from "./education/ViewEducationSection";
import ViewJurySection from "./dissertation/ViewJurySection";

type ViewEducationDataProps = {
  appType: AppType;
};

const ViewEducationData = ({ appType }: ViewEducationDataProps) => {
  const { t } = useTranslation();

  return (
    <AccordionItemBox mt={1}>
      <Accordion defaultExpanded={false}>
        <AccordionSummaryStld expandIcon={<ExpandMore />}>
          <Typography variant={"h4"}>{t("t.educationData")}</Typography>
        </AccordionSummaryStld>
        <ViewBaseUniversitySection appType={appType} />
        <ViewDiplomaTypeSection appType={appType} />
        <ViewEducationSection appType={appType} />
        {appType === AppType.DOCREC_APPLICATION && <ViewDissertationSection appType={appType} />}
        {appType === AppType.DOCREC_APPLICATION && <ViewJurySection />}
        <ViewSchoolSection appType={appType} />
        <ViewPreviousDiplomaSection appType={appType} />
        <ViewRecognitionPurposeSection appType={appType} />
        {/*<ViewNotesSection appType={appType} />*/}
      </Accordion>
    </AccordionItemBox>
  );
};
export default ViewEducationData;
