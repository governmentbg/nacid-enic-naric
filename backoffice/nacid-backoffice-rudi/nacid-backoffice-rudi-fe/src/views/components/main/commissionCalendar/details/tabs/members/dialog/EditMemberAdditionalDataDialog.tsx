import {
  FlagSelectFormField,
  GridContainer,
  GridItem,
  useReactHookForm,
  ViewDialog,
} from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import React, { useEffect } from "react";
import useAppDispatch from "../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { closeMemberAdditionalData } from "../../../../../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";
import { CommissionParticipationDetails } from "../../../../../../../../types/commissionCalendar/commissionCalendarTypes";
import { commissionParticipationInitialValues } from "../../../../../../../../init/commissionCalendar/commissionParticipationInitialValues";
import { createCommissionParticipationValidationSchema } from "../../../../../../../../yup/schema/commissionCalendar/commissionParticipationValidationSchema";

const Content = ({ methods }) => {
  return (
    <FormProvider {...methods}>
      <form>
        <GridContainer spacing={3}>
          <GridItem sm={4} md={4}>
            <FlagSelectFormField fieldName={"notified"} labelCode={"l.notified"} addEmptyOption={false} />
          </GridItem>
          <GridItem sm={4} md={4}>
            <FlagSelectFormField fieldName={"participated"} labelCode={"l.participated"} addEmptyOption={false} />
          </GridItem>
        </GridContainer>
      </form>
    </FormProvider>
  );
};

const EditMemberAdditionalDataDialog = ({ saveMemberAdditionalData }) => {
  const dispatch = useAppDispatch();

  const { open, member } = useAppSelector((state) => {
    return state.ComponentsControl.commissionCalendarControl.modals.memberAdditionalData;
  });

  const handleCloseDialog = () => {
    dispatch(closeMemberAdditionalData());
  };

  const { methods, handleSubmit } = useReactHookForm<CommissionParticipationDetails>({
    defaultValues: commissionParticipationInitialValues,
    validationSchema: createCommissionParticipationValidationSchema,
  });

  useEffect(() => {
    methods.reset(member);
    // eslint-disable-next-line
    }, [member]);

  const onSubmit = (values) => {
    saveMemberAdditionalData(values);
    handleCloseDialog();
  };

  return (
    <ViewDialog
      open={open}
      onClose={handleCloseDialog}
      title={"t.edit.member.additional.data.dialog"}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
      onSubmitBtnClick={handleSubmit(onSubmit)}
    >
      <Content methods={methods} />
    </ViewDialog>
  );
};

export default EditMemberAdditionalDataDialog;
