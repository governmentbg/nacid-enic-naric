import { Typography } from "@mui/material";
import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import { BoxSpg, TextButton, GridContainer, GridItem, AlertSpg, parseDate } from "@duosoftbg/nacid-components";
import { Calculate } from "@mui/icons-material";
import { useWatch } from "react-hook-form";
import { createExperienceDocumentsOnlyPeriodsValidationSchema } from "../../../../../../../yup/regprof/regprofEducationDetailsValidationSchema";

const ExperienceCalculator = () => {
  const { t } = useTranslation();
  const [errors, setErrors] = useState(false);
  const [calculation, setCalculation] = useState(null);
  const daysInYear = 365.25;
  const daysInMonth = 30.4375;
  const millisInADay = 1000 * 3600 * 24;
  const documents = useWatch({ name: "experience.experienceDocuments" });
  const hasExperience = useWatch({ name: "experienceSelected" });

  const areWorkPeriodsValid = async () => {
    const validationResult = await createExperienceDocumentsOnlyPeriodsValidationSchema()
      .validate(documents)
      .then(() => {
        return true;
      })
      .catch(() => {
        return false;
      });
    return validationResult;
  };

  const calculateExperience = async () => {
    if (await areWorkPeriodsValid()) {
      setErrors(false);
      let totalDays = 0;
      let totalMonths;
      let totalYears;

      documents.forEach((doc) => {
        doc.workPeriods.forEach((wp) => {
          const ratio = parseInt(wp.workDayHours.id) / 8;
          const dateFrom = parseDate(wp.fromDate);
          const dateTo = parseDate(wp.toDate);

          let currentDays = (dateTo.getTime() - dateFrom.getTime()) / millisInADay;
          currentDays = Math.round(currentDays * ratio);
          totalDays += currentDays;
        });
      });

      totalYears = Math.trunc(totalDays / daysInYear);
      totalDays = totalDays % daysInYear;
      totalMonths = Math.trunc(totalDays / daysInMonth);
      totalDays = Math.round(totalDays % daysInMonth);

      setCalculation({ days: totalDays, months: totalMonths, years: totalYears });
    } else {
      setErrors(true);
      setCalculation(null);
    }
  };

  if (!hasExperience || !documents || documents.length < 1) {
    return null;
  }
  return (
    <BoxSpg>
      <GridContainer mt={0}>
        <GridItem sm={4} md={3}>
          <Typography mr={4} mt={2}>
            <TextButton
              size={"small"}
              disableRipple
              startIcon={<Calculate />}
              color="primary"
              onClick={calculateExperience}
            >
              {t("l.btn.calculate.experience")}
            </TextButton>
          </Typography>
        </GridItem>
        <GridItem sm={4} md={6}>
          {errors && <AlertSpg severity={"error"}>{t("m.fix.working.periods")}</AlertSpg>}
          {calculation && (
            <AlertSpg severity={"success"}>
              <Typography textAlign={"center"} alignItems={"center"} align={"center"}>
                {`${calculation.years} ${t("l.years")}, ${calculation.months} ${t("l.months")}, ${calculation.days} ${t(
                  "l.days"
                )}`}
              </Typography>
            </AlertSpg>
          )}
        </GridItem>
      </GridContainer>
    </BoxSpg>
  );
};
export default ExperienceCalculator;
