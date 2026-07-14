import { Step } from "../types/common/stepsTypes";
import React from "react";

const useSteps = (steps: Step[]) => {
  const [activeStep, setActiveStep] = React.useState(0);

  const handleNext = () => {
    const newActiveStep = activeStep + 1;
    setActiveStep(newActiveStep);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleStep = (step) => {
    setActiveStep(step);
  };

  const isCompletedStep = (step) => {
    return steps[step].completed;
  };

  const activeStepComponent = steps[activeStep].component;

  return {
    steps,
    activeStep,
    activeStepComponent,
    handleNext,
    handleBack,
    handleStep,
    isCompletedStep,
  };
};

export default useSteps;
