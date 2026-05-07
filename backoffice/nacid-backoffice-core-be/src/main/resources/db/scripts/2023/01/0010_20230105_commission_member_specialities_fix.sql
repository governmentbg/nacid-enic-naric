--liquibase formatted sql

--changeset ggeorgiev:0010
ALTER TABLE rudi.application_commission_member_specialities drop column apn_id;
ALTER TABLE rudi.application_commission_member_specialities drop column commission_member_id;
ALTER TABLE rudi.application_commission_member_specialities add column application_commission_member_id int not null;
alter table rudi.application_commission_member_specialities
    add CONSTRAINT acms_application_commission_member_fk FOREIGN KEY (application_commission_member_id)
    REFERENCES rudi.application_commission_members (id);