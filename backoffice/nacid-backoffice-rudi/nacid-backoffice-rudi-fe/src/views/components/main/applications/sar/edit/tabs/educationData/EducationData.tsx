import { BoxSpg } from "@duosoftbg/nacid-components";
import ReadonlyNotesSection from "../../../../common/sections/educationData/notes/ReadonlyNotesSection";
import {
  AppSectionTitle,
  AppType,
  CreateFacultyDialog,
  CreateGraduationDocumentTypeDialog,
} from "@duosoftbg/nacid-backoffice-components";
import React from "react";
import UniversitySection from "../../../../common/sections/educationData/university/UniversitySection";
import DiplomaTypeSection from "../../../../common/sections/educationData/diplomaType/DiplomaTypeSection";
import BaseUniversityDialogsProvider from "../../../../common/sections/educationData/university/components/dialog/BaseUniversityDialogsProvider";
import EducationSection from "../../../../common/sections/educationData/education/EducationSection";
import SchoolSection from "../../../../common/sections/educationData/school/SchoolSection";
import PreviousDiplomaSection from "../../../../common/sections/educationData/previousDiploma/PreviousDiplomaSection";
import RecognitionPurposeSection from "../../../../common/sections/educationData/recognitionPurpose/RecognitionPurposeSection";
import { selectAppEduDataNotes } from "../../../../../../../../axios/api/services";
import EducationDataFormInitializer from "../../../../common/sections/educationData/EducationDataFormInitializer";
import SimilarDiplomasSection from "../../../../common/sections/educationData/similarDiplomas/SimilarDiplomasSection";

const EducationData = () => {
  const appType = AppType.SAR_APPLICATION;

  return (
    <BoxSpg>
      <AppSectionTitle title={"t.appSections.education"} />
      <BoxSpg>
        <EducationDataFormInitializer>
          <UniversitySection appType={appType} baseUniversityIdPointer={"baseUniversityId"} checkUnfilledUniversities />
          <DiplomaTypeSection appType={appType} />
          <EducationSection appType={appType} />
          <SchoolSection appType={appType} />
          <PreviousDiplomaSection appType={appType} />
          <RecognitionPurposeSection appType={appType} />
          <ReadonlyNotesSection selectNoteFn={selectAppEduDataNotes} />
          <SimilarDiplomasSection />
        </EducationDataFormInitializer>
      </BoxSpg>

      <BaseUniversityDialogsProvider />
      <CreateGraduationDocumentTypeDialog />
      <CreateFacultyDialog />
    </BoxSpg>
  );
};

export default EducationData;
