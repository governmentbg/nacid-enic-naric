import { useFormContext } from "react-hook-form";
import {
  BlockText,
  FormSection,
  GridContainer,
  GridItem,
  isArrayNotEmpty,
  SimpleFetchAutocompleteFormField,
  useExternalFormField,
  useReloadWatcherReader,
} from "@duosoftbg/nacid-components";
import React from "react";
import { selectCompetentInstitutionByCountries } from "../../../../../../../../../../../axios/api/services";
import { i18nKeyByCode, ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";
import CompetentInstitutionMenuButton from "../../../common/button/CompetentInstitutionMenuButton";

const InfoSourcesSection = ({ appType, tempDataKey, competentInstitutionPointer }) => {
  const { getValues } = useFormContext();
  const universities = getValues("universityNames");

  const competentInstitutionId = useExternalFormField({ key: tempDataKey, pointer: competentInstitutionPointer });
  const { reloadWatcher } = useReloadWatcherReader(ReloadWatcherObject.CompetentInstitution.change());

  const uniRegisterLabel = i18nKeyByCode(appType, "l.diplomaExam.universityRegister");

  return (
    <FormSection label={"l.diplomaExam.infoSources"}>
      {isArrayNotEmpty(universities) && (
        <GridContainer spacing={4} mt={0}>
          {universities.map((university) => {
            return (
              <GridItem md={6} key={university}>
                <BlockText label={uniRegisterLabel} text={university} />
              </GridItem>
            );
          })}
        </GridContainer>
      )}
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={12} md={12}>
          <div style={{ width: "100%", position: "relative" }}>
            <div style={{ width: "calc(100% - 30px)" }}>
              <SimpleFetchAutocompleteFormField
                key={reloadWatcher}
                initialValue={competentInstitutionId}
                fieldName={competentInstitutionPointer}
                labelCode={"t.national.competentInstitution.data"}
                autocompleteFn={() => selectCompetentInstitutionByCountries(getValues("universityCountryIds"))}
                setInputOnSelect={(option) => `${option.name} / ${option.country?.name}`}
                setOptionText={(option) => `${option.name} / ${option.country?.name}`}
              />
            </div>
            <div style={{ width: "30px", position: "absolute", right: -2, top: -8 }}>
              <CompetentInstitutionMenuButton
                competentInstitutionId={competentInstitutionId}
                withCountry={getValues("universityCountryIds")?.length === 1}
                countryField={"universityCountryIds[0]"}
              />
            </div>
          </div>
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default InfoSourcesSection;
