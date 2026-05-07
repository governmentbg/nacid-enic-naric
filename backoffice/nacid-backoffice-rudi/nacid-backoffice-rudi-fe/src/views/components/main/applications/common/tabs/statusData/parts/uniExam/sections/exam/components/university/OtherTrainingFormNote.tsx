import { useWatch } from "react-hook-form";
import { TrainingForm } from "@duosoftbg/nacid-backoffice-components";
import { GridItem, InputFormField } from "@duosoftbg/nacid-components";
import React from "react";

const OtherTrainingFormNote = () => {
  const trainingForms = useWatch({ name: `trainingForms` });

  const otherTrainingForm = trainingForms.find((element) => element === TrainingForm.OTHER);

  if (otherTrainingForm) {
    return (
      <GridItem sm={12} md={12}>
        <InputFormField fieldName={"otherTrainingFormNote"} labelCode={"l.notes"} />
      </GridItem>
    );
  }

  return null;
};
export default OtherTrainingFormNote;
