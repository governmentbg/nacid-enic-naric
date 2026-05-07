import {
  GridItem,
  CheckboxFormField,
  SelectFormField,
  FileUploadButton,
  AsyncCallArgs,
  useAsyncCall,
} from "@duosoftbg/nacid-components";
import { FormLabel } from "@mui/material";
import React, { useEffect } from "react";
import { useTranslation } from "react-i18next";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import { documentDeliveryCopyTypeThunk } from "../../../../../../../store/redux/slice/AppData/documentDeliveryCopyType";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { uploadFile } from "../../../../../../../services/coreServicesCalls";
import { useGoogleReCaptcha } from "react-google-recaptcha-v3";
import { fileGroupConfig, fileGroups } from "../../../../../../../config/fileGroupConfig";
import FileDetails from "../../../../common/form/document/FileDetails";

const DocBibliographicDetailsFormFields = ({ methods }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const { executeRecaptcha } = useGoogleReCaptcha();
  const { asyncCall } = useAsyncCall();

  const documentDeliveryCopyType = useAppSelector((state) => {
    return state.AppData.DocumentDeliveryCopyType;
  });

  useEffect(() => {
    dispatch(documentDeliveryCopyTypeThunk());
  }, [dispatch]);

  const onFileChange = async (e) => {
    const token = await executeRecaptcha("ServicesFileUpload");
    const uploadAttachmentFile: AsyncCallArgs = {
      promise: uploadFile(e.target.files[0], token, fileGroups.editableDoc),
      withGlobalBackdrop: true,
      reactHooksForm: { methods },
      onSuccess: (response) => {
        const file = { ...response.data };
        methods.setValue("file", file);
        methods.clearErrors("file");
      },
    };
    asyncCall(uploadAttachmentFile);
  };

  return (
    <>
      <GridItem sm={12} md={12}>
        <FormLabel required={true}>{t("l.docDelivery.bibliographicDetails.file")}</FormLabel>
      </GridItem>
      <GridItem sm={12} md={12}>
        <FileUploadButton
          id={"doc-bibliographic-details-file-upload"}
          onFileChange={onFileChange}
          accept={fileGroupConfig[fileGroups.editableDoc]}
        />
        <FileDetails />
      </GridItem>
      <GridItem sm={12} md={12}>
        <FormLabel required={true}>{t("l.docDelivery.bibliographicDetails.sources")}</FormLabel>
      </GridItem>

      <GridItem sm={6} md={6}>
        <CheckboxFormField
          fieldName={`electronicCatalogues`}
          labelCode={"l.docDelivery.bibliographicDetails.electronicCatalogues"}
        />
      </GridItem>
      <GridItem sm={6} md={6}>
        <CheckboxFormField fieldName={`bgLibraries`} labelCode={"l.docDelivery.bibliographicDetails.bgLibraries"} />
      </GridItem>
      <GridItem sm={12} md={12}>
        <CheckboxFormField
          fieldName={`foreignLibraries`}
          labelCode={"l.docDelivery.bibliographicDetails.foreignLibraries"}
        />
      </GridItem>
      <GridItem sm={12} md={12}>
        {documentDeliveryCopyType.data && documentDeliveryCopyType.data.length > 0 && (
          <SelectFormField
            required={true}
            fieldName={`deliveryResultKind.id`}
            labelCode={"l.docDelivery.bibliographicDetails.deliveryResultKind"}
            addEmptyOption={false}
            selectOptions={documentDeliveryCopyType.data.map((docCopyType) => {
              return { value: docCopyType.id, text: docCopyType.name, active: docCopyType.isActive };
            })}
          />
        )}
      </GridItem>
    </>
  );
};
export default DocBibliographicDetailsFormFields;
