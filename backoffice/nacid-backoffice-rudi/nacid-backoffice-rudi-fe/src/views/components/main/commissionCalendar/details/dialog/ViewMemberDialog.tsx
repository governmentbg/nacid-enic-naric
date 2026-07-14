import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import {
  AlertSpg,
  AsyncCallArgs,
  BlockText,
  CircularLoader,
  GridContainer,
  GridItem,
  useAsyncCall,
  ViewDialog,
  ViewSection,
} from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { closeViewCalendarMemberModal } from "../../../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";
import { getCommissionMember } from "../../../../../../axios/api/services";
import { CardContent } from "@mui/material";

const ViewMemberDialog = () => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const dispatch = useAppDispatch();

  const { open, id } = useAppSelector((state) => {
    return state.ComponentsControl.commissionCalendarControl.modals.viewCalendarMember;
  });
  const [member, setMember] = useState(null);
  const [error, setError] = useState(false);

  useEffect(() => {
    if (open && id != null) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getCommissionMember(id),
        onSuccess: (response) => {
          setMember(response);
          setError(false);
        },
        onError: () => {
          setMember(null);
          setError(true);
        },
      };
      asyncCall(asyncCallArgs);
    }

    // eslint-disable-next-line
    }, [id,open]);

  const handleCloseDialog = () => {
    setMember(null);
    setError(false);
    dispatch(closeViewCalendarMemberModal());
  };

  return (
    <ViewDialog open={open} onClose={handleCloseDialog} title={"t.commission.member.view"}>
      {!member && <CircularLoader />}
      {error && <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>}
      {member && (
        <CardContent style={{ padding: 24, position: "relative" }}>
          <PersonalDetailsSection member={member}></PersonalDetailsSection>
          <ProfDetailsSection member={member}></ProfDetailsSection>
          <ContactsSection memberAddress={member?.address} />
          <BankSection member={member} />
          <AccountSection member={member}></AccountSection>
        </CardContent>
      )}
    </ViewDialog>
  );
};

const ContactsSection = ({ memberAddress }) => {
  const postCode = memberAddress?.postCode;
  const address = memberAddress?.address;
  const phone = memberAddress?.phone;
  const email = memberAddress?.email;
  const settlement = memberAddress?.settlement?.fullSettlementName;

  if (!(postCode || address || phone || email || settlement)) {
    return <EmptyViewSection title={"t.contact.details"} />;
  }

  return (
    <ViewSection label={"t.contact.details"}>
      <GridContainer spacing={3} mt={0}>
        {settlement && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.address.city"} text={settlement} />
          </GridItem>
        )}
        {postCode && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.address.postCode"} text={postCode} />
          </GridItem>
        )}
        {address && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.address.address"} text={address} />
          </GridItem>
        )}
        {phone && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.address.phone"} text={phone} />
          </GridItem>
        )}
        {email && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.address.email"} text={email} />
          </GridItem>
        )}
      </GridContainer>
    </ViewSection>
  );
};

const ProfDetailsSection = ({ member }) => {
  const degree = member?.degre;
  const institution = member?.institution;
  const division = member?.division;
  const title = member?.title;
  const profGroupName = member?.profGroup?.name;
  const commissionPositionName = member?.commissionPosition?.name;

  return (
    <ViewSection label={"t.prof.details"}>
      <GridContainer spacing={3} mt={0}>
        {degree && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.prof.degree"} text={degree} />
          </GridItem>
        )}
        {institution && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.prof.institution"} text={institution} />
          </GridItem>
        )}
        {division && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.prof.division"} text={division} />
          </GridItem>
        )}
        {title && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.prof.title"} text={title} />
          </GridItem>
        )}
        {profGroupName && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.nomenclature.profGroup"} text={profGroupName} />
          </GridItem>
        )}
        {commissionPositionName && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.nomenclature.commissionPosition"} text={commissionPositionName} />
          </GridItem>
        )}
      </GridContainer>
    </ViewSection>
  );
};

const PersonalDetailsSection = ({ member }) => {
  const civilTypeName = member?.civilIdType?.name;
  const civilId = member?.civilId;
  const firstName = member?.firstName;
  const middleName = member?.middleName;
  const lastName = member?.lastName;

  return (
    <ViewSection label={"t.personal.details"}>
      <GridContainer spacing={3} mt={0}>
        {civilTypeName && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.person.personalIdType"} text={civilTypeName} />
          </GridItem>
        )}
        {civilId && (
          <GridItem sm={8} md={8}>
            <BlockText label={"l.id"} text={civilId} />
          </GridItem>
        )}

        {firstName && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.person.firstName"} text={firstName} />
          </GridItem>
        )}

        {middleName && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.person.middleName"} text={middleName} />
          </GridItem>
        )}

        {lastName && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.person.lastName"} text={lastName} />
          </GridItem>
        )}
      </GridContainer>
    </ViewSection>
  );
};

const AccountSection = ({ member }) => {
  const isActive = member?.isActive;
  const { t } = useTranslation();
  return (
    <ViewSection label={"t.account.details"}>
      <GridContainer spacing={3} mt={0}>
        <GridItem sm={4} md={4}>
          <BlockText label={"l.active"} text={isActive ? t("l.yes") : t("l.no")} />
        </GridItem>
      </GridContainer>
    </ViewSection>
  );
};

const BankSection = ({ member }) => {
  const iban = member?.iban;
  const bic = member?.bic;

  if (!(iban || bic)) {
    return <EmptyViewSection title={"t.bank.details"} />;
  }

  return (
    <ViewSection label={"t.bank.details"}>
      <GridContainer spacing={3} mt={0}>
        {iban && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.iban"} text={iban} />
          </GridItem>
        )}
        {bic && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.bic"} text={bic} />
          </GridItem>
        )}
      </GridContainer>
    </ViewSection>
  );
};

type EmptyViewSectionProps = {
  title: string;
  alertType?: "success" | "info" | "warning" | "error";
  message?: string;
};
const EmptyViewSection = ({ title, alertType = "info", message = "m.empty.list" }: EmptyViewSectionProps) => {
  const { t } = useTranslation();
  return (
    <ViewSection ml={0} label={title}>
      <AlertSpg mt={2} severity={alertType}>
        {t(message)}
      </AlertSpg>
    </ViewSection>
  );
};

export default ViewMemberDialog;
