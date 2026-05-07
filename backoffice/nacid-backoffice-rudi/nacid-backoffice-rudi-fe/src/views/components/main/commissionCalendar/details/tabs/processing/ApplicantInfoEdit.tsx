import React, { useEffect } from "react";
import { useFormContext, useWatch } from "react-hook-form";
import { CommissionCalendarConst } from "../../../../../../../utils/constants";
import { GridItem, TextareaFormField } from "@duosoftbg/nacid-components";

const ApplicantInfoEdit = () => {
  const { getValues, setValue } = useFormContext();
  useWatch({ name: "statusCode" });
  const statusCode = getValues("statusCode");
  const validStatusCode = statusCode === CommissionCalendarConst.applicantInfoStatus;

  useEffect(() => {
    if (!validStatusCode) {
      setValue("applicantInfo", "");
    }
    // eslint-disable-next-line
  }, [statusCode]);

  if (!validStatusCode) {
    return null;
  } else {
    return (
      <GridItem sm={12} md={12}>
        <TextareaFormField rows={2} fieldName={"applicantInfo"} labelCode={"l.applicantInfo"} />
      </GridItem>
    );
  }
};

export default ApplicantInfoEdit;
