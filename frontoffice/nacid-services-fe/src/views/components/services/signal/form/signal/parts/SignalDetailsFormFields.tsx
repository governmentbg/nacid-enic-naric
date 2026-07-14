import { GridContainer, GridItem, TextareaFormField } from "@duosoftbg/nacid-components";

const SignalDetailsFormFields = () => {
  const rows = 3;
  return (
    <>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <TextareaFormField
            required={true}
            rows={rows}
            fieldName={"violationDescription"}
            labelCode={"l.signal.violationDescription"}
          />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <TextareaFormField
            required={true}
            rows={rows}
            fieldName={"violationPlace"}
            labelCode={"l.signal.violationPlace"}
          />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <TextareaFormField rows={rows} fieldName={"checkRequirement"} labelCode={"l.signal.checkRequirement"} />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <TextareaFormField rows={rows} fieldName={"damagesDescription"} labelCode={"l.signal.damagesDescription"} />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <TextareaFormField rows={rows} fieldName={"measuresTaken"} labelCode={"l.signal.measuresTaken"} />
        </GridItem>
      </GridContainer>
    </>
  );
};
export default SignalDetailsFormFields;
