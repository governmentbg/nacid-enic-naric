import React from "react";
import { AlertSpg, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import SpecialityAutocompleteFormField from "./SpecialityAutocompleteFormField";
import { Chip } from "@mui/material";
import { Delete } from "@mui/icons-material";
import AddRecordButton from "../../../../../common/button/AddRecordButton";
import { useTranslation } from "react-i18next";
import {
  getOriginalSpecialitiesAutocomplete,
  getSpecialitiesAutocomplete,
} from "../../../../../../../services/autocompleteCalls";

const RudiSpecialityFormFields = ({
  addSpeciality,
  removeSpeciality,
  specialitiesList,
  originalNameLabelCode = "l.rudiSpecialityOriginalName",
  nameLabelCode = "l.rudiSpecialityName",
}) => {
  const { t } = useTranslation();

  console.log("RudiSpecialityFormFields");

  return (
    <React.Fragment>
      <GridContainer mt={2} spacing={4}>
        <GridItem sm={6} md={6}>
          <SpecialityAutocompleteFormField
            required={false}
            autocompleteFn={getOriginalSpecialitiesAutocomplete}
            specialityField={"specialitySingle.originalName"}
            specialityIdField={null}
            labelCode={originalNameLabelCode}
            additionalParams={null}
          />
        </GridItem>
        <GridItem sm={6} md={6}>
          <SpecialityAutocompleteFormField
            required={true}
            autocompleteFn={getSpecialitiesAutocomplete}
            specialityField={"specialitySingle.name"}
            specialityIdField={null}
            labelCode={nameLabelCode}
            additionalParams={null}
          />
        </GridItem>
        <GridItem sm={12} md={12}>
          <AddRecordButton labelCode={"l.btn.speciality.add"} onClick={addSpeciality} mt={-2} />
        </GridItem>
      </GridContainer>
      <GridContainer mt={0}>
        <GridItem xs={12} sm={12} md={12} pr={4}>
          {specialitiesList.map((spec, index) => (
            <Chip
              sx={{ mr: 2, mt: 2, maxWidth: "inherit" }}
              key={index + spec.name}
              label={`${spec.name}${spec.originalName ? " / " + spec.originalName : ""}`}
              onDelete={() => removeSpeciality(index)}
              deleteIcon={<Delete sx={{ pl: 5 }} />}
              variant="outlined"
            />
          ))}
        </GridItem>
      </GridContainer>
      <GridContainer mt={0} spacing={4}>
        <GridItem sm={12} md={12}>
          <AlertSpg severity={"info"}>{t("m.speciality.multiple.info")}</AlertSpg>
        </GridItem>
      </GridContainer>
    </React.Fragment>
  );
};
export default RudiSpecialityFormFields;
