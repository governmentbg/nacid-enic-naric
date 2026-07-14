import { useTranslation } from "react-i18next";
import { ButtonSpg, TableButton } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import { UniversityControlActions } from "../../../../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";

type CreateUniversityButtonProps = {
  label?: string;
  mt?: number;
  mb?: number;
  variant?: "table" | "contained";
  tempDataKey: string;
  universityIdPointer: string;
  defaultValues?: any;
};

const CreateUniversityButton = ({
  label = "l.btn.createNewUniversity",
  mt = 4,
  mb = 4,
  variant = "contained",
  tempDataKey,
  universityIdPointer,
  defaultValues = null,
}: CreateUniversityButtonProps) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const handleClick = () => {
    dispatch(
      UniversityControlActions.openCreateUniversityModal({
        universityIdPointer,
        tempDataKey,
        initialData: defaultValues,
      }),
    );
  };

  if (variant === "table") {
    return <TableButton title={label} type={"userAdd"} onClick={handleClick} />;
  }
  if (variant === "contained") {
    return (
      <ButtonSpg mt={mt} mb={mb} type={"button"} variant={"contained"} onClick={handleClick}>
        {t(label)}
      </ButtonSpg>
    );
  }

  return null;
};

export default CreateUniversityButton;
