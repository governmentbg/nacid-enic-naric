import { GridItem, SectionArrayFormFieldControl } from "@duosoftbg/nacid-components";
import React from "react";
import TrainingCourseSpecialityFormFields from "../sections/educationData/education/components/TrainingCourseSpecialityFormFields";
import { trainingCourseSpecialityInitialValues } from "../../../../../../init/trainingCourseSpeciality/trainingCourseSpecialityInitialValues";

type TrainingCourseSpecialitiesProps = {
  sectionTitle?: string;
  wrapper?: "text-section" | "form-section";
};

const TrainingCourseSpecialities = ({
  sectionTitle = "",
  wrapper = "text-section",
}: TrainingCourseSpecialitiesProps) => {
  return (
    <GridItem sm={12} md={12}>
      <SectionArrayFormFieldControl
        field={"trainingCourseSpecialities"}
        renderFormFields={(index, key) => {
          return (
            <TrainingCourseSpecialityFormFields index={index} baseField={"trainingCourseSpecialities"} key={key} />
          );
        }}
        initialValues={trainingCourseSpecialityInitialValues}
        addBtnLabelCode={"l.btn.tc.speciality.add"}
        removeBtnLabelCode={"l.btn.tc.speciality.remove"}
        formLabelCode={sectionTitle}
        titlePosition={wrapper === "text-section" ? "global" : "section"}
        removeBtnPosition={"right"}
        withDivider={false}
      />
    </GridItem>
  );
};
export default TrainingCourseSpecialities;
