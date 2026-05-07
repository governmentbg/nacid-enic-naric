import * as React from "react";
import { ScrollableAsyncFormAutocomplete, SearchFilterGrid } from "@duosoftbg/nacid-components";
import { getUniversitiesAutocomplete, selectUniversityOptionById } from "../../../../../../../axios/api/services";
import { useFormContext } from "react-hook-form";

const DiplomaUniversityFilter = ({ xs = 12, sm = 4, md = 4 }) => {
  const { getValues } = useFormContext();
  return (
    <SearchFilterGrid xs={xs} sm={sm} md={md}>
      <ScrollableAsyncFormAutocomplete
        fieldName={"universityId"}
        selectedOption={{ id: getValues("universityId"), name: "" }}
        setOptionText={(option) => option.name}
        getOptionLabel={(option) => option.id + ""}
        setInputOnSelect={(option) => option.name}
        autocompleteFn={getUniversitiesAutocomplete}
        selectedOptionFunction={selectUniversityOptionById}
        label={"l.prevDiplomaUniversity"}
        reduceOptionObject={false}
      />
    </SearchFilterGrid>
  );
};

export default DiplomaUniversityFilter;
