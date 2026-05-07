import React, { useEffect } from "react";
import { SelectFormField, GridItem, GridContainer } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { cfgRecognitionCategoryThunk } from "../../../../../../../store/redux/slice/AppData/cfgRecognitionCategory";
import OriginalGainedLevelAutocompleteFormField from "./OriginalGainedLevelAutocompleteFormField";
import OriginalGainedLevelTranslatedAutocompleteFormField from "./OriginalGainedLevelTranslatedAutocompleteFormField";

const EducationBeginningFormFields = ({
  applicationType,
  applicationSubtype,
  withRecognitionCategory,
  profGroupFields,
}) => {
  const dispatch = useAppDispatch();

  const cfgRecognitionCategoryThunkState = useAppSelector((state) => {
    return state.AppData.CfgRecognitionCategory;
  });

  const originalGainedLevelFieldMD = withRecognitionCategory ? 4 : 6;

  useEffect(() => {
    if (withRecognitionCategory) {
      dispatch(cfgRecognitionCategoryThunk());
    }
  }, [dispatch, withRecognitionCategory]);

  return (
    <React.Fragment>
      <GridContainer spacing={4} mt={0}>
        {withRecognitionCategory && (
          <GridItem>
            <SelectFormField
              required={true}
              fieldName={"recognitionCategory.id"}
              labelCode={"l.recognitionCategory"}
              addEmptyOption={true}
              selectOptions={cfgRecognitionCategoryThunkState.data
                .filter(
                  (cfg) => cfg.applicationType === applicationType && cfg.applicationSubtype === applicationSubtype
                )
                .map((cfg) => {
                  return {
                    value: cfg.recognitionCategory.id,
                    text: cfg.recognitionCategory.name,
                    active: cfg.recognitionCategory.isActive,
                  };
                })}
            />
          </GridItem>
        )}
        <GridItem md={originalGainedLevelFieldMD}>
          <OriginalGainedLevelAutocompleteFormField />
        </GridItem>
        <GridItem md={originalGainedLevelFieldMD}>
          <OriginalGainedLevelTranslatedAutocompleteFormField />
        </GridItem>
        {profGroupFields}
      </GridContainer>
    </React.Fragment>
  );
};
export default EducationBeginningFormFields;
