import React from "react";
import ArrayFormControl from "../../ArrayFormControl";
import { initialEducationPlace } from "../../../../../../../init/common/educationInitialValues";
import EducationPlaceFormFields from "./EducationPlaceFormFields";

const EducationPlaceArrayFormFields = () => {
  return (
    <ArrayFormControl
      field={"educationPlaces"}
      initialValues={initialEducationPlace}
      renderFormFields={(index, key) => {
        return <EducationPlaceFormFields index={index} key={key} />;
      }}
      addBtnLabelCode={"l.btn.educationPlace.add"}
      subsequentTitleCode={"t.educationPlace.additional.places"}
    />
  );
};

export default EducationPlaceArrayFormFields;
