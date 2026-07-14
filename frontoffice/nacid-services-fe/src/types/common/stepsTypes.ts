import React from "react";

export interface Step {
  labelCode: string;
  completed: boolean;
  component: React.ReactNode;
  isEdited: boolean;
}

export interface StepperApplication {
  steps: Step[];
}
