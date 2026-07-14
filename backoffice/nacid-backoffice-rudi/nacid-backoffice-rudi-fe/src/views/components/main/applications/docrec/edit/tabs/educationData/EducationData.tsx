import {
  AppSectionTitle,
  AppType,
  CreateFacultyDialog,
  CreateGraduationDocumentTypeDialog,
} from "@duosoftbg/nacid-backoffice-components";
import React from "react";
import { BoxSpg } from "@duosoftbg/nacid-components";
import UniversitySection from "../../../../common/sections/educationData/university/UniversitySection";
import DiplomaTypeSection from "../../../../common/sections/educationData/diplomaType/DiplomaTypeSection";
import EducationSection from "../../../../common/sections/educationData/education/EducationSection";
import PreviousDiplomaSection from "../../../../common/sections/educationData/previousDiploma/PreviousDiplomaSection";
import ReadonlyNotesSection from "../../../../common/sections/educationData/notes/ReadonlyNotesSection";
import { selectAppEduDataNotes } from "../../../../../../../../axios/api/services";
import BaseUniversityDialogsProvider from "../../../../common/sections/educationData/university/components/dialog/BaseUniversityDialogsProvider";
import DissertationSection from "../../../../common/sections/educationData/dissertation/DissertationSection";
import EducationDataFormInitializer from "../../../../common/sections/educationData/EducationDataFormInitializer";
import SimilarDiplomasSection from "../../../../common/sections/educationData/similarDiplomas/SimilarDiplomasSection";
import JurySection from "../../../../common/sections/educationData/dissertation/JurySection";

const EducationData = () => {
  const appType = AppType.DOCREC_APPLICATION;
  return (
    <BoxSpg>
      <AppSectionTitle title={"t.appSections.education"} />
      <BoxSpg>
        <EducationDataFormInitializer>
          <UniversitySection appType={appType} baseUniversityIdPointer={"baseUniversityId"} checkUnfilledUniversities />
          <DiplomaTypeSection appType={appType} />
          <EducationSection appType={appType} />
          <DissertationSection appType={appType} />
          <JurySection />
          <PreviousDiplomaSection appType={appType} />
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
