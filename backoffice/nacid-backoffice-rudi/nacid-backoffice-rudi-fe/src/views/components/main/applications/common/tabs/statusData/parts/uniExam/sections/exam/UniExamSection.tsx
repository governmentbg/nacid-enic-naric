import { FormSection } from "@duosoftbg/nacid-components";
import React from "react";
import CommonData from "./components/common/CommonData";
import UniversityData from "./components/university/UniversityData";
import CompetentInstitutionData from "./components/compInst/CompetentInstitutionData";
import NotesData from "./components/notes/NotesData";

const UniExamSection = ({ tempDataKey, competentInstitutionPointer }) => {
  return (
    <FormSection label={"l.university.examination"}>
      <CommonData />
      <UniversityData />
      <CompetentInstitutionData tempDataKey={tempDataKey} competentInstitutionPointer={competentInstitutionPointer} />
      <NotesData />
    </FormSection>
  );
};
export default UniExamSection;
