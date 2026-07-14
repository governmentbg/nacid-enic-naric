import {
  GridContainer,
  GridItem,
  CheckboxFormField,
  FormSection,
  ApplicationType,
  ApplicationSubtype,
} from "@duosoftbg/nacid-components";
import React from "react";
import { useTranslation } from "react-i18next";
import { FormLabel } from "@mui/material";
import ServiceTypeFormFields from "../../../../common/form/education/parts/ServiceTypeFormFields";

const ApplicationDetailsFormSection = () => {
  const { t } = useTranslation();

  return (
    <FormSection label={"t.uniChecks.application.details"}>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <FormLabel required={true}>{t("l.serviceKind")}</FormLabel>
        </GridItem>
        <GridItem sm={4} md={3}>
          <CheckboxFormField fieldName={"statute"} labelCode={"l.statute"} />
        </GridItem>
        <GridItem sm={4} md={3}>
          <CheckboxFormField fieldName={"authenticity"} labelCode={"l.authenticity"} />
        </GridItem>
        <GridItem sm={4} md={3}>
          <CheckboxFormField fieldName={"recommendation"} labelCode={"l.recommendation"} />
        </GridItem>
      </GridContainer>
      <ServiceTypeFormFields
        applicationType={ApplicationType.ACADEMIC_RECOGNITION}
        applicationSubtype={ApplicationSubtype.UNI_CHECKS}
      />
    </FormSection>
  );
};
export default ApplicationDetailsFormSection;
