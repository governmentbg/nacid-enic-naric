import React, { useEffect } from "react";
import {
  CheckboxFormField,
  DependencyAutocompleteFormField1Param,
  FormSection,
  GridContainer,
  GridItem,
  isArrayNotEmpty,
  isNotEmpty,
  ScrollableAsyncFormAutocomplete,
  TextareaFormField,
} from "@duosoftbg/nacid-components";
import { useFormContext, useWatch } from "react-hook-form";
import { CommissionMemberPositionSelectField, EducationLevelSelectField } from "@duosoftbg/nacid-backoffice-components";
import { qualificationObjectInitValues } from "../../../../../../../../../../../../../init/application/commissionMembers/applicationCommissionMemberInitialValues";
import {
  getLegalReasonByMemberPositionCode,
  getQualificationsAutocomplete,
} from "../../../../../../../../../../../../../axios/api/services";
import SpecialitiesFilter from "../../../../../../../../../../../common/search/filters/definition/autocomplete/SpecialitiesFilter";

const ExpertPositionDataEdit = ({ applicationId }) => {
  const { getValues, setValue } = useFormContext();
  const id = useWatch({ name: "id" });

  useEffect(() => {
    const applicationCommissionMemberSpecialities = getValues("applicationCommissionMemberSpecialities");
    const qualification = getValues("qualification");
    if (isArrayNotEmpty(applicationCommissionMemberSpecialities)) {
      let specialities = applicationCommissionMemberSpecialities.map(function (obj) {
        return obj.speciality;
      });
      setValue("specialities", specialities);
    } else {
      setValue("specialities", []);
    }
    if (isNotEmpty(qualification)) {
      setValue("qualificationObject", { id: qualification, name: qualification });
    } else {
      setValue("qualificationObject", qualificationObjectInitValues);
    }
    // eslint-disable-next-line
}, [id]);

  return (
    <>
      <FormSection label={"t.expert.position.data"}>
        <GridContainer spacing={3} mt={0}>
          <GridItem sm={12} md={12}>
            <TextareaFormField fieldName={"courseContent"} labelCode={"l.courseContent"} />
          </GridItem>
          <GridItem sm={12} md={6}>
            <CommissionMemberPositionSelectField
              field={"commissionMemberPosition"}
              required={true}
            ></CommissionMemberPositionSelectField>
          </GridItem>
          <GridItem sm={12} md={6}>
            <DependencyAutocompleteFormField1Param
              fieldId={"legalReason.id"}
              labelCode={"l.legal.reason"}
              required={false}
              disabled={false}
              initialValue={getValues("legalReason.id")}
              selectOptions={() => {
                return getLegalReasonByMemberPositionCode(getValues("commissionMemberPosition.id"));
              }}
              watchField={"commissionMemberPosition.id"}
            />
          </GridItem>
          <GridItem sm={12} md={6}>
            <EducationLevelSelectField field={"eduLevel"} applicationId={applicationId} />
          </GridItem>
          <GridItem sm={12} md={6}>
            <ScrollableAsyncFormAutocomplete
              freeSolo={true}
              fieldName={`qualificationObject.id`}
              selectedOption={getValues("qualificationObject")}
              setOptionText={(option) => option.name}
              autocompleteFn={getQualificationsAutocomplete}
              label={"l.prof.qualification"}
              reduceOptionObject={false}
              getOptionLabel={(option) => option.id + ""}
              setInputOnSelect={(option) => option.name}
            />
          </GridItem>
        </GridContainer>
        <GridContainer mt={0}>
          <SpecialitiesFilter></SpecialitiesFilter>
        </GridContainer>
        <GridContainer spacing={3} mt={2}>
          <GridItem sm={12} md={12}>
            <TextareaFormField fieldName={"previousBoardDecisions"} labelCode={"l.previousBoardDecisions"} />
          </GridItem>
          <GridItem sm={12} md={12}>
            <TextareaFormField fieldName={"similarBulgarianPrograms"} labelCode={"l.similarBulgarianPrograms"} />
          </GridItem>
          <GridItem sm={12} md={12}>
            <CheckboxFormField fieldName={`processStatus`} labelCode={"l.processStatus"} />
          </GridItem>
        </GridContainer>
      </FormSection>
    </>
  );
};

export default ExpertPositionDataEdit;
