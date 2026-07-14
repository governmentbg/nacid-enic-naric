import { Typography } from "@mui/material";
import { useTranslation } from "react-i18next";
import { Close, Done } from "@mui/icons-material";
import React from "react";
import { BoxSpg, GridContainer, GridItem, FormSection } from "@duosoftbg/nacid-components";

const EvaluationsFormSection = ({ evaluations }) => {
  const { t } = useTranslation();

  if (evaluations === null || evaluations.length === 0) {
    return null;
  }
  return (
    <FormSection label={"t.evaluation.details"}>
      <GridContainer>
        <GridItem sm={12} md={12}>
          {evaluations.map((evaluation) => (
            <BoxSpg key={evaluation.evaluationCode}>
              <Typography
                component={"span"}
                position={"relative"}
                top={5}
                color={evaluation.evaluationValue ? "green" : "red"}
              >
                {evaluation.evaluationValue ? <Done /> : <Close />}
              </Typography>
              <Typography
                color={evaluation.evaluationValue ? "green" : "red"}
                key={evaluation.evaluationCode}
                component={"span"}
                fontSize={16}
                ml={4}
              >
                {t(evaluation.evaluationCode)}
              </Typography>
              {evaluation.templateUrl && (
                <Typography key={evaluation.templateUrl} component={"span"} fontSize={16} ml={4}>
                  <a href={evaluation.templateUrl}>{t("l.download.template")}</a>
                </Typography>
              )}
            </BoxSpg>
          ))}
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};

export default EvaluationsFormSection;
