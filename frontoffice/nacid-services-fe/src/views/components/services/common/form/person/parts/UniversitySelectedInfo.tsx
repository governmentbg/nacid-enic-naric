import { BlockText, GridContainer, GridItem, separatePath } from "@duosoftbg/nacid-components";
import Box from "@mui/system/Box";
import { buildFetchFileUrl } from "../../../../../../../services/coreServicesCalls";

const UniversitySelectedInfo = ({ university }) => {
  const { relativePath, fileId } = separatePath(university.logoRelativePath);

  return (
    <GridContainer>
      {relativePath && fileId && (
        <GridItem>
          <Box
            component="img"
            sx={{
              maxHeight: { xs: 233, md: 167 },
              maxWidth: { xs: 350, md: 250 },
            }}
            src={buildFetchFileUrl("portal", relativePath, fileId)}
          />
        </GridItem>
      )}
      <GridItem md={8}>
        <GridContainer>
          <GridItem sm={12} md={4}>
            <BlockText label={"l.university.universityIdentifier"} text={university.id} />
            <BlockText label={"l.university.universitySettlement"} text={university.settlement.fullSettlementName} />
          </GridItem>
        </GridContainer>
      </GridItem>
    </GridContainer>
  );
};
export default UniversitySelectedInfo;
