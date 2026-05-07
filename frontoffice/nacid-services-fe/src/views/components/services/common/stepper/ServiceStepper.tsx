import useSteps from "../../../../../hooks/useSteps";
import { Box, Button, Step, StepButton, Stepper } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { ChevronLeft, ChevronRight } from "@mui/icons-material";
import StepEditedDialog from "./StepEditedDialog";

const ServiceStepper = ({ serviceSteps, applicationSubtype }) => {
  const { t } = useTranslation();
  const [open, setOpen] = useState(false);

  useEffect(() => {}, [serviceSteps]);

  const { steps, activeStep, activeStepComponent, handleStep, handleBack, handleNext, isCompletedStep } =
    useSteps(serviceSteps);

  const isFormEdited = () => {
    if (steps[activeStep].isEdited) {
      setOpen(true);
      return true;
    }
    return false;
  };

  const handleStepBack = () => {
    if (!isFormEdited()) {
      handleBack();
    }
  };

  const handleStepNext = () => {
    if (!isFormEdited()) {
      handleNext();
    }
  };

  const handleStepClick = (index) => {
    if (!isFormEdited()) {
      handleStep(index);
    }
  };

  return (
    <React.Fragment>
      <Stepper nonLinear activeStep={activeStep}>
        {steps.map((step, index) => (
          <Step key={step.labelCode} completed={step.completed} sx={{ fontSize: "18px" }}>
            <StepButton color="inherit" onClick={() => handleStepClick(index)} disabled={!isCompletedStep(0)}>
              {t(step.labelCode)}
            </StepButton>
          </Step>
        ))}
      </Stepper>

      <Box sx={{ width: "100%" }}>{activeStepComponent}</Box>
      <Box
        style={{ position: "absolute", right: 0, marginRight: 30, marginTop: -45 }}
        sx={{ display: "flex", flexDirection: "row", pt: 2 }}
      >
        {activeStep !== 0 ? (
          <Button
            startIcon={<ChevronLeft />}
            color="inherit"
            disabled={activeStep === 0}
            onClick={handleStepBack}
            sx={{ mr: 1 }}
          >
            {t("l.btn.previous")}
          </Button>
        ) : null}
        <Box sx={{ flex: "1 1 auto" }} />
        {activeStep !== steps.length - 1 ? (
          <Button endIcon={<ChevronRight />} onClick={handleStepNext} sx={{ mr: 1 }} disabled={!isCompletedStep(0)}>
            {t("l.btn.next")}
          </Button>
        ) : null}
      </Box>
      <StepEditedDialog applicationSubtype={applicationSubtype} open={open} onCloseDialog={() => setOpen(false)} />
    </React.Fragment>
  );
};

export default ServiceStepper;
