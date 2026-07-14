import { GridContainer, LabeledDataItem, TextSection } from "@duosoftbg/nacid-components";

const CorrespondenceBaseDetails = ({ correspondence }) => {
  return (
    <TextSection label={"t.application.correspondence.basic.details"}>
      <GridContainer mt={0} spacing={4}>
        <LabeledDataItem sm={6} md={6} labelCode={"l.correspondence.dateCreated"} data={correspondence.dateCreated} />
        <LabeledDataItem sm={6} md={6} labelCode={"l.correspondence.dateRead"} data={correspondence.dateRead} />
        <LabeledDataItem sm={6} md={6} labelCode={"l.correspondence.tempNumber"} data={correspondence.tempNumber} />
        <LabeledDataItem
          sm={6}
          md={6}
          labelCode={"l.correspondence.appType"}
          data={correspondence.applicationSubtypeName}
        />
        <LabeledDataItem sm={6} md={6} labelCode={"l.correspondence.about"} data={correspondence.about} />
        <LabeledDataItem
          sm={6}
          md={6}
          labelCode={"l.correspondence.registrationNumber"}
          data={correspondence.registrationNumber + "/" + correspondence.registrationDate}
        />
      </GridContainer>
    </TextSection>
  );
};
export default CorrespondenceBaseDetails;
