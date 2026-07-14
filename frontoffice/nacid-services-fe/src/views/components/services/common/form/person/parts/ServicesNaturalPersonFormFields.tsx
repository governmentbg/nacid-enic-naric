import {
  NaturalPersonIdentifierFormFields,
  NaturalPersonNamesFormFields,
  NaturalPersonBirthFormFields,
  NaturalPersonAdditionalFormFields,
  IdentifierType,
  NaturalPersonHumanitarianStatusFormFields,
  NaturalPersonTitleFormFields,
} from "@duosoftbg/nacid-frontoffice-components";
import React from "react";
import { useFormContext, useWatch } from "react-hook-form";
import { countriesDataThunk } from "../../../../../../../store/redux/slice/AppData/countriesData";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import {
  getExtractedBirthDate,
  getSettlement,
  getSettlementsAutocomplete,
} from "../../../../../../../services/coreServicesCalls";
import { foreignIdTypeThunk } from "../../../../../../../store/redux/slice/AppData/foreignIdType";
import { GridContainer, GridItem, AlertSpg } from "@duosoftbg/nacid-components";
import { humanitarianStatusThunk } from "../../../../../../../store/redux/slice/AppData/humanitarianStatus";
import { useTranslation } from "react-i18next";

const ServicesNaturalPersonFormFields = ({ field, showEmail = true, naturalPersonBirthPlaceCitizenshipRequired }) => {
  const { getValues } = useFormContext();
  const { t } = useTranslation();

  const thunkStateCountries = useAppSelector((state) => {
    return state.AppData.CountriesData;
  });

  const thunkStateForeignIdTypes = useAppSelector((state) => {
    return state.AppData.ForeignIdType;
  });

  const thunkStateHumanitarianStatus = useAppSelector((state) => {
    return state.AppData.HumanitarianStatus;
  });

  const personalIdType = useWatch({ name: `${field}.personalIdType` });
  useWatch({ name: `${field}.userName` });
  const birthCountryId = useWatch({ name: `${field}.birthCountry.id` });
  const citizenshipId = useWatch({ name: `${field}.citizenship.id` });

  return (
    <GridContainer spacing={4} mt={0}>
      <GridItem sm={12} md={12}>
        <AlertSpg severity={"info"}>{t("m.naturalPerson.names.explanation")}</AlertSpg>
      </GridItem>
      <NaturalPersonNamesFormFields
        baseField={field}
        disabled={Boolean(getValues(field).userName)}
        firstNameRequired={true}
        middleNameRequired={birthCountryId === "BG" && citizenshipId === "BG"}
        lastNameRequired={citizenshipId === "BG" || personalIdType === IdentifierType.NATIONAL_ID}
      />
      <NaturalPersonIdentifierFormFields
        baseField={field}
        disabled={Boolean(getValues(field).userName)}
        countriesThunk={countriesDataThunk}
        countriesThunkState={thunkStateCountries}
        foreignIdThunk={foreignIdTypeThunk}
        foreignIdThunkState={thunkStateForeignIdTypes}
      />
      <NaturalPersonHumanitarianStatusFormFields
        baseField={field}
        humanitarianStatusThunk={humanitarianStatusThunk}
        humanitarianStatusThunkState={thunkStateHumanitarianStatus}
      />
      <NaturalPersonBirthFormFields
        baseField={field}
        countryRequired={naturalPersonBirthPlaceCitizenshipRequired}
        placeRequired={naturalPersonBirthPlaceCitizenshipRequired}
        dateRequired={true}
        countriesThunk={countriesDataThunk}
        countriesThunkState={thunkStateCountries}
        getSettlementsAutocomplete={getSettlementsAutocomplete}
        getSettlement={getSettlement}
        getExtractedBirthDate={getExtractedBirthDate}
      />
      <NaturalPersonAdditionalFormFields
        baseField={field}
        citizenshipRequired={naturalPersonBirthPlaceCitizenshipRequired}
        showEmail={showEmail}
        emailDisabled={Boolean(getValues(field).userName)}
        emailRequired={false}
        countriesThunk={countriesDataThunk}
        countriesThunkState={thunkStateCountries}
      />
      <NaturalPersonTitleFormFields baseField={field} />
    </GridContainer>
  );
};

export default ServicesNaturalPersonFormFields;
