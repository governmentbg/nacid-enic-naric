import { BoxSpg, MediumButton } from "@duosoftbg/nacid-components";
import { Typography } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faList, faPlus } from "@fortawesome/free-solid-svg-icons";
import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router-dom";
import { createMyApplicationsUrl } from "../../../../../utils/applicationUrlUtils";

const InfoActions = ({ align = "left", baseUrl }: { align?: "left" | "right"; baseUrl: string }) => {
  const { t } = useTranslation();
  const navigate = useNavigate();

  return (
    <BoxSpg mt={4}>
      <Typography align={align}>
        <MediumButton
          startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faPlus} />}
          variant="contained"
          color="primary"
          onClick={() => navigate(`${baseUrl}/new`)}
        >
          {t("l.btn.newRequest")}
        </MediumButton>
        <MediumButton
          ml={2}
          startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faList} />}
          variant="outlined"
          color="primary"
          onClick={() => navigate(createMyApplicationsUrl())}
        >
          {t("l.btn.myApplications")}
        </MediumButton>
      </Typography>
    </BoxSpg>
  );
};

export default InfoActions;
