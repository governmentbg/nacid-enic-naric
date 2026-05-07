import { BoxSpg, FormSection, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import { AppSectionTitle } from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import { useTranslation } from "react-i18next";
import { Typography } from "@mui/material";
import { Circle } from "@mui/icons-material";

const ConstraintVisualizer = ({ constraintTabs }) => {
  const { t } = useTranslation();

  return (
    <GridContainer mt={0}>
      <GridItem sm={12} md={12}>
        {constraintTabs &&
          constraintTabs.map((tab) => {
            return (
              <BoxSpg ml={3} mt={5} key={tab.name}>
                <Typography variant={"h5"} color={"primary"}>{`${t("l.tab")} "${t(tab.name)}":`}</Typography>
                <BoxSpg ml={2}>
                  {tab.constraintList &&
                    tab.constraintList.map((constraint) => {
                      return (
                        <BoxSpg mt={2} key={constraint.message}>
                          <Circle style={{ fontSize: 8, marginRight: 10 }} color={"primary"} />
                          <BoxSpg
                            component={"span"}
                            style={{ fontSize: 16 }}
                            color={constraint.accomplished ? "green" : "red"}
                          >
                            {t(constraint.message)}
                          </BoxSpg>
                        </BoxSpg>
                      );
                    })}
                </BoxSpg>
              </BoxSpg>
            );
          })}
      </GridItem>
    </GridContainer>
  );
};

const StatusInitialExamination = ({ constraint }) => {
  return (
    <BoxSpg>
      <AppSectionTitle title={"t.appSubSections.statusInitialExamination"} />
      <FormSection label={"t.appStatus.constraints"}>
        <ConstraintVisualizer constraintTabs={constraint.tabs} />
      </FormSection>
    </BoxSpg>
  );
};
export default StatusInitialExamination;
