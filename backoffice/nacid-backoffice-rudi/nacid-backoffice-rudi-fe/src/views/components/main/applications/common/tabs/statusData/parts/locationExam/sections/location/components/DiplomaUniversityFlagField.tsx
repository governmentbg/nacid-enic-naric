import { i18nKeyByCode } from "@duosoftbg/nacid-backoffice-components";
import { CheckboxFormField, GridItem } from "@duosoftbg/nacid-components";
import React from "react";

const DiplomaUniversityFlagField = ({ baseField, appType }) => {
  return (
    <GridItem sm={12} md={12}>
      <CheckboxFormField
        fieldName={`${baseField}.isNotUniInstitution`}
        labelCode={i18nKeyByCode(appType, "l.trainingLocationExam.isNotUniInstitution")}
      />
    </GridItem>
  );
};
export default DiplomaUniversityFlagField;
