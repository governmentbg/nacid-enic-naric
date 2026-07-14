import { CheckboxFormField, DividerSpg, FormSection, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import CommissionSessionFilters from "./session/CommissionSessionFilters";
import CommissionStatusFilters from "./status/CommissionStatusFilters";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";
import { useReportSectionClearOnUnmount } from "@duosoftbg/nacid-backoffice-components";
import React, { useEffect } from "react";
import { useFormContext, useWatch } from "react-hook-form";

const ReportCommissionSection = ({ reportGroup }) => {
  const baseField = "commission";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  return (
    <FormSection label={label}>
      <CommissionReviewedFormFields baseField={baseField} />
      <CommissionFormFields baseField={baseField} />
    </FormSection>
  );
};

const CommissionReviewedFormFields = ({ baseField }) => {
  useWatch({ name: `${baseField}.isNotCommissionReviewed` });
  useWatch({ name: `${baseField}.isCommissionReviewed` });

  return (
    <GridContainer>
      <IsCommissionReviewedFormField baseField={baseField} />
      <IsNotCommissionReviewedFormField baseField={baseField} />
    </GridContainer>
  );
};

const IsCommissionReviewedFormField = ({ baseField }) => {
  const { setValue, getValues } = useFormContext();
  const isCommissionReviewed = getValues(`${baseField}.isCommissionReviewed`);

  useEffect(() => {
    if (isCommissionReviewed) {
      setValue(`${baseField}.isNotCommissionReviewed`, false);
    }
    // eslint-disable-next-line
  }, [isCommissionReviewed]);

  return (
    <GridItem sm={6} md={3}>
      <CheckboxFormField fieldName={`${baseField}.isCommissionReviewed`} labelCode={"t.commission.reviewed"} />
    </GridItem>
  );
};

const IsNotCommissionReviewedFormField = ({ baseField }) => {
  const { setValue, getValues } = useFormContext();
  const isNotCommissionReviewed = getValues(`${baseField}.isNotCommissionReviewed`);

  useEffect(() => {
    if (isNotCommissionReviewed) {
      setValue(`${baseField}.isCommissionReviewed`, false);
    }
    // eslint-disable-next-line
  }, [isNotCommissionReviewed]);

  return (
    <GridItem sm={6} md={3}>
      <CheckboxFormField fieldName={`${baseField}.isNotCommissionReviewed`} labelCode={"t.commission.not.reviewed"} />
    </GridItem>
  );
};

const CommissionFormFields = ({ baseField }) => {
  const isCommissionReviewed = useWatch({ name: `${baseField}.isCommissionReviewed` });
  const { setValue } = useFormContext();

  useEffect(() => {
    if (!isCommissionReviewed) {
      setValue("commission.commissionStatuses", []);
    }
    // eslint-disable-next-line
  }, [isCommissionReviewed]);

  if (!isCommissionReviewed) {
    return null;
  }

  return (
    <>
      <CommissionSessionFilters baseField={baseField} />
      <DividerSpg my={4} />
      <CommissionStatusFilters />
    </>
  );
};

export default ReportCommissionSection;
