import { FreeSoloAutocompleteFromViewFormField, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import React from "react";
import {
  getOriginalSpecialitiesFreeSolo,
  getSpecialitiesFreeSolo,
} from "../../../../../../../../../axios/api/services";

const TrainingCourseSpecialityFormFields = ({ index, baseField }) => {
  const baseFieldRevised = `${baseField}.${index}`;

  return (
    <>
      <GridContainer mt={index === 0 ? 5 : 4}>
        <GridItem sm={6} md={6}>
          <FreeSoloAutocompleteFromViewFormField
            fieldName={`${baseFieldRevised}.speciality`}
            autocompleteFn={getSpecialitiesFreeSolo}
            labelCode={"l.speciality.translated"}
            inputMinSearchLength={1}
            required
          />
        </GridItem>
        <GridItem sm={6} md={6}>
          <FreeSoloAutocompleteFromViewFormField
            fieldName={`${baseFieldRevised}.originalSpeciality`}
            autocompleteFn={getOriginalSpecialitiesFreeSolo}
            labelCode={"l.speciality"}
            inputMinSearchLength={1}
          />
        </GridItem>
      </GridContainer>
    </>
  );
};
export default TrainingCourseSpecialityFormFields;
