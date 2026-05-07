import React, { useEffect, useState } from "react";
import { AsyncCallArgs, SelectFormField, useAsyncCall } from "@duosoftbg/nacid-components";
import { useDispatch } from "react-redux";
import { useParams } from "react-router-dom";
import { CoreApiServicesBase, DocumentCategories, DocumentTypeDirection } from "@duosoftbg/nacid-backoffice-components";

type CommissionMembersDocTypesSelectFieldProps = {
  field: string;
  label?: string;
  required?: boolean;
  disabled?: boolean;
};

const CommissionMembersDocTypesSelectField = ({
  field,
  label = "l.commissionMembersDocTypes",
  required = false,
  disabled = false,
}: CommissionMembersDocTypesSelectFieldProps) => {
  const { id } = useParams();
  const dispatch = useDispatch();
  const { asyncCall } = useAsyncCall();
  const [options, setOptions] = useState(null);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: CoreApiServicesBase.getDocumentTypesForApplication({
        applicationId: id,
        docCategory: DocumentCategories.COMMISSION_EXPERTS,
        direction: DocumentTypeDirection.OUT,
      }),
      processResponseErrors: false,
      onSuccess: (response) => {
        setOptions(response);
      },
      onError: () => {
        setOptions(null);
      },
    };
    asyncCall(asyncCallArgs);

    return () => {
      setOptions(null);
    };
  }, [asyncCall, dispatch, field, id]);

  if (options) {
    return (
      <SelectFormField
        required={required}
        isDisabled={disabled}
        fieldName={field}
        labelCode={label}
        addEmptyOption={true}
        selectOptions={options.map((el) => {
          return { value: el.id, text: el.name };
        })}
      />
    );
  }

  return null;
};

export default CommissionMembersDocTypesSelectField;
