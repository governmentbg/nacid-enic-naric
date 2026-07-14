import { BoxSpg } from "@duosoftbg/nacid-components";
import { AppSectionTitle, i18nKeyByCode } from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import { useParams } from "react-router-dom";
import UniExamPartFormInitializer from "./UniExamPartFormInitializer";
import UniExamSection from "./sections/exam/UniExamSection";
import UniExamListSection from "./sections/table/UniExamListSection";
import UniversityInfoSection from "./sections/uniInfo/UniversityInfoSection";
import DialogsProvider from "./DialogsProvider";
import StatusUpdateAlert from "../../../../status/StatusUpdateAlert";
import UniExamAttachmentsSection from "./sections/attachment/UniExamAttachmentsSection";

const UniExamPart = ({ appType }) => {
  const { id: applicationId } = useParams();
  const tempDataKey = `${appType}-${applicationId}`;
  const competentInstitutionPointer = "ciAutocompleteField";

  return (
    <>
      <BoxSpg>
        <AppSectionTitle title={i18nKeyByCode(appType, "t.appSubSections.uniExam")} />
      </BoxSpg>
      <BoxSpg>
        <DialogsProvider tempDataKey={tempDataKey} competentInstitutionPointer={competentInstitutionPointer} />
        <UniExamPartFormInitializer>
          <StatusUpdateAlert />
          <UniversityInfoSection />
          <UniExamSection tempDataKey={tempDataKey} competentInstitutionPointer={competentInstitutionPointer} />
          <UniExamAttachmentsSection applicationId={applicationId} tempDataKey={tempDataKey} />
          <UniExamListSection applicationId={applicationId} />
        </UniExamPartFormInitializer>
      </BoxSpg>
    </>
  );
};

export default UniExamPart;
