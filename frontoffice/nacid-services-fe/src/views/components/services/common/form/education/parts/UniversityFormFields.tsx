import { GridItem, GridContainer, TextareaFormField, AlertSpg, DividerSpg } from "@duosoftbg/nacid-components";
import UniversityAutocompleteFormField from "./UniversityAutocompleteFormField";
import FacultyAutocompleteFormField from "./FacultyAutocompleteFormField";
import { useTranslation } from "react-i18next";

const UniversityFormFields = ({ index }) => {
  const { t } = useTranslation();

  return (
    <GridContainer spacing={4} mt={0}>
      {index > 1 && (
        <GridItem sm={12} md={12}>
          <DividerSpg variant={"fullWidth"} />
        </GridItem>
      )}
      <GridItem sm={12} md={8}>
        <UniversityAutocompleteFormField
          required={true}
          nameField={`universitiesData.${index}.name`}
          nameIdField={`universitiesData.${index}.nameId`}
          labelCode={"l.university.name"}
        />
      </GridItem>
      <GridItem sm={12} md={4} pr={0}>
        <FacultyAutocompleteFormField
          uniIdField={`universitiesData.${index}.nameId`}
          nameField={`universitiesData.${index}.faculty`}
          nameIdField={`universitiesData.${index}.facultyId`}
          labelCode={"l.university.faculty"}
        />
      </GridItem>
      <GridItem sm={12} md={12} pr={0}>
        <TextareaFormField
          fieldName={`universitiesData.${index}.universityContact`}
          labelCode={"l.university.universityContact"}
          rows={2}
        />
        <GridItem sm={12} md={12} pr={0}>
          <AlertSpg mt={4} severity={"info"}>
            {t("m.university.universityContact.info")}
          </AlertSpg>
        </GridItem>
      </GridItem>
    </GridContainer>
  );
};

export default UniversityFormFields;
