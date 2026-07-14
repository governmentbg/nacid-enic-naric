import { BoxSpg, FormSection, GridSpg } from "@duosoftbg/nacid-components";
import { useFormContext, useWatch } from "react-hook-form";
import React from "react";
import useAppDispatch from "../../../../../../../../hooks/redux/base/useAppDispatch";
import { Link } from "@mui/material";
import { useTranslation } from "react-i18next";
import { UniversityControlActions } from "../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";
import { UniversitySearchForm } from "../../../../../../../../utils/helpers";
import SecondaryUniversityFragment from "./SecondaryUniversityFragment";
import { secondaryUniversityInitialValues } from "../../../../../../../../init/secondaryUniversity/secondaryUniversityInitialValues";

const SecondaryUniversitiesFormSection = ({
  tempDataKey,
  baseUniversityId,
  showTranslationFields = true,
  showContactFields = true,
}) => {
  const { getValues, setValue } = useFormContext();
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const secondaryUniversities = useWatch({ name: "secondaryUniversities" });

  const handleClick = () => {
    let sUnis = getValues().secondaryUniversities;
    sUnis = sUnis?.filter((item) => item?.university?.id);
    setValue("secondaryUniversities", sUnis);
    let index = sUnis && sUnis.length > 0 ? sUnis.length : 0;
    setValue(`secondaryUniversities[${index}]`, secondaryUniversityInitialValues);
    dispatch(
      UniversityControlActions.openSearchUniversityModal({
        universityIdPointer: `secondaryUniversities[${index}].university.id`,
        tempDataKey,
        searchFormValues: UniversitySearchForm.transformData(null),
      }),
    );
  };

  if (secondaryUniversities && secondaryUniversities.length > 0) {
    return (
      <FormSection label={"l.secondary.universities"}>
        <BoxSpg mt={2}>
          <Link onClick={handleClick} underline="hover" fontSize={13} style={{ cursor: "pointer" }}>
            {t("l.btn.add.secondary.university")}
          </Link>
        </BoxSpg>
        <GridSpg container spacing={1}>
          <GridSpg item xs={12}>
            {secondaryUniversities.map((object, i) => (
              <SecondaryUniversityFragment
                secondaryUniversityPointer={`secondaryUniversities[${i}]`}
                secondaryUniversityIdPointer={`secondaryUniversities[${i}].university.id`}
                tempDataKey={tempDataKey}
                key={"uvc-" + i}
                baseUniversityId={baseUniversityId}
                showTranslationFields={showTranslationFields}
                showContactFields={showContactFields}
              />
            ))}
          </GridSpg>
        </GridSpg>
      </FormSection>
    );
  } else {
    return (
      <FormSection label={"l.secondary.universities"}>
        <BoxSpg mt={2}>
          <Link onClick={handleClick} underline="hover" fontSize={13} style={{ cursor: "pointer" }}>
            {t("l.btn.add.secondary.university")}
          </Link>
        </BoxSpg>
      </FormSection>
    );
  }
};
export default SecondaryUniversitiesFormSection;
