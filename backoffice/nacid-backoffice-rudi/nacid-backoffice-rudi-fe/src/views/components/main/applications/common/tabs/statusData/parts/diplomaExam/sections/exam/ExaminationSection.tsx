import { i18nKeyByCode } from "@duosoftbg/nacid-backoffice-components";
import {
  CheckboxFormField,
  DateFormField,
  FormSection,
  GridContainer,
  GridItem,
  TextareaFormField,
} from "@duosoftbg/nacid-components";
import React from "react";

const ExaminationSection = ({ appType }) => {
  return (
    <FormSection label={"l.examination"}>
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={6} md={4}>
          <DateFormField fieldName={"examinationDate"} labelCode={"l.examinationDate"} required={true} />
        </GridItem>
        <GridItem sm={12} md={12}>
          <CheckboxFormField
            fieldName={"isInstitutionCommunicated"}
            labelCode={"l.diplomaExam.isInstitutionCommunicated"}
          />
        </GridItem>
        <GridItem sm={12} md={12}>
          <CheckboxFormField
            fieldName={"isUniversityCommunicated"}
            labelCode={i18nKeyByCode(appType, "l.diplomaExam.isUniversityCommunicated")}
          />
        </GridItem>
        <GridItem sm={12} md={12}>
          <CheckboxFormField fieldName={"isStateApproved"} labelCode={"l.diplomaExam.isStateApproved"} />
        </GridItem>
        <GridItem sm={12} md={12}>
          <CheckboxFormField fieldName={"isFoundInRegister"} labelCode={"l.diplomaExam.isFoundInRegister"} />
        </GridItem>
        <GridItem sm={12} md={12}>
          <CheckboxFormField fieldName={"isAuthentic"} labelCode={"l.diplomaExam.isAuthentic"} />
        </GridItem>
        <GridItem sm={12} md={12}>
          <TextareaFormField fieldName={"notes"} labelCode={"l.notes"} />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default ExaminationSection;
