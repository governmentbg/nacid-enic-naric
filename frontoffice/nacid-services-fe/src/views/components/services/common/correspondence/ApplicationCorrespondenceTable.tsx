import { useTranslation } from "react-i18next";
import { NacidTableSimple } from "@duosoftbg/nacid-components";
import React, { useState } from "react";
import CorrespondenceReadDialog from "./CorrespondenceReadDialog";
import CorrespondenceTableBody from "./CorrespondenceTableBody";

const ApplicationCorrespondenceTable = ({ correspondence, onReadChanged }) => {
  const { t } = useTranslation();
  const [correspondenceData, setCorrespondenceData] = useState({
    open: false,
    correspondence: null,
  });

  const headCells = [
    { id: "dateCreated", label: t("h.correspondence.dateCreated"), sortable: true },
    { id: "about", label: t("h.correspondence.about"), sortable: true },
    { id: "registrationNumber", label: t("h.correspondence.registrationNumber"), sortable: true },
    { id: "dateRead", label: t("h.correspondence.dateRead"), sortable: true },
    { id: "options", label: t("h.options"), sortable: false },
  ];

  return (
    <>
      <NacidTableSimple headCells={headCells}>
        <CorrespondenceTableBody
          correspondence={correspondence}
          setCorrespondenceData={setCorrespondenceData}
          showTempNumber={false}
        />
      </NacidTableSimple>
      <CorrespondenceReadDialog
        correspondence={correspondenceData.correspondence}
        open={correspondenceData.open}
        onCloseDialog={(hasReadChanged) => {
          setCorrespondenceData({ open: false, correspondence: null });
          if (hasReadChanged) {
            onReadChanged();
          }
        }}
      />
    </>
  );
};
export default ApplicationCorrespondenceTable;
