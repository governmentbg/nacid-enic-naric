import QualificationAutocompleteFormField from "./QualificationAutocompleteFormField";
import { GridItem, GridContainer } from "@duosoftbg/nacid-components";
import React from "react";
import {
  getOriginalQualificationsAutocomplete,
  getQualificationsAutocomplete,
} from "../../../../../../../services/autocompleteCalls";

const QualificationFormFields = () => {
  return (
    <GridContainer spacing={4} mt={0}>
      <GridItem xs={12} sm={6} md={6}>
        <QualificationAutocompleteFormField
          fieldName={"originalGainedQualification"}
          labelCode={"l.originalGainedQualification"}
          autocompleteFn={getOriginalQualificationsAutocomplete}
        />
      </GridItem>
      <GridItem xs={12} sm={6} md={6}>
        <QualificationAutocompleteFormField
          fieldName={"gainedQualification"}
          labelCode={"l.gainedQualification"}
          autocompleteFn={getQualificationsAutocomplete}
        />
      </GridItem>
    </GridContainer>
  );
};

export default QualificationFormFields;
