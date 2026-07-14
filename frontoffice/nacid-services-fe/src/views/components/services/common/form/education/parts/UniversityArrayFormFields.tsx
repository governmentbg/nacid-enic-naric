import React from "react";
import UniversityFormFields from "../parts/UniversityFormFields";
import { initialUniversityData } from "../../../../../../../init/common/educationInitialValues";
import ArrayFormControl from "../../ArrayFormControl";

const UniversityArrayFormFields = () => {
  return (
    <ArrayFormControl
      field={"universitiesData"}
      renderFormFields={(index, key) => {
        return <UniversityFormFields index={index} key={key} />;
      }}
      initialValues={initialUniversityData}
      addBtnLabelCode={"l.btn.university.add"}
      subsequentTitleCode={"t.university.joint.universities"}
    />
  );
};
export default UniversityArrayFormFields;
