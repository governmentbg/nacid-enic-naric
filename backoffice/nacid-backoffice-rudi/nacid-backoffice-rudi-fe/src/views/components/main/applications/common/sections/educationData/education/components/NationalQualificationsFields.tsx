import {
  DependencyAutocompleteFormField1Param,
  GridItem,
  isNotEmpty,
  useExternalFormField,
} from "@duosoftbg/nacid-components";
import {
  CoreApiServicesBase,
  LibraryComponentsControlActions,
  SaveNationalQualificationsFrameworkDialog,
} from "@duosoftbg/nacid-backoffice-components";
import React, { useState } from "react";
import { useFormContext, useWatch } from "react-hook-form";
import { useDispatch } from "react-redux";
import { useTranslation } from "react-i18next";
import { IconButton, Tooltip } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";

const NationalQualificationsFields = ({ addNomenclaturesOption = true }) => {
  const dispatch = useDispatch();
  const { t } = useTranslation();
  const { getValues } = useFormContext();

  const country = useWatch({ name: "primaryUniversity.university.country.id" });

  const [modalNationalQualificationFieldId, setModalNationalQualificationFieldId] = useState<string>("");
  const nationalQualificationsTempDataKey = "SNQFD";
  useExternalFormField({ key: nationalQualificationsTempDataKey, pointer: "nationalQualificationFramework.id" });
  useExternalFormField({
    key: nationalQualificationsTempDataKey,
    pointer: "accessedNationalQualificationFramework.id",
  });

  const handleOpenNQModal = (id, modalType) => {
    setModalNationalQualificationFieldId("nationalQualificationFramework.id");
    const payload = { id, modalType, country };
    dispatch(LibraryComponentsControlActions.nationalQualificationsFrameworkControlActions.openModal(payload));
  };

  const handleOpenANQModal = (id, modalType) => {
    setModalNationalQualificationFieldId("accessedNationalQualificationFramework.id");
    const payload = { id, modalType, country };
    dispatch(LibraryComponentsControlActions.nationalQualificationsFrameworkControlActions.openModal(payload));
  };

  return (
    <>
      {addNomenclaturesOption && isNotEmpty(country) && (
        <SaveNationalQualificationsFrameworkDialog
          resetNationalQualificationExternalField={true}
          fieldId={modalNationalQualificationFieldId}
          tempFormDataKey={nationalQualificationsTempDataKey}
        />
      )}
      <GridItem sm={6} md={6}>
        {addNomenclaturesOption && isNotEmpty(country) && (
          <div style={{ width: "100%", position: "relative" }}>
            <div style={{ width: "calc(100% - 50px)" }}>
              <DependencyAutocompleteFormField1Param
                fieldObject={"nationalQualificationFramework"}
                fieldId={"id"}
                labelCode={"l.nationalQualificationFramework"}
                required={false}
                disabled={false}
                initialValue={getValues("nationalQualificationFramework.id")}
                selectOptions={CoreApiServicesBase.autocompleteNationalQualifications}
                watchField={"primaryUniversity.university.country.id"}
                reloadOptionsWatchField={"accessedNationalQualificationFramework.id"}
              />
            </div>
            <div style={{ width: "50px", position: "absolute", right: -2, top: -2 }}>
              <Tooltip title={t("l.create.new")}>
                <IconButton color="primary" onClick={() => handleOpenNQModal("", "edit")}>
                  <AddIcon />
                </IconButton>
              </Tooltip>
            </div>
          </div>
        )}
        {!(addNomenclaturesOption && isNotEmpty(country)) && (
          <DependencyAutocompleteFormField1Param
            fieldObject={"nationalQualificationFramework"}
            fieldId={"id"}
            labelCode={"l.nationalQualificationFramework"}
            required={false}
            disabled={false}
            initialValue={getValues("nationalQualificationFramework.id")}
            selectOptions={CoreApiServicesBase.autocompleteNationalQualifications}
            watchField={"primaryUniversity.university.country.id"}
          />
        )}
      </GridItem>
      <GridItem sm={6} md={6}>
        {addNomenclaturesOption && isNotEmpty(country) && (
          <div style={{ width: "100%", position: "relative" }}>
            <div style={{ width: "calc(100% - 50px)" }}>
              <DependencyAutocompleteFormField1Param
                fieldObject={"accessedNationalQualificationFramework"}
                fieldId={"id"}
                labelCode={"l.accessedNationalQualificationFramework"}
                required={false}
                disabled={false}
                initialValue={getValues("accessedNationalQualificationFramework.id")}
                selectOptions={CoreApiServicesBase.autocompleteNationalQualifications}
                watchField={"primaryUniversity.university.country.id"}
                reloadOptionsWatchField={"nationalQualificationFramework.id"}
              />
            </div>
            <div style={{ width: "50px", position: "absolute", right: -2, top: -2 }}>
              <Tooltip title={t("l.create.new")}>
                <IconButton color="primary" onClick={() => handleOpenANQModal("", "edit")}>
                  <AddIcon />
                </IconButton>
              </Tooltip>
            </div>
          </div>
        )}
        {!(addNomenclaturesOption && isNotEmpty(country)) && (
          <DependencyAutocompleteFormField1Param
            fieldObject={"accessedNationalQualificationFramework"}
            fieldId={"id"}
            labelCode={"l.accessedNationalQualificationFramework"}
            required={false}
            disabled={false}
            initialValue={getValues("accessedNationalQualificationFramework.id")}
            selectOptions={CoreApiServicesBase.autocompleteNationalQualifications}
            watchField={"primaryUniversity.university.country.id"}
          />
        )}
      </GridItem>
    </>
  );
};
export default NationalQualificationsFields;
