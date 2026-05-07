import React from "react";
import UniversityFormProvider from "./UniversityFormProvider";

const UniversityForm = ({ universityId, universityIdPointer, universityTDK, initialData = null }) => {
  return (
    <UniversityFormProvider
      universityId={universityId}
      universityIdPointer={universityIdPointer}
      universityTDK={universityTDK}
      initialData={initialData}
    />
  );
};

export default UniversityForm;
