import { Status } from "@duosoftbg/nacid-backoffice-components";
import { GridContainer, GridItem, isEmpty, isNotEmpty, TextareaFormField } from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import { useFormContext, useWatch } from "react-hook-form";

const SubmittedDocumentsFormField = ({ initialSubmittedDocs }) => {
  const { setValue, getValues } = useFormContext();
  const status = useWatch({ name: `status.id` });

  useEffect(() => {
    if (
      isNotEmpty(initialSubmittedDocs) &&
      status !== Status.SUSPEND_SUBMITTED_DOCUMENTS &&
      getValues("submittedDocs") !== initialSubmittedDocs
    ) {
      setValue("submittedDocs", initialSubmittedDocs);
    }
    // eslint-disable-next-line
  }, [status]);

  return (
    <>
      {isEmpty(initialSubmittedDocs) && isNotEmpty(status) && status === Status.SUSPEND_SUBMITTED_DOCUMENTS && (
        <GridContainer spacing={4} mt={0}>
          <GridItem sm={12} md={12}>
            <TextareaFormField required={false} fieldName={"submittedDocs"} labelCode={"l.submittedDocs"} />
          </GridItem>
        </GridContainer>
      )}
      {isNotEmpty(initialSubmittedDocs) && (
        <GridContainer spacing={4} mt={0}>
          <GridItem sm={12} md={12}>
            <TextareaFormField
              required={false}
              fieldName={"submittedDocs"}
              labelCode={"l.submittedDocs"}
              isDisabled={isEmpty(status) || (isNotEmpty(status) && status !== Status.SUSPEND_SUBMITTED_DOCUMENTS)}
            />
          </GridItem>
        </GridContainer>
      )}
    </>
  );
};
export default SubmittedDocumentsFormField;
