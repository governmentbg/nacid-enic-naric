import React from "react";
import { Typography } from "@mui/material";
import { useTranslation } from "react-i18next";
import { CheckboxFormField, GridItem, GridContainer, FormSection } from "@duosoftbg/nacid-components";

const DeclarationsFormSection = ({
  agreeDataUsageLabelCode = "l.declaration.agreeDataUsage.input",
  documentDeclarationLabelCode = "l.declaration.documentsDeclaration",
  showAgreeDeclarationLink = true,
}) => {
  const { t } = useTranslation();

  return (
    <FormSection label={"t.declarations.details"}>
      <GridContainer>
        <GridItem xs={12} sm={12} md={12}>
          <CheckboxFormField
            label={
              <Typography component={"span"}>
                <Typography component={"span"}>{t(agreeDataUsageLabelCode)}</Typography>
                {showAgreeDeclarationLink && (
                  <Typography component={"span"} ml={4}>
                    <a href={"https://portal.nacid.bg/documents/DataUsageDeclaration.doc"}>{t("l.declaration")}</a>
                  </Typography>
                )}
              </Typography>
            }
            fieldName={"agreeDataUsage"}
          />
        </GridItem>
        <GridItem xs={12} sm={12} md={12}>
          <CheckboxFormField labelCode={documentDeclarationLabelCode} fieldName={"documentsDeclaration"} />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default DeclarationsFormSection;
