import { GridItem, SelectFormField } from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";

const JointDegreeFlagFilter = ({ baseField }) => {
  const { t } = useTranslation();

  const options = [
    { value: false, text: t("l.degree.including") },
    { value: true, text: t("l.jointDegree.only") },
  ];

  return (
    <GridItem sm={12} md={9} pt={3}>
      <SelectFormField
        fieldName={`${baseField}.onlyJointDegree`}
        labelCode={"l.reportFilter.degree"}
        selectOptions={options}
        addEmptyOption={true}
        emptyOptionLabel={"l.degree.all"}
      />
    </GridItem>
  );
};
export default JointDegreeFlagFilter;
