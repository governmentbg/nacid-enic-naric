import {
  ArchiveNumberFormField,
  DocflowStatusSelectField,
  NormalStatusAutocompleteFormField,
  ReloadWatcherObject,
} from "@duosoftbg/nacid-backoffice-components";
import {
  ConcealmentDependencyAutocompleteFormField1Param,
  FormSection,
  GridContainer,
  GridItem,
  useReloadWatcherReader,
} from "@duosoftbg/nacid-components";
import React, { useEffect, useState } from "react";
import { getLegalReasonByApplicationAndStatus } from "../../../../../../../../../../../axios/api/services";
import { useFormContext } from "react-hook-form";
import SubmittedDocumentsFormField from "./components/SubmittedDocumentsFormField";
import RecognizedDetailsFormFields from "./components/RecognizedDetailsFormFields";

const StatusSection = ({ appType, applicationId }) => {
  const { getValues } = useFormContext();
  const { reloadWatcher } = useReloadWatcherReader(ReloadWatcherObject.Status.save());

  const legalReason = getValues("legalReason.id");
  const sectionTitle = "t.applicationStatus.details";
  const [archiveNumber, setArchiveNumber] = useState(getValues("archiveNumber"));
  const [submittedDocs, setSubmittedDocs] = useState(getValues("submittedDocs"));

  useEffect(() => {
    if (reloadWatcher) {
      setArchiveNumber(getValues("archiveNumber"));
      setSubmittedDocs(getValues("submittedDocs"));
    }
    // eslint-disable-next-line
  }, [reloadWatcher]);

  return (
    <FormSection label={sectionTitle}>
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={12} md={6}>
          <NormalStatusAutocompleteFormField applicationId={applicationId} />
        </GridItem>
        <GridItem sm={12} md={6}>
          <DocflowStatusSelectField field={"docflowStatus.id"} onlyActive required={true} />
        </GridItem>
      </GridContainer>

      <GridContainer spacing={4} mt={0}>
        <GridItem sm={12} md={6}>
          <ConcealmentDependencyAutocompleteFormField1Param
            fieldId={"legalReason.id"}
            labelCode={"l.legal.reason"}
            required={false}
            disabled={false}
            onlyActive={true}
            initialValue={legalReason}
            selectOptions={() => {
              return getLegalReasonByApplicationAndStatus(applicationId, getValues("status.id"));
            }}
            watchField={"status.id"}
            withGrid={false}
          />
        </GridItem>
        <ArchiveNumberFormField initialArchiveNumber={archiveNumber} prefix={"ИД-05-01-"} />
      </GridContainer>

      <SubmittedDocumentsFormField initialSubmittedDocs={submittedDocs} />
      <RecognizedDetailsFormFields appType={appType} reloadWatcher={reloadWatcher} applicationId={applicationId} />
    </FormSection>
  );
};

export default StatusSection;
