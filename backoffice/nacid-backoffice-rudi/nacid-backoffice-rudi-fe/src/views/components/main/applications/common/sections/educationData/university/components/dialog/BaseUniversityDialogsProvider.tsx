import React from "react";
import SearchUniversityDialog from "./search/SearchUniversityDialog";
import ViewUniversityDialog from "./view/ViewUniversityDialog";
import CreateUniversityDialog from "./create/CreateUniversityDialog";
import EditUniversityDialog from "./create/EditUniversityDialog";

const BaseUniversityDialogsProvider = () => {
  return (
    <>
      <SearchUniversityDialog />
      <ViewUniversityDialog />
      <CreateUniversityDialog />
      <EditUniversityDialog />
    </>
  );
};

export default BaseUniversityDialogsProvider;
