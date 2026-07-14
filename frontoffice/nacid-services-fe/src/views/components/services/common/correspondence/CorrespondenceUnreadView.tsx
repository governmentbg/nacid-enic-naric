import {
  AlertSpg,
  AsyncCallArgs,
  BoxSpg,
  ButtonSpg,
  useAsyncCall,
  GridContainer,
  GridItem,
} from "@duosoftbg/nacid-components";
import { toast } from "react-toastify";
import i18n from "i18next";
import { readMyCorrespondence } from "../../../../../services/myCorrespondenceCalls";
import { useTranslation } from "react-i18next";

const CorrespondenceUnreadView = ({ correspondence, onRead }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const readCorrespondence = () => {
    const readAsyncArgs: AsyncCallArgs = {
      promise: readMyCorrespondence(correspondence.applicationId, correspondence.id),
      withGlobalBackdrop: true,
      processResponseErrors: false,
      onSuccess: (response) => {
        onRead(response.data);
      },
      onError: () => {
        toast.error(i18n.t("m.generic.error.service.fail"));
      },
    };
    asyncCall(readAsyncArgs);
  };

  return (
    <BoxSpg>
      <GridContainer spacing={4} mt={0}>
        <GridItem xs={12} sm={12} md={12}>
          <AlertSpg severity={"info"}>{t("m.correspondence.is.unread.info")}</AlertSpg>
        </GridItem>
        <GridItem xs={12} sm={12} md={12}>
          <ButtonSpg variant={"contained"} onClick={readCorrespondence} fullWidth={true}>
            {t("l.btn.read.correspondence")}
          </ButtonSpg>
        </GridItem>
      </GridContainer>
    </BoxSpg>
  );
};
export default CorrespondenceUnreadView;
