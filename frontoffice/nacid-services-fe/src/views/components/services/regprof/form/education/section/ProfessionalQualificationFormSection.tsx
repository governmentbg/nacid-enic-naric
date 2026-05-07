import { CheckboxFormField, GridContainer, GridItem, FormSection } from "@duosoftbg/nacid-components";
import React from "react";
import { Typography } from "@mui/material";
import { useTranslation } from "react-i18next";
import ProfQualificationRequestedAutocompleteFormField from "../parts/ProfQualificationRequestedAutocompleteFormField";

const ProfessionalQualificationFormSection = () => {
  const { t } = useTranslation();

  return (
    <FormSection label={"t.regprof.education.professionalQualification"}>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <CheckboxFormField
            required={true}
            label={
              <Typography component={"span"}>
                <Typography component={"span"}>{t("l.regprof.education.nonRevokedRightToPractice.input")}</Typography>
                <Typography component={"span"} ml={4}>
                  <a href={"https://nacid.bg/att_files/adm_usl/1601_4_deklaraciya.doc"}>{t("l.declaration")}</a>
                </Typography>
              </Typography>
            }
            fieldName={"nonRevokedRightToPractice"}
          />
        </GridItem>
        <GridItem sm={12} md={12}>
          <ProfQualificationRequestedAutocompleteFormField />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default ProfessionalQualificationFormSection;
