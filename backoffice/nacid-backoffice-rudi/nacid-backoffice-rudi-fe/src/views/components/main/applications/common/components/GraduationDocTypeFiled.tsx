import {
  GridItem,
  SelectFormField,
  useAsyncCall,
  useExternalFormField,
  useReloadWatcherReader,
} from "@duosoftbg/nacid-components";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import { useFormContext } from "react-hook-form";
import { CoreApiServicesBase, LibraryComponentsControlActions } from "@duosoftbg/nacid-backoffice-components";
import { IconButton, Tooltip } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";

const GraduationDocTypeField = ({ tempDataKey, baseUniversityIdPointer }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const { asyncCall } = useAsyncCall();
  const { getValues, setValue } = useFormContext();
  const [graduationDocOptions, setGraduationDocOptions] = useState([]);
  const [baseUniversityCountry, setBaseUniversityCountry] = useState(null);

  const baseUniversityId = useExternalFormField({ key: tempDataKey, pointer: baseUniversityIdPointer });
  useExternalFormField({ key: tempDataKey, pointer: "graduationDocumentTypeId" });

  const { reloadWatcher: gdtWatcher } = useReloadWatcherReader({
    key: tempDataKey,
    pointer: "graduationDocumentTypeId",
  });

  useEffect(() => {
    if (baseUniversityId) {
      asyncCall({
        promise: CoreApiServicesBase.getUniversity(baseUniversityId),
        processResponseErrors: false,
        onSuccess: (response) => {
          setBaseUniversityCountry(response.country.id);
          asyncCall({
            promise: CoreApiServicesBase.selectGraduationDocTypesByCountryAndEducation(response.country.id, "H"),
            processResponseErrors: false,
            onSuccess: (response) => {
              setGraduationDocOptions(response);
              if (!response.some((e) => e.value === getValues("graduationDocumentTypeId"))) {
                setValue("graduationDocumentTypeId", null);
              }
            },
          });
        },
      });
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [asyncCall, baseUniversityId, gdtWatcher]);

  const handleClickAdd = () => {
    dispatch(
      LibraryComponentsControlActions.graduationDocumentTypeActions.openCreateGraduationDocumentTypeModal({
        tempDataKey,
        tempDataPointer: "graduationDocumentTypeId",
        baseUniversityCountry,
      }),
    );
  };

  if (baseUniversityId) {
    return (
      <GridItem sm={6} md={6}>
        <div style={{ width: "100%", position: "relative" }}>
          <div style={{ width: "calc(100% - 50px)" }}>
            <SelectFormField
              required={false}
              isDisabled={false}
              fieldName={"graduationDocumentTypeId"}
              labelCode={"l.graduationDocumentTypeId"}
              addEmptyOption={true}
              selectOptions={graduationDocOptions}
            />
          </div>
          <div style={{ width: "50px", position: "absolute", right: -2, top: -2 }}>
            <Tooltip title={t("l.add.graduationDocumentType")}>
              <IconButton color="primary" onClick={handleClickAdd}>
                <AddIcon />
              </IconButton>
            </Tooltip>
          </div>
        </div>
      </GridItem>
    );
  } else {
    return null;
  }
};
export default GraduationDocTypeField;
